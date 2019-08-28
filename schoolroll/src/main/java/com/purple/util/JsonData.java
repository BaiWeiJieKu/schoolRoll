package com.purple.util;


import lombok.*;

@Data
@Builder
@ToString
public class JsonData {

    private int code;
    private String message;
    private Object obj;

    public static JsonData success () {
        return JsonData.builder().code(200).obj(null).build();
    }

    public static JsonData success (Object obj) {
        return JsonData.builder().code(200).obj(obj).build();
    }

    public static JsonData success (int code, String message) {
        return JsonData.builder().code(code).message(message).build();
    }

    public static JsonData success (String message) {
        return JsonData.builder().code(200).message(message).build();
    }

    public static  JsonData success (String message, Object obj) {
        return JsonData.builder().code(200).message(message).obj(obj).build();
    }

    public static JsonData error (String message) {
        return JsonData.builder().code(500).message(message).build();
    }

    public static JsonData error (int code, String message) {
        return JsonData.builder().code(code).message(message).build();
    }

}

