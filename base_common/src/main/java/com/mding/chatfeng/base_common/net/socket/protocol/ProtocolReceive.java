package com.mding.chatfeng.base_common.net.socket.protocol;

import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ProtocolReceive {

    public OkSocketOptions.Builder ProtocolByReceive() {
        OkSocketOptions.Builder builder = new OkSocketOptions.Builder();
        builder.setReaderProtocol(new IReaderProtocol(){
            @Override
            public int getHeaderLength() {
                return 4;
            }
            @Override
            public int getBodyLength(byte[] header, ByteOrder byteOrder) {
                if (header == null || header.length < getHeaderLength()) {
                    return 0;
                }
                ByteBuffer bb = ByteBuffer.wrap(header);
                bb.order(byteOrder);
                return bb.getInt()-4;
            }
        });
        return builder;
    }
}
