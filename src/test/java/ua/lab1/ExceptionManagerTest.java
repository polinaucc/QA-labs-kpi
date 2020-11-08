package ua.lab1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.lab1.exceptions.CriticalException;
import ua.lab1.exceptions.NonCriticalException;
import ua.lab1.manager.ExceptionManagerImpl;
import ua.lab1.manager.ServerFactory;
import ua.lab1.manager.ServerImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionManagerTest {
    ServerImpl server;

    @Mock
    ServerWriter serverWriter;

    @Mock
    ServerFactory serverfactory;

    @Mock
    ConfigNamesReader configNamesReader;

    ExceptionManagerImpl exceptionManager;

    @Test
    public void isCriticalException() {
        exceptionManager = new ExceptionManagerImpl(configNamesReader);
        List<String> exceptionsList = Collections.singletonList("CriticalException");
        when(configNamesReader.readExceptionNames()).thenReturn(new ExceptionNamesDto(exceptionsList));
        exceptionManager.addExceptionClassToCritical();
        CriticalException criticalException = new CriticalException();
        boolean isCritical = exceptionManager.isCriticalException(criticalException);
        assertTrue(isCritical);
    }

    @Test
    public void isNonCriticalException() {
        exceptionManager = new ExceptionManagerImpl(configNamesReader);
        List<String> exceptionsList = Collections.singletonList("CriticalException");
        when(configNamesReader.readExceptionNames()).thenReturn(new ExceptionNamesDto(exceptionsList));
        exceptionManager.addExceptionClassToCritical();

        NonCriticalException nonCriticalException = new NonCriticalException();
        boolean isCritical = exceptionManager.isCriticalException(nonCriticalException);
        assertFalse(isCritical);
    }

    @Test
    public void countExceptions() throws IOException {
        int criticalExceptionsCount = 2;
        int nonCriticalExceptionsCount = 3;

        server = new ServerImpl(serverWriter);
        when(serverfactory.createServer()).thenReturn(server);
        exceptionManager = new ExceptionManagerImpl(serverfactory, configNamesReader);

        List<String> exceptionsList = Collections.singletonList("CriticalException");
        when(configNamesReader.readExceptionNames()).thenReturn(new ExceptionNamesDto(exceptionsList));
        exceptionManager.addExceptionClassToCritical();


        doNothing().when(serverWriter).write(any(String.class));

        for (int i = 0; i < criticalExceptionsCount; i++) {
            CriticalException criticalException = new CriticalException();
            exceptionManager.manageExceptions(criticalException);
        }

        for (int i = 0; i < nonCriticalExceptionsCount; i++) {
            NonCriticalException nonCriticalException = new NonCriticalException();
            exceptionManager.manageExceptions(nonCriticalException);
        }

        assertEquals(criticalExceptionsCount,
                exceptionManager.getCriticalExceptionsNumber());
        assertEquals(nonCriticalExceptionsCount,
                exceptionManager.getNonCriticalExceptionsNumber());
        assertEquals(server.getSuccessfulResponsesNumber(), exceptionManager.getCriticalExceptionsNumber());

    }


}