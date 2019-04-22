package com.Monk.Server;

public class Constants {

    public static final int PORT = 1000;

    /**
     * Client request codes
     */
    public static final String READ = "READ";
    public static final String WRITE = "WRITE";
    public static final String MODIFY = "MODIFY";
    public static final String DELETE = "DELETE";

    /**
     * Server reply codes
     */
    public static final String REQ_ERROR = "REQERR";

    public static final String READ_OK = "ROK";
    public static final String READ_ERROR = "RERR";

    public static final String WRITE_OK = "WOK";
    public static final String WRITE_ERROR = "WERR";

    public static final String MODIFY_OK = "MOK";
    public static final String MODIFY_ERROR = "MERR";

    public static final String DELETE_OK = "DOK";
    public static final String DELETE_ERROR = "DERR";

    public static final String EXIT = "EXIT";

}
