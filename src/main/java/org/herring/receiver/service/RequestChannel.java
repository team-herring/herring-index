package org.herring.receiver.service;

import org.herring.receiver.service.read.ReadExecutor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class RequestChannel {
    private static final int BUFFER_SIZE = 8198;
    private SocketChannel channel;
    private ByteBuffer buffer;

    public RequestChannel(SelectionKey key) {
        super();
        setupDataGramChannel(key);
        setupByteBuffer();
    }

    public ByteBuffer receive(ReadExecutor read) throws IOException {
        while ((channel.read(buffer)) != -1) {
            buffer.flip();
            read.read(buffer);
            buffer.clear();
        }
        return buffer;
    }

    public void send(ByteBuffer sendBuffer) throws IOException {
        channel.write(sendBuffer);
    }

    public void close() throws IOException {
        if (channel != null) {
            try {
                channel.close();
            } catch (Exception e) {
                channel.close();
            }
        }
    }

    private void setupDataGramChannel(SelectionKey key) {
        this.channel = (SocketChannel) key.channel();
    }

    private void setupByteBuffer() {
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    private InetSocketAddress getInetSocketAddress() {
        InetAddress inetAddress = channel.socket().getInetAddress();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, 5880);
        return inetSocketAddress;
    }

}

