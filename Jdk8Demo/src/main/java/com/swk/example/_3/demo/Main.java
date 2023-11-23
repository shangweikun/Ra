package com.swk.example._3.demo;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;
import java.util.*;

public class Main {

    private static final String _TOKEN = "04160484459B150A3D237224FCF38B6942274FD06FC42924B1FE619E1A01298CFA453E59D4F4F86A2CD59A889C17DCAFD964771859DCF46B511B342E5C1EDFDC463922A1EC1D157BAED6DA3BFBC3FDECA2B8F533086D4990F4994914C8A9F24F226B5C7A2B164B2892F0F8F3554FB7CBB8EC1EE59CBDB8C4A3C1923369FD027D6A2EB4F2F93F84AA14C29BEA6DAD87A532755653169515494F26D231B0A465C4ADC69F5742B45";

    private static final class CEB_BANK_BOP_INFO implements Serializable {
        private String ID;
        private String BOP_CIF_NO;
        private String BOP_CIF_CARD;
        private String BOP_CIF_TYPE;
        private String BOP_KHBSHM;
        private String REL_ORDER_ID;
        private String QUERY_ORDER_ID;
        private String QUERY_TIME;
        private String QUERY_TASK_ID;
        private String ORA_ROW_ID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getBOP_CIF_NO() {
            return BOP_CIF_NO;
        }

        public void setBOP_CIF_NO(String BOP_CIF_NO) {
            this.BOP_CIF_NO = BOP_CIF_NO;
        }

        public String getBOP_CIF_CARD() {
            return BOP_CIF_CARD;
        }

        public void setBOP_CIF_CARD(String BOP_CIF_CARD) {
            this.BOP_CIF_CARD = BOP_CIF_CARD;
        }

        public String getBOP_CIF_TYPE() {
            return BOP_CIF_TYPE;
        }

        public void setBOP_CIF_TYPE(String BOP_CIF_TYPE) {
            this.BOP_CIF_TYPE = BOP_CIF_TYPE;
        }

        public String getBOP_KHBSHM() {
            return BOP_KHBSHM;
        }

        public void setBOP_KHBSHM(String BOP_KHBSHM) {
            this.BOP_KHBSHM = BOP_KHBSHM;
        }

        public String getREL_ORDER_ID() {
            return REL_ORDER_ID;
        }

        public void setREL_ORDER_ID(String REL_ORDER_ID) {
            this.REL_ORDER_ID = REL_ORDER_ID;
        }

        public String getQUERY_ORDER_ID() {
            return QUERY_ORDER_ID;
        }

        public void setQUERY_ORDER_ID(String QUERY_ORDER_ID) {
            this.QUERY_ORDER_ID = QUERY_ORDER_ID;
        }

        public String getQUERY_TIME() {
            return QUERY_TIME;
        }

        public void setQUERY_TIME(String QUERY_TIME) {
            this.QUERY_TIME = QUERY_TIME;
        }

        public String getQUERY_TASK_ID() {
            return QUERY_TASK_ID;
        }

        public void setQUERY_TASK_ID(String QUERY_TASK_ID) {
            this.QUERY_TASK_ID = QUERY_TASK_ID;
        }

        public String getORA_ROW_ID() {
            return ORA_ROW_ID;
        }

        public void setORA_ROW_ID(String ORA_ROW_ID) {
            this.ORA_ROW_ID = ORA_ROW_ID;
        }

        @Override
        public String toString() {
            return "CEB_BANK_BOP_INFO{" +
                    "ID='" + ID + '\'' +
                    ", BOP_CIF_NO='" + BOP_CIF_NO + '\'' +
                    ", BOP_CIF_CARD='" + BOP_CIF_CARD + '\'' +
                    ", BOP_CIF_TYPE='" + BOP_CIF_TYPE + '\'' +
                    ", BOP_KHBSHM='" + BOP_KHBSHM + '\'' +
                    ", REL_ORDER_ID='" + REL_ORDER_ID + '\'' +
                    ", QUERY_ORDER_ID='" + QUERY_ORDER_ID + '\'' +
                    ", QUERY_TIME='" + QUERY_TIME + '\'' +
                    ", QUERY_TASK_ID='" + QUERY_TASK_ID + '\'' +
                    ", ORA_ROW_ID='" + ORA_ROW_ID + '\'' +
                    '}';
        }
    }

