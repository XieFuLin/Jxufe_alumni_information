package com.xfl.common.enumeration;


/**
 * Created by XFL.
 * time on 2016/5/20 19:40
 * description: 响应结果枚举类
 */
public enum ResponseStatusEnum {
    /**
     * 响应成功.
     */
    SUCCESS(0, "成功"),
    /**
     * 响应失败.
     */
    FAILURE(1, "失败"),
    /**
     * 参数错误.
     */
    PARAMETER_ERROR(2, "参数错误");
    /**
     * 编码.
     */
    private int code;

    /**
     * 值名称.
     */
    private String value;

    /**
     * 构造函数.
     * @param code 代码
     * @param value 值
     */
    ResponseStatusEnum(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 根据编号获得枚举值.
     *
     * @param code 编号
     * @return 枚举值
     */
    public static String getValueByCode(final int code) {
        for (ResponseStatusEnum responseStatusEnum
                : ResponseStatusEnum.values()) {
            if (responseStatusEnum.getCode() == code) {
                return responseStatusEnum.getValue();
            }
        }
        return null;
    }

    /**
     * 获取code.
     * @return 返回code
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取值.
     * @return 返回值
     */
    public String getValue() {
        return value;
    }
}


