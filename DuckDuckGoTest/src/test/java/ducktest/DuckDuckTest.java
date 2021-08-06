package ducktest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DuckDuckTest {
    private WebDriver driver;
    private By barraBusquedaDuck = By.id("search_form_input_homepage");
    private By buttonBuscar = By.id("search_button_homepage");

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void busquedaDuckTest(){

        /*Punto 1 y 2: Realizar una búsqueda de una frase o palabra en la página DuckDuckGo
         y validar que el campo de búsqueda de la página de Resultados contenga
         la frase o palabra buscada */

        driver.findElement(barraBusquedaDuck).sendKeys("cat");
        driver.findElement(buttonBuscar).click();

        String element = driver.findElement(By.id("search_form_input")).getAttribute("value");
        System.out.println("Elemento buscado "+ element );

        Assert.assertEquals(element,"cat");

        /*Punto 3: validar que la página de Resultados muestre uno o más links de resultados*/

        List <WebElement> results =  driver.findElements(By.xpath("//div[@class='result__body links_main links_deep']"));
        for(WebElement element1 : results) {
            System.out.println(element1.getText());
        }
        Assert.assertTrue("Debe retornar mas que 0",results.size() > 0);

        /*Punto 4: validar que la página de Resultados contenga
         uno o más links de resultados que contengan la frase o palabra buscada*/

        List <WebElement> countResultados = driver.findElements(By.xpath("//*[contains(text(), 'cat')]"));
        for(WebElement element1 : countResultados) {
            System.out.println(element1.getText());
        }
        Assert.assertTrue("Debe retornar mas que 0",countResultados.size() > 0);









    }


}
