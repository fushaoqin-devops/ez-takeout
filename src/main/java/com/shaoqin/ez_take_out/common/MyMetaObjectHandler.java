package com.shaoqin.ez_take_out.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * ClassName: MyMetaObjectHandler
 * Package: com.shaoqin.ez_take_out.common
 * Description:
 * Author Shaoqin
 * Create 6/26/23 4:08 PM
 * Version 1.0
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        if (metaObject.hasSetter("createTime")) metaObject.setValue("createTime", now);
        if (metaObject.hasSetter("updateTime")) metaObject.setValue("updateTime", now);
        if (metaObject.hasSetter("createUser")) metaObject.setValue("createUser", BaseContext.getCurrendId());
        if (metaObject.hasSetter("updateUser")) metaObject.setValue("updateUser", BaseContext.getCurrendId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrendId());
    }

}
