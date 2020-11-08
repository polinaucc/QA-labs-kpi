package ua.lab1.manager;

public interface ExceptionManager {
    Boolean isCriticalException(Exception e);

    void manageExceptions(Exception exception);

    int getNonCriticalExceptionsNumber();

    int getCriticalExceptionsNumber();

    void addExceptionClassToCritical();
}
