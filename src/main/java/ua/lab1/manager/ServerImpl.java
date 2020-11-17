package ua.lab1.manager;

import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lab1.ServerWriter;

import java.io.IOException;

@Data
public class ServerImpl implements Server {
    private int successfulResponsesNumber;
    private int nonSuccessfulResponsesNumber;

    private ServerWriter serverWriter;

    public ServerImpl() {
        this.serverWriter = new ServerWriter();
    }

    public ServerImpl(ServerWriter serverWriter) {
        this.serverWriter = serverWriter;
    }

    @Override
    public boolean sendDataToServer(String data) {
        try {
            serverWriter.write(data);
            increaseSuccessfulResponsesNumber();
            return true;
        } catch (IOException e) {
            nonSuccessfulResponsesNumber++;
            return false;
        }
    }

    private void increaseSuccessfulResponsesNumber() {
        this.successfulResponsesNumber++;
    }
}
