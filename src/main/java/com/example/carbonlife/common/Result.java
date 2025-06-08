package com.example.carbonlife.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenxinzhi
 * 统一封装返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    //状态码
    private String code;
    //异常信息
    private String msg;
    //返回数据
    private Object data;

    /**
     * 请求成功, 不返回数据给前端
     * @return Result
     */
    public static Result success(){
        return new Result(Constants.CODE_200, "", null);
    }

    /**
     * 请求成功，返回提示信息给前端
     * @param msg
     * @return Result
     */
    public static Result success(String msg){
        return new Result(Constants.CODE_200, msg, null);
    }
    /**
     * 请求成功，返回数据给前端
     * @param data
     * @return Result
     */
    public static Result success(Object data){
        return new Result(Constants.CODE_200, "", data);
    }

    /**
     * 请求成功，返回数据和消息给前端
     * @param msg
     * @param data
     * @return Result
     */
    public static Result success(String msg, Object data){
        return new Result(Constants.CODE_200, msg, data);
    }

    /**
     * 请求失败，返回异常信息给前端
     * @param code
     * @param msg
     * @return Result
     */
    public static Result error(String code, String msg){
        return new Result(code, msg, null);
    }

    /**
     * 请求失败，不返回数据给前端
     * @return Result
     */
    public static Result error(){
        return new Result(Constants.CODE_500, "系统错误", null);
    }
}
