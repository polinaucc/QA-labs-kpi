package ua.lab1;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExceptionManager {
    public static final ArrayList<String> criticalExceptionClassNamesList
            = new ArrayList<String>();

    private int nonCriticalExceptionsNumber;
    private int criticalExceptionsNumber;

    public Boolean addExceptionClassToCritical(String exceptionName){
        return criticalExceptionClassNamesList.add(exceptionName);
    }

    public Boolean isCriticalException(Exception e) {
        if (criticalExceptionClassNamesList.contains(e.getClass().getCanonicalName())) {
            return true;
        } else {
            return false;
        }
    }

    public void manageExceptions(Exception exception){
        if(isCriticalException(exception)){
            criticalExceptionsNumber++;
        }
        else{
            nonCriticalExceptionsNumber++;
        }
    }
}
