package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class NotMyoungJiMemberException extends BusinessException {
    public NotMyoungJiMemberException() {
        super(Message.NOT_MYOUNGJI_MEMBER);
    }
}
