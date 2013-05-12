package org.herring.receiver.service.read;

import org.herring.receiver.service.HerringService;
import org.herring.receiver.service.Request;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class Read implements HerringService {
    @Override
    public void service(Request request) throws IOException {
        ReadExecutor readExecutor = new ReadExecutor() {
            @Override
            public void read(ByteBuffer buffer) {
                System.out.println(String.valueOf(buffer.get()));
            }
        };
        request.receive(readExecutor);
    }
}
