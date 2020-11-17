package ua.lab1.manager;

import lombok.Setter;

@Setter
public class ServerFactory {
    Server server;

    public Server createServer() {
        if (server != null) {
            return server;
        }
        return new ServerImpl();
    }
}