    private static final class MS_ORDER_DETAIL implements Serializable {
        private String JNLNO;
        private String DEALTIME;
        private String DEALCODE;
        private String DEALSTATE;
        private String ACNO;
        private String AMOUNT;
        private String USERID;
        private String USERNAME;
        private String CIFNO;
        private String MOBILEPHONE;
        private String OPERNO;
        private String OPERKEY;
        private String OPERNAME;
        private String WORKORG;
        private String PRODUCTCODE;
        private String PRODUCTNAME;
        private String DEALAMT;
        private String DEALTYPE;
        private String STAMP;
        private String DEPTIDFST;
        private String DEPTNAMEFST;
        private String BRANCHID;
        private String BRANCHNAME;
        private String DEPTNAME;
        private String INCHANNEL;
        private String ORDER_TYPE;
        private String YLJ_COMPONENT_INFO;
        private String YLJ_STATUS;
        private String ORA_ROW_ID;

        public String getJNLNO() {
            return JNLNO;
        }

        public void setJNLNO(String JNLNO) {
            this.JNLNO = JNLNO;
        }

        public String getDEALTIME() {
            return DEALTIME;
        }

        public void setDEALTIME(String DEALTIME) {
            this.DEALTIME = DEALTIME;
        }

        public String getDEALCODE() {
            return DEALCODE;
        }

        public void setDEALCODE(String DEALCODE) {
            this.DEALCODE = DEALCODE;
        }

        public String getDEALSTATE() {
            return DEALSTATE;
        }

        public void setDEALSTATE(String DEALSTATE) {
            this.DEALSTATE = DEALSTATE;
        }

        public String getACNO() {
            return ACNO;
        }

