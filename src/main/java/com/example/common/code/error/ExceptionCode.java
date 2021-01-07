package com.example.common.code.error;

public enum ExceptionCode {
    SUCCESS("0000","处理成功"),
    AUTH("0100","权限校验异常"),
    PARAM("0200","参数校验异常"),
    RESULT("0300","查询结果异常"),
    SYSTEM("9999","系统繁忙，请联系管理员......");
    private String exceCode;
    private String codeDesc;
    private ExceptionCode(String exceCode,String codeDesc){
        this.exceCode = exceCode;
        this.codeDesc = codeDesc;
    }
    public String getExceCode() {
        return exceCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

}
