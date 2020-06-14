package com.pbl.backend.common.response;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description: 通用响应状态
 */
public enum  ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "操作成功"),

    /* 错误状态码 */
    FAIL(-1,"操作失败！"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 查询为空 */
    RESULT_NULL(90001,"未查询到任何信息"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),
    PJTASK_GROUPTASK_ALREADY_EXISTED(20002, "该项目任务下,小组已创建同名小组任务"),
    COURSE_SAME_PROJECTNAME(20003, "该课程存在同名项目,项目创建失败"),
    COURES_ALREADY_EXISTS(20004,"已存在同名课程，课程创建失败"),
    COURSE_SAME_PROJECT_TASK(20005,"该项目下存在同名项目任务"),
    UPLOAD_PJ_SHARED_FILE(20006, "文件过大, 不能超过100M"),

    /* 权限错误：70001-79999 */
    PERMISSION_TOKEN_INVALID(70006, "无效token"),
    PERMISSION_TOKEN_EXPIRED(70004, "token已过期"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败");


    //操作码
    private int code;
    //提示信息
    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
