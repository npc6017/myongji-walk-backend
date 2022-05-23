package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class AccountAlreadyExistException extends BusinessException {
    public AccountAlreadyExistException() {
        super(Message.ACCOUNT_ALREADY_EXIST);
    }
}
