package ua.lab1.manager;

public interface Server {
    boolean sendDataToServer(String data);

    int getSuccessfulResponsesNumber();
}
