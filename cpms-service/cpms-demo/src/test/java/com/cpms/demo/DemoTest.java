package com.cpms.demo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/10/19 9:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    /**
     * jasypt加解密代码
     */
    @Test
    public void jasyptEncrypt(){
        // 默认加密/解密算法是 PBEWithMD5AndDES
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("#p518rcpmsod");// 密钥
        System.out.println(encryptor.encrypt("wq540074./"));
    }

    /**
     * stream流生成树形结构
     */
    @Test
    public void buildTree(){
        //模拟从数据库查询出来
        List<Menu> menus = Arrays.asList(
                new Menu(1,"根节点",0),
                new Menu(2,"子节点1",1),
                new Menu(3,"子节点1.1",2),
                new Menu(4,"子节点1.2",2),
                new Menu(5,"根节点1.3",2),
                new Menu(6,"根节点2",1),
                new Menu(7,"根节点2.1",6),
                new Menu(8,"根节点2.2",6),
                new Menu(9,"根节点2.2.1",7),
                new Menu(10,"根节点2.2.2",7),
                new Menu(11,"根节点3",1),
                new Menu(12,"根节点3.1",11)
        );

        //获取父节点
        List<Menu> collect = menus.stream().filter(m -> m.getParentId() ==0).peek(
                (m) -> m.setChildList(getChildrens(m, menus))
        ).collect(Collectors.toList());

        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));
    }

    /**
     * 递归查询子节点
     * @param root  根节点
     * @param all   所有节点
     * @return 根节点信息
     */
    private List<Menu> getChildrens(Menu root, List<Menu> all) {
        return all.stream().filter(m -> Objects.equals(m.getParentId(), root.getId())).peek(
                m-> m.setChildList(getChildrens(m, all))
        ).collect(Collectors.toList());
    }

     @Data
     class Menu {
        /**
         * id
         */
        public Integer id;
        /**
         * 名称
         */
        public String name;
        /**
         * 父id ，根节点为0
         */
        public Integer parentId;
        /**
         * 子节点信息
         */
        public List<Menu> childList;


        public Menu(Integer id, String name, Integer parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public Menu(Integer id, String name, Integer parentId, List<Menu> childList) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.childList = childList;
        }

    }
}
