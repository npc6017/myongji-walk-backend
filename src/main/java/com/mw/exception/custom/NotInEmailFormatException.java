package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class NotInEmailFormatException extends BusinessException {
    public NotInEmailFormatException() {
        super(Message.NOT_IN_EMAIL_FORMAT);
    }
}
