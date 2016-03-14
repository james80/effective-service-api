package com.quattrogatti.effective.common;

import static java.lang.Integer.parseInt;
import static java.util.Optional.ofNullable;
import static org.rapidoid.http.fast.On.port;

@FunctionalInterface
public interface RegisteredController {

    void register();

    static void open(String port) {
        if (ofNullable(port).isPresent()) {
            port(parseInt(port));
        }
    }
}
