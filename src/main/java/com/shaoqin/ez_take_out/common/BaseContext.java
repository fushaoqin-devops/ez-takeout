package com.shaoqin.ez_take_out.common;

/**
 * ClassName: BaseContext
 * Package: com.shaoqin.ez_take_out.common
 * Description:
 * Author Shaoqin
 * Create 6/26/23 4:26 PM
 * Version 1.0
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrendId() {
        return threadLocal.get();
    }

}
