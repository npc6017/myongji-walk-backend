package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class ObjectNotFoundException extends BusinessException {

    public ObjectNotFoundException() {
        super(Message.OBJECT_NOT_FOUND);
    }
}
