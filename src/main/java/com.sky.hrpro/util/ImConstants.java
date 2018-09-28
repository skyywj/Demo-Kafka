package com.sky.hrpro.util;

import io.grpc.Metadata;


/**
 * IM 常量定义
 *
 * @author CarryJey
 * @since 2018-09-28
 */
public abstract class ImConstants {

    private ImConstants() {}

    public static final String HEADER_NAME_REQUEST_ID = "X-Request-Id";
    public static final String HEADER_NAME_REQUEST_IP = "X-Real-IP";

    public static final Metadata.Key<String> HEADER_KEY_REQUEST_ID =
        Metadata.Key.of(HEADER_NAME_REQUEST_ID, Metadata.ASCII_STRING_MARSHALLER);

}
