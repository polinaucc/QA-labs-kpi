package ua.lab1;

import java.io.FileWriter;
import java.io.IOException;

public class ServerWriter {

    public void write(String data) throws IOException {
        FileWriter nFile = new FileWriter("log.txt", true);
        nFile.write("Exception name:" + data + " Time:" + java.time.LocalDateTime.now());
        nFile.close();
    }
}
