package ua.lab1.manager;

import lombok.Data;
import ua.lab1.ConfigNamesReader;

import java.util.Set;

@Data
public class ExceptionManagerImpl implements ExceptionManager {
    private Set<String> criticalExceptionClassNamesList;

    private int nonCriticalExceptionsNumber;
    private int criticalExceptionsNumber;

    private ConfigNamesReader configNamesReader;
    private Server server;
    private ServerFactory serverFactory;

    public ExceptionManagerImpl(ServerFactory serverFactory, ConfigNamesReader configNamesReader) {
        this.serverFactory = serverFactory;
        this.server = serverFactory.createServer();
        this.configNamesReader = configNamesReader;
    }

    public ExceptionManagerImpl(ConfigNamesReader configNamesReader) {
        this.configNamesReader = configNamesReader;
    }

    @Override
    public void addExceptionClassToCritical() {
        this.criticalExceptionClassNamesList = Set.copyOf(configNamesReader.readExceptionNames().getCriticalExceptions());
    }

    @Override
    public Boolean isCriticalException(Exception e) {
        return criticalExceptionClassNamesList.contains(e.getClass()
                .getSimpleName());
    }

    @Override
    public void manageExceptions(Exception exception) {
        if (isCriticalException(exception)) {
            criticalExceptionsNumber++;
            server.sendDataToServer(exception.getClass().getSimpleName());
        } else {
            nonCriticalExceptionsNumber++;
        }
    }
}
