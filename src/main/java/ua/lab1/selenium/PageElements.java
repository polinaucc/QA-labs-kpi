package ua.lab1.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageElements {

    public WebDriver driver;

    public PageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "new-todo")
    private WebElement inputTodo;

    @FindBy(className = "destroy")
    private WebElement deleteTodo;

    @FindBy(css = "label[for=toggle-all]")
    private WebElement toggleAll;

    @FindBy(className = "todo-list")
    private WebElement todoList;

    @FindBy(className = "toggle")
    private WebElement toggleTodo;

    @FindBy(className = "clear-completed")
    private WebElement clearCompleted;

    @FindBy(xpath = "//*[contains(@class,'todo-count')]//*[contains(@class,'ng-binding')]")
    private WebElement todoCount;

    @FindBy(linkText = "All")
    private WebElement allTodos;

    @FindBy(linkText = "Active")
    private WebElement activeTodos;

    @FindBy(linkText = "Completed")
    private WebElement completedTodos;

    public void createTodo(String todoName) {
        inputTodo.sendKeys(todoName);
        inputTodo.sendKeys(Keys.RETURN);
    }

    public void toggleAllTodos() {
        toggleAll.click();
    }

    public WebElement getCertainTodo(String todoName) {
        return todoList.findElement(By.xpath("//*[contains(text(),'" + todoName + "')]"));
    }

    public void deleteTodo(String todoName) {
        getCertainTodo(todoName).click();
        deleteTodo.click();
    }

    public void editTodo(String todoName, String newName) {
        Actions ac = new Actions(driver);
        ac.doubleClick(getCertainTodo(todoName))
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.DELETE)
                .sendKeys(newName)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    public int getTodoCounter() {
        return Integer.parseInt(todoCount.getText());
    }

    public int getTodoCount() {
        return todoList.findElements(By.tagName("li")).size();
    }

    public void toggleTodo(String todoName) {
        getCertainTodo(todoName).click();
        toggleTodo.click();
    }

    public void showActiveTodos() {
        activeTodos.click();
    }

    public void showCompletedTodos() {
        completedTodos.click();
    }

    public void deleteCompletedTodo() {
        clearCompleted.click();
    }
}
