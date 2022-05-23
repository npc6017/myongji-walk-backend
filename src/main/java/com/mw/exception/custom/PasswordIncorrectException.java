package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class PasswordIncorrectException extends BusinessException {
    public PasswordIncorrectException() {
        super(Message.PASSWORD_INCORRECT);
    }
}
