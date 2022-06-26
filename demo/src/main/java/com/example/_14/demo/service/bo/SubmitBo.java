package com.example._14.demo.service.bo;

import lombok.Data;

/**
 *
 * @author 767133
 * @date 2022/6/208:27
 */
@Data
public class SubmitBo {

    public final String id;
    public final Integer splitId;

    public SubmitBo(String id,
                    Integer splitId) {
        this.id = id;
        this.splitId = splitId;
    }

    private int flag = 0;

    public void handle(int bit) {
        flag |= bit;
    }

    public boolean isHandled() {
        return flag == 15;
    }

    private String column2;

    private String column3;

    private String column4;

    private String column5;

    private String column6;

    private String column7;

    private String column8;

    private String column9;

    private String column10;

    private String column11;

    private String column12;

    private String column13;

    private String column14;

    private String column15;

    private String column16;

    private String column17;

    public String toLineString() {
        return id + "," + column2 + "," + column3 + "," + column4 + "," + column5 + "," + column6 + "," + column7 + ","
                + column8 + "," + column9 + "," + column10 + "," + column11 + "," + column12 + "," + column13 + ","
                + column14 + "," + column15 + "," + column16 + "," + column17;

    }
}
