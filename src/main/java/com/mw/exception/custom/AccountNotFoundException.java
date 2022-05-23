package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException() {
        super(Message.ACCOUNT_NOT_FOUND);
    }
}
