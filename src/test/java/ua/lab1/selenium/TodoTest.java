package ua.lab1.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TodoTest {
    public static PageElements pageElements;
    public static WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        pageElements = new PageElements(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://todomvc.com/examples/angularjs/#/");
    }

    @After
    public void after() {
        driver.quit();
    }

    /**
     * Checks if todo was created and todo number was increased
     */
    @Test
    public void createTodo() {
        String todo1 = "smth";
        pageElements.createTodo(todo1);
        Assert.assertEquals(1, pageElements.getTodoCounter());
    }

    /**
     * Checks if todo was toggled and active todo number was decreased
     */
    @Test
    public void toggleTodo() {
        String todo1 = "smth";
        pageElements.createTodo(todo1);
        pageElements.toggleTodo(todo1);
        Assert.assertEquals(0, pageElements.getTodoCounter());
    }

    /**
     * Checks if todos were toggled and todo number was decreased
     */
    @Test
    public void toggleAllTodos() {
        String todo1 = "smth";
        String todo2 = "smth else";
        pageElements.createTodo(todo1);
        pageElements.createTodo(todo2);
        pageElements.toggleAllTodos();
        Assert.assertEquals(0, pageElements.getTodoCounter());
        Assert.assertEquals(2, pageElements.getTodoCount());
    }

    /**
     * Checks if todos were deleted and todo number was decreased
     */
    @Test
    public void deleteTodo() {
        String todo1 = "smth";
        String todo2 = "else";
        String todo3 = "more";
        pageElements.createTodo(todo1);
        pageElements.createTodo(todo2);
        pageElements.createTodo(todo3);
        pageElements.deleteTodo(todo1);
        pageElements.deleteTodo(todo2);
        Assert.assertEquals(1, pageElements.getTodoCount());
    }

    /**
     * Checks if todos was edited
     */
    @Test
    public void editTodo() {
        String todo1 = "smth";
        String newTodo1 = "something";
        pageElements.createTodo(todo1);
        pageElements.editTodo(todo1, newTodo1);
        Assert.assertEquals(newTodo1, pageElements.getCertainTodo(newTodo1).getText());
    }

    /**
     * Checks if active todos are shown
     */
    @Test
    public void activeTodos() {
        String todo1 = "smth";
        String todo2 = "smth else";
        pageElements.createTodo(todo1);
        pageElements.createTodo(todo2);
        pageElements.toggleTodo(todo1);
        pageElements.showActiveTodos();
        Assert.assertEquals(1, pageElements.getTodoCount());
    }

    /**
     * Checks if completed todos are shown
     */
    @Test
    public void completedTodos() {
        String todo1 = "smth";
        String todo2 = "smth else";
        pageElements.createTodo(todo1);
        pageElements.createTodo(todo2);
        pageElements.toggleTodo(todo1);
        pageElements.showCompletedTodos();
        Assert.assertEquals(1, pageElements.getTodoCount());
    }

    /**
     * Checks if completed todos are deleted
     */
    @Test
    public void deleteCompletedTodos() {
        String todo1 = "smth";
        String todo2 = "smth else";
        pageElements.createTodo(todo1);
        pageElements.createTodo(todo2);
        pageElements.toggleAllTodos();
        pageElements.showCompletedTodos();
        pageElements.deleteCompletedTodo();
        Assert.assertEquals(0, pageElements.getTodoCount());
    }


}
