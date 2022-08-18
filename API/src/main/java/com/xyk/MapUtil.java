package com.xyk;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 徐亚奎
 * @date 2021-09-29 11:09
 * @Description map集合工具类
 */
public class MapUtil {
    /**
     * 获取key集合
     * */
    public static List<String> getKeyList(Map map){
        ArrayList<String> keyList = new ArrayList<>(10);
        if (CollectionUtils.isEmpty(map)){
            return keyList;
        }
        Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet){
            String key = Objects.isNull(entry.getKey()) ? "" : entry.getKey().toString();
            keyList.add(key);
        }
        return keyList;
    }
    /**
     * 获取value集合
     * */
    public static List<Object> getValueList(Map map){
        ArrayList<Object> keyList = new ArrayList<>(10);
        if (CollectionUtils.isEmpty(map)){
            return keyList;
        }
        Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet){
            keyList.add(entry.getValue());
        }
        return keyList;
    }
}
