package com9.shop.common.exception;

import com9.shop.common.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SoldOutException extends RuntimeException{
    private ErrorCode errorCode;
    public SoldOutException(String message){
        super(message);
    }

    public SoldOutException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public SoldOutException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SoldOutException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
