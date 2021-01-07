package com.example.common.constant;

public enum OperatStatus {
    SUCCESS("1","操作成功"),
    FAIL("0","操作失败");
    private String code;
    private String desc;
    private OperatStatus(String code,String desc){
        this.code = code ;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
