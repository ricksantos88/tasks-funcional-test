package br.ce.devops.tasks.funcional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TaskTest {

    public WebDriver accessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = accessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("teste selenium");
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2099");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = accessarAplicacao();

        try {

            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("dueDate")).sendKeys("10/10/2099");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = accessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("teste selenium");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = accessarAplicacao();

        try {
            driver.findElement(By.id("addTodo")).click();

            driver.findElement(By.id("task")).sendKeys("teste selenium");
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2009");

            driver.findElement(By.id("saveButton")).click();

            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
            driver.quit();
        }
    }

}
