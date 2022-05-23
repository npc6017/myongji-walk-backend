package com.mw.exception.custom;

import com.mw.exception.BusinessException;
import com.mw.utils.Message;

public class CodeNotEqualException extends BusinessException {
    public CodeNotEqualException() {
        super(Message.CODE_NOT_EQUAL);
    }
}
