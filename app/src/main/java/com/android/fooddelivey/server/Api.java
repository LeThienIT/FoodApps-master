package com.android.fooddelivey.server;

public class Api {
    public static final String HOST = "192.168.0.104";
    public static final String GET_URL_CATE = "http://"+HOST+"/mysql_ad0820e/Food/getDataCate.php";
    public static final String GET_URL_FOOD = "http://"+HOST+"/mysql_ad0820e/Food/getDataFood.php?categoryid=";

    public static final String GET_URL_CATE_ID = "http://"+HOST+"/mysql_ad0820e/Food/getDataCateId.php?cateid=";
}
