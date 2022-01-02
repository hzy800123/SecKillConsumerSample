package com.microvideoSecKill.error.code;

import lombok.Getter;

public enum BusinessErrCode implements BaseErrCode{
    SECKILL_FAILED_ERROR("40090","seckill failed error");

    @Getter
    private String code;

    @Getter
    private String message;

    private BusinessErrCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
