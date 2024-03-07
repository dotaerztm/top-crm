package com.lzj.admin.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityToMapConverter {

    public static Map<String, Object> convert(Object entity) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(entity));
        }
        return map;
    }
}
