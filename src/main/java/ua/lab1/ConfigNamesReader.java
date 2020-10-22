package ua.lab1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ConfigNamesReader {
    public static ExceptionNamesDto readExceptionNames() {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("D:\\1\\Полина\\JavaProjects\\qa-lab-1\\settings.json");
            ExceptionNamesDto exceptionNamesDto = gson.fromJson(reader, ExceptionNamesDto.class);
            System.out.println(exceptionNamesDto.criticalExceptions.get(0));
            return exceptionNamesDto;
        } catch (FileNotFoundException e) {
            return new ExceptionNamesDto();
        }
    }


}
