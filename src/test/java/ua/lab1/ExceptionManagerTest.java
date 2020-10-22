package ua.lab1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.lab1.exceptions.CriticalException;
import ua.lab1.exceptions.NonCriticalException;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class ExceptionManagerTest {
    ExceptionManager exceptionManager;

    @Before
    public void setUp() {
        exceptionManager = new ExceptionManager();
    }

    @Test
    public void isCriticalException() {
        CriticalException criticalException = new CriticalException();
        System.out.println("Name: " + criticalException.getClass().getName());
        boolean isCritical = exceptionManager.isCriticalException(criticalException);
        assertTrue(isCritical);
    }

    @Test
    public void isNonCriticalException() {
        NonCriticalException nonCriticalException = new NonCriticalException();
        boolean isCritical = exceptionManager.isCriticalException(nonCriticalException);
        assertFalse(isCritical);
    }

    @Test
    @Parameters({"2, 3", "5, 6", "10, 11"})
    public void countExceptions(int criticalExceptionsCount,
                                int nonCriticalExceptionsCount) {
        exceptionManager.setCriticalExceptionsNumber(0);
        exceptionManager.setNonCriticalExceptionsNumber(0);

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
    }


}