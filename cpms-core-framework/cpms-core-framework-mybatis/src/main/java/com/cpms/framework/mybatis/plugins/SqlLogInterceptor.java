package com.cpms.framework.mybatis.plugins;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import java.sql.Statement;
import java.util.*;

/**
 * 用于输出每条 SQL 语句及其执行时间
 * @author gulang
 * @since 2021-10-30
 */
@Slf4j
@Intercepts({
		@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
		@Signature(type = StatementHandler.class, method = "update", args = Statement.class),
		@Signature(type = StatementHandler.class, method = "batch", args = Statement.class)
})
public class SqlLogInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = PluginUtils.realTarget(invocation.getTarget());
		// 计算执行 SQL 耗时
		long startTime = SystemClock.now();
		StatementHandler statementHandler = (StatementHandler) target;
		int size = 0;
		try {
			Object o = invocation.proceed();
			if (!Objects.isNull(o)  && isList(o.getClass())) {
				size = ((List) o).size();
			}
			return o;
		} finally {
			long timing = SystemClock.now() - startTime;
			BoundSql boundSql = statementHandler.getBoundSql();
			String originalSql = boundSql.getSql();
			Object parameterObject = boundSql.getParameterObject();
			List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
			originalSql = originalSql.replaceAll("[\\s]+", StringPool.SPACE);
			int index = indexOfSqlStart(originalSql);
			if (index > 0) {
				originalSql = originalSql.substring(index);
			}
			// 打印 sql
			System.err.println(
				String.format(
				"\n==============  Sql Start  ==============" +
						"\nExecute SQL ：%s" +
						"\nExecute Time：%d ms" +
						"\nExecute Total：%d " +
						"\n==============  Sql  End   ==============\n",
				 originalSql, timing,size)
			);
		}
	}

	/**
	 * 是否List的实现类
	 */
	private boolean isList(Class<?> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}


	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}



	/**
	 * 获取sql语句开头部分
	 *
	 * @param sql ignore
	 * @return ignore
	 */
	private int indexOfSqlStart(String sql) {
		String upperCaseSql = sql.toUpperCase();
		Set<Integer> set = new HashSet<>();
		set.add(upperCaseSql.indexOf("SELECT "));
		set.add(upperCaseSql.indexOf("UPDATE "));
		set.add(upperCaseSql.indexOf("INSERT "));
		set.add(upperCaseSql.indexOf("DELETE "));
		set.remove(-1);
		if (CollectionUtils.isEmpty(set)) {
			return -1;
		}
		List<Integer> list = new ArrayList<>(set);
		list.sort(Comparator.naturalOrder());
		return list.get(0);
	}

}
