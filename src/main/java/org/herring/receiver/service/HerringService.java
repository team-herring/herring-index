package org.herring.receiver.service;

import java.io.IOException;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface HerringService {
    public void service(Request request) throws IOException;
}
