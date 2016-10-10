package com.xfl.common.entity;


import com.xfl.common.enumeration.ResponseStatusEnum;

/**
 * Created by XFL.
 * time on 2016/5/20 19:41
 * description: 响应结果实体对象
 */
public class Response {
    /**
     * 返回值状态.
     */
    private Integer status;

    /**
     * 返回信息文本.
     */
    private String message;

    /**
     * 返回对象.
     */
    private Object data;

    /**
     * 空构造函数.
     */
    public Response() {
    }

    /**
     * 构造函数,该构造函数会根据状态码设置消息.
     * @param status 状态码
     */
    public Response(final Integer status) {
        this.status = status;
        this.message = ResponseStatusEnum.getValueByCode(status);
    }

    /**
     * 构造函数.
     * @param status 状态码
     * @param message 消息
     */
    public Response(final Integer status, final String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 构造函数.
     * @param status 状态码
     * @param message 消息
     * @param data 信息
     */
    public Response(final Integer status, final String message, final Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造函数.
     * @param status 状态码
     * @param data 信息
     */
    public Response(final Integer status, final Object data) {
        this.status = status;
        this.message = ResponseStatusEnum.getValueByCode(status);
        this.data = data;
    }

    /**
     * 获取状态码.
     * @return 返回状态码
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态码.
     * @param status 状态码
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取消息.
     * @return 返回消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息.
     * @param message 消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取内容.
     * @return 返回信息内容
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置返回的信息内容.
     * @param data 信息内容
     */
    public void setData(Object data) {
        this.data = data;
    }
}
