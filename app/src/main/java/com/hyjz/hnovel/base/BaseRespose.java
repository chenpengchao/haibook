package com.hyjz.hnovel.base;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseRespose<T> implements Serializable {
//    public String code;
//    public String msg;
public boolean ok;
    public T data;
//
//    public boolean success() {
//        return ;
//    }


}
