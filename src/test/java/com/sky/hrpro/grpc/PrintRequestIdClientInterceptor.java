package com.sky.hrpro.grpc;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印 request id 拦截器
 *
 * @author wangjunwei
 * @since 2017-09-19
 */
public class PrintRequestIdClientInterceptor implements ClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PrintRequestIdClientInterceptor.class);



    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
        MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                super.start(
                    new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                        @Override
                        public void onHeaders(Metadata headers) {
                            logger.info("On headers, method: {}, headers: {}.", method.getFullMethodName(), headers);
                            super.onHeaders(headers);
                        }

                        @Override
                        public void onClose(Status status, Metadata trailers) {
                            logger.info("On close, method: {}, trailers: {}.", method.getFullMethodName(), trailers);
                            delegate().onClose(status, trailers);
                        }
                    },
                    headers);
            }
        };
    }
}
