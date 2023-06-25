package com.shaoqin.ez_take_out.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: R
 * Package: com.shaoqin.ez_take_out.common
 * Description: Common return class that maps the backend response data
 * Author Shaoqin
 * Create 6/23/23 11:35 PM
 * Version 1.0
 */
@Data
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
