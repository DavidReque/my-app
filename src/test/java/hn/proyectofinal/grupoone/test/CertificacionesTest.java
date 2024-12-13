package hn.proyectofinal.grupoone.test;

import org.openqa.selenium.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;		

public class CertificacionesTest {

		 private WebDriver driver;
		 private WebDriverWait wait;

	    @BeforeEach
	    public void setUp() {
		
	    	driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        driver.get("http://localhost:8080/person-form"); 
	    }

	    @AfterEach
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @Test
	    public void testClearFields() {
	        // Localizar elementos con espera explícita
	        WebElement certificacionComboBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("certificaciones")));
	        WebElement txtNombre = driver.findElement(By.id("txtNombre"));
	        WebElement txtDescripcion = driver.findElement(By.id("txtdescripcion"));
	        WebElement txtValidez = driver.findElement(By.id("txtvalidez"));
	        WebElement txtCursoID = driver.findElement(By.id("txtcursoID"));
	        WebElement btnLimpiar = driver.findElement(By.id("btnLimpiar"));

	        // Simular la selección de un valor
	        certificacionComboBox.click();
	        WebElement firstOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-combo-box-item")));
	        firstOption.click();

	        // Verificar que los campos contienen valores
	        assertNotEquals("", txtNombre.getAttribute("value"));
	        assertNotEquals("", txtDescripcion.getAttribute("value"));
	        assertNotEquals("", txtValidez.getAttribute("value"));
	        assertNotEquals("", txtCursoID.getAttribute("value"));

	        // Hacer clic en el botón Limpiar
	        btnLimpiar.click();

	        // Verificar que los campos están vacíos
	        assertEquals("", txtNombre.getAttribute("value"));
	        assertEquals("", txtDescripcion.getAttribute("value"));
	        assertEquals("", txtValidez.getAttribute("value"));
	        assertEquals("", txtCursoID.getAttribute("value"));
	    }

	    @Test
	    public void testSelectCertification() {
	        // Localizar elementos con espera explícita
	        WebElement certificacionComboBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("certificaciones")));
	        WebElement txtNombre = driver.findElement(By.id("txtNombre"));
	        WebElement txtDescripcion = driver.findElement(By.id("txtdescripcion"));
	        WebElement txtValidez = driver.findElement(By.id("txtvalidez"));
	        WebElement txtCursoID = driver.findElement(By.id("txtcursoID"));

	        // Simular la selección de un valor
	        certificacionComboBox.click();
	        WebElement firstOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//vaadin-combo-box-item")));
	        firstOption.click();

	        // Verificar que los campos muestran valores después de la selección
	        assertNotEquals("", txtNombre.getAttribute("value"));
	        assertNotEquals("", txtDescripcion.getAttribute("value"));
	        assertNotEquals("", txtValidez.getAttribute("value"));
	        assertNotEquals("", txtCursoID.getAttribute("value"));
	    }
}
