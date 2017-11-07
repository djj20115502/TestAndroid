package com.djj.testcontentproviderservice;

import android.net.Uri;

/**
 * Created by DongJunJie on 2016-8-24.
 */
public class Constant {

    /**
     * scheme已经由Android所规定， scheme为：content://
     */
    public static final String SCHEME = "content://";
    /**
     * 主机名（或叫Authority）用于唯一标识这个ContentProvider，外部调用者可以根据这个标识来找到它。
     */
    public static final String AUTHORITY = "com.djj.testcontentproviderservice";
    /**
     * 路径（path）可以用来表示我们要操作的数据，路径的构建应根据业务而定，如下:
     * 要操作person表中id为10的记录，可以构建这样的路径:/person/10
     * 要操作person表中id为10的记录的name字段， person/10/name
     * 要操作person表中的所有记录，可以构建这样的路径:/person
     * 要操作xxx表中的记录，可以构建这样的路径:/xxx
     * 当然要操作的数据不一定来自数据库，也可以是文件、xml或网络等其他存储方式，如下:
     * 要操作xml文件中person节点下的name节点，可以构建这样的路径：/person/name
     */
    public static final String PATH = "";

    public static final String URI_STRING = SCHEME + AUTHORITY + PATH;

    public static final Uri CONTENT_URI = Uri.parse(URI_STRING);
}
