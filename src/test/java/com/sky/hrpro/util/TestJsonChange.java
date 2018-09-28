package com.sky.hrpro.util;

import com.sky.hrpro.entity.TestEntity;
import net.sf.json.JSONObject;

/**
 * @Author: CarryJey @Date: 2018/9/28 19:26:33
 */
public class TestJsonChange {

    //通过java对象转换成json
    public static void BeanToJson(Object object) {

        JSONObject js = JSONObject.fromObject(object);
        System.out.println(js);
    }

    //json字符串转换成实体对象
    public static void parseJson(String json) {
        //先将string串转为json对象
        JSONObject js = JSONObject.fromObject(json);
        System.out.println(js);

        //再将json对象转换为实体对象
        TestEntity testEntity = (TestEntity) JSONObject.toBean(js, TestEntity.class);
        System.out.println(testEntity);
    }

    public static void main(String args[]) {

        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("abc");
        testEntity.setAge(23);

        String json = "{\"age\":23,\"id\":1,\"name\":\"abc\"}";

        BeanToJson(testEntity);
        parseJson(json);
    }
}
