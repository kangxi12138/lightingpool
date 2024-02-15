package com.githhub.codeman.constant;


public final class DriverType {

    private DriverType(){}


    public static final String SQL_SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


    public static final String MYSQL = "com.mysql.jdbc.Driver";


    //jdbc.url=jdbc:mysql://localhost:3306/k3c?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&serverTimezone=Hongkong
    public static final String MYSQL_8 = "com.mysql.cj.jdbc.Driver";


    public static final String ORACLE = "oracle.jdbc.OracleDriver";

}
