package ua.lab1;

import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

@Data
public class ExceptionManager {
    private static final Logger LOGGER =
            LogManager.getLogger(ExceptionManager.class);
    public final List<String> criticalExceptionClassNamesList
            = ConfigNamesReader.readExceptionNames().getCriticalExceptions();

    private int nonCriticalExceptionsNumber;
    private int criticalExceptionsNumber;

//    public Boolean addExceptionClassToCritical(String exceptionName) {
//        return criticalExceptionClassNamesList.add(exceptionName);
//    }

    public Boolean isCriticalException(Exception e) {
        return criticalExceptionClassNamesList.contains(e.getClass()
                .getSimpleName());
    }

    public void manageExceptions(Exception exception) {
        if (isCriticalException(exception)) {
            criticalExceptionsNumber++;
            LOGGER.info(exception.getClass().getSimpleName());
        } else {
            nonCriticalExceptionsNumber++;
        }
    }
}
