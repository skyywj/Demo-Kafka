package com.sky.hrpro.grpc;

import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.AbstractStub;

import java.util.concurrent.TimeUnit;

/**
 * Test Base Class
 *
 * @author wangjunwei
 * @since 2017-09-06
 */
public abstract class TestBase<T extends AbstractStub> {

    private static ManagedChannel channel;

    protected ManagedChannel getManagedChannel() {
        if (channel == null) {
            String host = "127.0.0.1";
            int port = 6565;

            /**
             * GRPC的Client 与 Server 都通过netty channal作为数据通信
             */
            channel =
                NettyChannelBuilder.forAddress(host, port)
                        .usePlaintext(true)
                    .keepAliveTime(15, TimeUnit.SECONDS)
                    .keepAliveTimeout(30, TimeUnit.SECONDS)
                    .userAgent(getUserAgent())
                    .build();
        }
        return channel;
    }

    private final Metadata METADATA = new Metadata();

    {
        String accessToken = "mkMCqbajCx3fWHkpzIwcXSwYR6YRPEjjF7PvyPFEHblfjBtXoB2AKYd-HhuAjoNe";
        METADATA.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer " + accessToken);
    }

    protected String getUserAgent() {
        return "BulletMessenger/0.1.5 (Android; SM919; 1.5.1-2015031300-sfo_lte) grpc-java-netty/1.9.0";
    }

    /**
     * 没有找到好方法，暂时写死
     *
     * @return
     */
    //request head
//    private final ClientInterceptor attachHeadersInterceptor = MetadataUtils.newAttachHeadersInterceptor(METADATA);
    //request id
    private final ClientInterceptor printRequestIdClientInterceptor = new PrintRequestIdClientInterceptor();

    private T stub;

    @SuppressWarnings("unchecked")
    protected T getStub() {
        if (stub != null) {
            return stub;
        }

        stub =
            (T)
                doGetStub().withInterceptors(printRequestIdClientInterceptor);
        return stub;
    }

    protected abstract T doGetStub();

}
