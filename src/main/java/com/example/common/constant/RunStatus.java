package com.example.common.constant;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 10:22
 * @Description TODO
 * @Version 1.0
 **/
public enum RunStatus {
    RUN("1","启动"),
    PAUSE("0","暂停");
    private String code;
    private String desc;
    RunStatus(String code,String desc){
        this.code = code ;
        this.desc = desc ;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
