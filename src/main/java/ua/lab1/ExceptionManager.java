package ua.lab1;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExceptionManager {
    public final ArrayList<String> criticalExceptionClassNamesList
            = new ArrayList<>();

    private int nonCriticalExceptionsNumber;
    private int criticalExceptionsNumber;

    public Boolean addExceptionClassToCritical(String exceptionName) {
        return criticalExceptionClassNamesList.add(exceptionName);
    }

    public Boolean isCriticalException(Exception e) {
        return criticalExceptionClassNamesList.contains(e.getClass()
                .getCanonicalName());
    }

    public void manageExceptions(Exception exception) {
        if (isCriticalException(exception)) {
            criticalExceptionsNumber++;
        } else {
            nonCriticalExceptionsNumber++;
        }
    }
}
