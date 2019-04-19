package com.hyjz.hnovel.utils;

import com.google.gson.Gson;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BaseBeanList;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by 王松松
 * date on 2016/10/8.
 * qq：578268701
 * 网络解析工具
 */

public class GsonUtils {

    public static BaseBean fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseBean.class, clazz);
        return gson.fromJson(json, objectType);
    }
    public static BaseBeanList fromJsonArray(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseBeanList.class, clazz);
        return gson.fromJson(json, objectType);
    }
    public static String[] fromArray(String str, Class clazz) {
        str= str.replaceAll("\\[","");
        str=str.replaceAll("\\]","");
        str= str.replaceAll("\\\"","");
        String[] list=str.split(",");

        return list;
    }
    public static <T> T parseJson(String s, Class<T> clz) {
        Gson gson = new Gson();
        T result = null;
        try {
            result = gson.fromJson(s, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }

        };
    }
}
