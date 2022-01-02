package com.microvideoSecKill.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microvideoSecKill.error.code.BaseErrCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pumbaa on 2021-08-12.
 */
@Data
@ApiModel(value = "返回结果")
public class ReturnResult<T> {
    @ApiModelProperty(value="信息")
    private String msg;
    @ApiModelProperty(value="数据")
    private T data;
    @ApiModelProperty(value="处理结果")
    private String status;
    @ApiModelProperty(value="返回状态码")
    private String code;

    private ReturnResult () {}

    private ReturnResult(String status, T data, String msg,String code) {
        this.data = data;
        this.msg = msg;
        this.status = status;
        this.code = code;
    }

    public static ReturnResult setSuccessResult(Object data){
        return new ReturnResult("success",data,"","20000");
    }

    public static ReturnResult setFailResult(BaseErrCode errCode) {
        return new ReturnResult("fail",null,errCode.getMessage(),errCode.getCode());
    }

    @Override
    public String toString() {
        ObjectMapper objMapper = new ObjectMapper();

        Map<String, Object> map =  new HashMap<String,Object>();
        map.put("status",this.status);
        map.put("data",this.data);
        map.put("msg",this.msg);
        map.put("code", this.code);
        try {
            return objMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
