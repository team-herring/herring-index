package org.herring.receiver;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringReceiverDispacher implements Runnable {
    private Socket socket;

    public HerringReceiverDispacher(Socket socket) {
        this.socket= socket;
    }

    @Override
    public void run() {

    }

    public void dispatchService(SelectionKey key)
            throws IOException {

    }
}
