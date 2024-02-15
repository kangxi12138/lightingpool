package com.githhub.codeman.constant;


public final class PoolConstants {

    private PoolConstants(){}

    public static final int DEFAULT_MIN_SIZE = 10;

    public static final int DEFAULT_MAX_SIZE = 300;


    public static final int DEFAULT_MAX_WAIT_MILLS = 60 * 1000;


    public static final String DEFAULT_VALID_QUERY = "select 1 from dual";


    public static final int DEFAULT_VALID_TIME_OUT_SECONDS = 5;


    public static final boolean DEFAULT_TEST_ON_BORROW = false;


    public static final boolean DEFAULT_TEST_ON_RETURN = false;

    public static final boolean DEFAULT_TEST_ON_IDLE = true;

    
    public static final long DEFAULT_TEST_ON_IDLE_INTERVAL_SECONDS = 60;

}
