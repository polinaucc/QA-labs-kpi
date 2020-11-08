package ua.lab1;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ConfigNamesReader {
    public ExceptionNamesDto readExceptionNames() {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("./src/settings.json");
            return gson.fromJson(reader, ExceptionNamesDto.class);
        } catch (FileNotFoundException e) {
            return new ExceptionNamesDto();
        }
    }


}
