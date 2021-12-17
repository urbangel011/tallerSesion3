package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyWhenDoDefs {
    private AppiumDriver driver;
    private By nomNotaObtenida=By.id("com.vrproductiveapps.whendo:id/home_list_item_text");


    @Given("que tengo acceso a la aplicacion whenDo")
    public void queTengoAccesoALaAplicacionWhenDo() throws MalformedURLException {
    //ingresar capabilities del json generado en appium inspector
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "SM-G975F");
        capabilities.setCapability("appium:platformVersion", "11");
        capabilities.setCapability("appium:appPackage", "com.vrproductiveapps.whendo");
        capabilities.setCapability("appium:appActivity", "com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName", "Android");
        //poner la ruta del appium server, lo puedes sacar del inspector
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        // implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        System.out.println("Ejecucion iniciada");
    }

    @When("agrego una nota con los datos")
    public void agregoUnaNotaConLosDatos(WhenDo whenDo) {
        System.out.println("TEST INCIADO");

        //Boton + para añadir
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        //Escribir titulo
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys(whenDo.getNomTituloNote());
        //Escribir nombre de la nota
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextNotes']")).sendKeys(whenDo.getNomDescripcionActividad());
        //pulsar en check para guardar
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/saveItem']")).click();

        //explicit wait
        WebDriverWait explicitWait = new WebDriverWait(driver, 5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(nomNotaObtenida));

    }

    @DataTableType
    public WhenDo convert(Map<String,String> entry){
        return new WhenDo(entry.get("titulo de nota"),entry.get("descripcion de actividad"));
    }

    @Then("la nota {string} debe ser creado")
    public void laNotaDebeSerCreado(String resultadoEsperado) {
   //VERIFICATION RESULT

        String resultadoObtenido = driver.findElement(nomNotaObtenida).getText();

        Assertions.assertEquals(resultadoEsperado, resultadoObtenido, "La suma fue incorrecta");
        System.out.println("Resultado Obtenido: " + resultadoObtenido);

    }
}