        public void setACNO(String ACNO) {
            this.ACNO = ACNO;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getUSERID() {
            return USERID;
        }

        public void setUSERID(String USERID) {
            this.USERID = USERID;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
        }

        public String getCIFNO() {
            return CIFNO;
        }

        public void setCIFNO(String CIFNO) {
            this.CIFNO = CIFNO;
        }

        public String getMOBILEPHONE() {
            return MOBILEPHONE;
        }

        public void setMOBILEPHONE(String MOBILEPHONE) {
            this.MOBILEPHONE = MOBILEPHONE;
        }

        public String getOPERNO() {
            return OPERNO;
        }

        public void setOPERNO(String OPERNO) {
            this.OPERNO = OPERNO;
        }

        public String getOPERKEY() {
            return OPERKEY;
        }

        public void setOPERKEY(String OPERKEY) {
            this.OPERKEY = OPERKEY;
        }

        public String getOPERNAME() {
            return OPERNAME;
        }

        public void setOPERNAME(String OPERNAME) {
            this.OPERNAME = OPERNAME;
        }

        public String getWORKORG() {
            return WORKORG;
        }

        public void setWORKORG(String WORKORG) {
            this.WORKORG = WORKORG;
        }

        public String getPRODUCTCODE() {
            return PRODUCTCODE;
        }

        public void setPRODUCTCODE(String PRODUCTCODE) {
            this.PRODUCTCODE = PRODUCTCODE;
        }

        public String getPRODUCTNAME() {
            return PRODUCTNAME;
        }

        public void setPRODUCTNAME(String PRODUCTNAME) {
            this.PRODUCTNAME = PRODUCTNAME;
        }

        public String getDEALAMT() {
            return DEALAMT;
        }

        public void setDEALAMT(String DEALAMT) {
            this.DEALAMT = DEALAMT;
        }

        public String getDEALTYPE() {
            return DEALTYPE;
        }

        public void setDEALTYPE(String DEALTYPE) {
            this.DEALTYPE = DEALTYPE;
        }

        public String getSTAMP() {
            return STAMP;
        }

        public void setSTAMP(String STAMP) {
            this.STAMP = STAMP;
        }

        public String getDEPTIDFST() {
            return DEPTIDFST;
        }

        public void setDEPTIDFST(String DEPTIDFST) {
            this.DEPTIDFST = DEPTIDFST;
        }

        public String getDEPTNAMEFST() {
            return DEPTNAMEFST;
        }

        public void setDEPTNAMEFST(String DEPTNAMEFST) {
            this.DEPTNAMEFST = DEPTNAMEFST;
        }

        public String getBRANCHID() {
            return BRANCHID;
        }

        public void setBRANCHID(String BRANCHID) {
            this.BRANCHID = BRANCHID;
        }

        public String getBRANCHNAME() {
            return BRANCHNAME;
        }

        public void setBRANCHNAME(String BRANCHNAME) {
            this.BRANCHNAME = BRANCHNAME;
        }

        public String getDEPTNAME() {
            return DEPTNAME;
        }

        public void setDEPTNAME(String DEPTNAME) {
            this.DEPTNAME = DEPTNAME;
        }

        public String getINCHANNEL() {
            return INCHANNEL;
        }

        public void setINCHANNEL(String INCHANNEL) {
            this.INCHANNEL = INCHANNEL;
        }

        public String getORDER_TYPE() {
            return ORDER_TYPE;
        }

        public void setORDER_TYPE(String ORDER_TYPE) {
            this.ORDER_TYPE = ORDER_TYPE;
        }

        public String getYLJ_COMPONENT_INFO() {
            return YLJ_COMPONENT_INFO;
        }

        public void setYLJ_COMPONENT_INFO(String YLJ_COMPONENT_INFO) {
            this.YLJ_COMPONENT_INFO = YLJ_COMPONENT_INFO;
        }

        public String getYLJ_STATUS() {
            return YLJ_STATUS;
        }

        public void setYLJ_STATUS(String YLJ_STATUS) {
            this.YLJ_STATUS = YLJ_STATUS;
        }

        public String getORA_ROW_ID() {
            return ORA_ROW_ID;
        }

        public void setORA_ROW_ID(String ORA_ROW_ID) {
            this.ORA_ROW_ID = ORA_ROW_ID;
        }

        @Override
        public String toString() {
            return "MS_ORDER_DETAIL{" +
                    "JNLNO='" + JNLNO + '\'' +
                    ", DEALTIME='" + DEALTIME + '\'' +
                    ", DEALCODE='" + DEALCODE + '\'' +
                    ", DEALSTATE='" + DEALSTATE + '\'' +
                    ", ACNO='" + ACNO + '\'' +
                    ", AMOUNT='" + AMOUNT + '\'' +
                    ", USERID='" + USERID + '\'' +
                    ", USERNAME='" + USERNAME + '\'' +
                    ", CIFNO='" + CIFNO + '\'' +
                    ", MOBILEPHONE='" + MOBILEPHONE + '\'' +
                    ", OPERNO='" + OPERNO + '\'' +
                    ", OPERKEY='" + OPERKEY + '\'' +
                    ", OPERNAME='" + OPERNAME + '\'' +
                    ", WORKORG='" + WORKORG + '\'' +
                    ", PRODUCTCODE='" + PRODUCTCODE + '\'' +
                    ", PRODUCTNAME='" + PRODUCTNAME + '\'' +
                    ", DEALAMT='" + DEALAMT + '\'' +
                    ", DEALTYPE='" + DEALTYPE + '\'' +
                    ", STAMP='" + STAMP + '\'' +
                    ", DEPTIDFST='" + DEPTIDFST + '\'' +
                    ", DEPTNAMEFST='" + DEPTNAMEFST + '\'' +
                    ", BRANCHID='" + BRANCHID + '\'' +
                    ", BRANCHNAME='" + BRANCHNAME + '\'' +
                    ", DEPTNAME='" + DEPTNAME + '\'' +
                    ", INCHANNEL='" + INCHANNEL + '\'' +
                    ", ORDER_TYPE='" + ORDER_TYPE + '\'' +
                    ", YLJ_COMPONENT_INFO='" + YLJ_COMPONENT_INFO + '\'' +
                    ", YLJ_STATUS='" + YLJ_STATUS + '\'' +
                    ", ORA_ROW_ID='" + ORA_ROW_ID + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Map<String, Object> param = new HashMap<>();
        Map<String, String> condition1 = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();

        param.put("name", "CEB_BANK_BOP_INFO");
        param.put("conditions", list);
        list.add(condition1);
        condition1.put("name", "REL_ORDER_ID");
        condition1.put("match", "NULL");

        HttpRequest request = HttpUtil.createPost("http://ebdc-mp.mp.io/ebdcService/nosec/eadmin/ds/s1")
                .contentType(ContentType.JSON.getValue())
                .body(JSONUtil.toJsonStr(param));

        String resultBody = request.execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(resultBody);

        List<CEB_BANK_BOP_INFO> listData = ((JSONArray) jsonObject.get("list")).toList(CEB_BANK_BOP_INFO.class);

        for (CEB_BANK_BOP_INFO bopInfo : listData) {
            String orderId = bopInfo.QUERY_ORDER_ID;
            Optional<String> optionalOrderRowId = getRowIdByOrderId(orderId);

            if (optionalOrderRowId.isPresent()) {
                update_MS_ORDER_DETAIL(bopInfo, optionalOrderRowId.get());
                update_CEB_BANK_BOP_INFO(bopInfo);
            } else {
                System.err.println(orderId + "可能存在异常，请检查");
            }

            Thread.sleep(1000L);
            break;
        }

    }

    private static void update_MS_ORDER_DETAIL(CEB_BANK_BOP_INFO bopInfo, String orderRowId) {
        Map<String, Object> param = new HashMap<>();
        Map<String, String> condition1 = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
        List<Map<String, String>> columns = new ArrayList<>();
        Map<String, String> column1 = new HashMap<>();
        Map<String, String> column2 = new HashMap<>();
        Map<String, String> column3 = new HashMap<>();
        Map<String, String> column4 = new HashMap<>();

        column1.put("name", "DEALSTATE");
        column1.put("value", "0");
        column2.put("name", "DEALAMT");
        column2.put("value", "1");
        column3.put("name", "YLJ_STATUS");
        column3.put("value", "S");
        column4.put("name", "YLJ_COMPONENT_INFO");
        column4.put("value", "20221223 投产导致问题修复");

        param.put("name", "MS_ORDER_DETAIL");
        param.put("columns", columns);
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        columns.add(column4);
        param.put("conditions", list);
        list.add(condition1);

        condition1.put("name", "ROWID");
        condition1.put("match", "EQUALS");
        condition1.put("value", orderRowId);

        HttpRequest request = HttpUtil.createPost("http://ebdc-mp.mp.io/ebdcService/nosec/eadmin/ds/u2")
                .contentType(ContentType.JSON.getValue())
                .header("token", _TOKEN)
                .body(JSONUtil.toJsonStr(param));

        System.out.println("update_MS_ORDER_DETAIL" + bopInfo.QUERY_ORDER_ID + "处理结果" + request.execute().body());
    }

    private static void update_CEB_BANK_BOP_INFO(CEB_BANK_BOP_INFO bopInfo) {

        Map<String, Object> param = new HashMap<>();
        Map<String, String> condition1 = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
        List<Map<String, String>> columns = new ArrayList<>();
        Map<String, String> column1 = new HashMap<>();

        column1.put("name", "REL_ORDER_ID");
        column1.put("value", bopInfo.QUERY_ORDER_ID);
        param.put("name", "CEB_BANK_BOP_INFO");
        param.put("columns", columns);
        columns.add(column1);

        param.put("conditions", list);
        list.add(condition1);
        condition1.put("name", "ROWID");
        condition1.put("match", "EQUALS");
        condition1.put("value", bopInfo.ORA_ROW_ID);

        HttpRequest request = HttpUtil.createPost("http://ebdc-mp.mp.io/ebdcService/nosec/eadmin/ds/u2")
                .contentType(ContentType.JSON.getValue())
                .header("token", _TOKEN)
                .body(JSONUtil.toJsonStr(param));

        System.out.println("update_CEB_BANK_BOP_INFO" + bopInfo.QUERY_ORDER_ID + "处理结果" + request.execute().body());
    }

    private static Optional<String> getRowIdByOrderId(String orderId) {

        Map<String, Object> param = new HashMap<>();
        Map<String, String> condition1 = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();

        param.put("name", "MS_ORDER_DETAIL");
        param.put("conditions", list);
        list.add(condition1);
        condition1.put("name", "JNLNO");
        condition1.put("match", "EQUALS");
        condition1.put("value", orderId);

        HttpRequest request = HttpUtil.createPost("http://ebdc-mp.mp.io/ebdcService/nosec/eadmin/ds/s1")
                .contentType(ContentType.JSON.getValue())
                .body(JSONUtil.toJsonStr(param));

        String resultBody = request.execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(resultBody);

        List<MS_ORDER_DETAIL> listData = ((JSONArray) jsonObject.get("list")).toList(MS_ORDER_DETAIL.class);

        if (listData.size() != 1) {
            throw new RuntimeException("查询MS_ORDER_DETAIL订单数大于1，异常");
        }

        if ("S".equals(listData.get(0).YLJ_STATUS)) {
            System.err.println("存在可能的错误：" + listData.get(0));
            return Optional.empty();
        }

        return Optional.of(listData.get(0).ORA_ROW_ID);
    }
}
