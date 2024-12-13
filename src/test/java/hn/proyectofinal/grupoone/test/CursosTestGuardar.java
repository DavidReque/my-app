package hn.proyectofinal.grupoone.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CursosTestGuardar {
	
	// test para probar funcionalidad de guardar curso
	@Test
	public void testGuardarCurso() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new EdgeDriver();
		// Abre la página web de inicio de sesión
		driver.get("http://localhost:8080/master-detail");
		
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Cursos"));

        // Localiza los campos de entrada para la información del curso
        WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txtNombreCurso']/input"));
        WebElement campoDescripcion = driver.findElement(By.xpath("//vaadin-text-field[@id='txtDescripcion']/input"));
        WebElement campoDuracion = driver.findElement(By.xpath("//vaadin-integer-field[@id='txtDuracion']/input"));

        // Localiza el botón de guardar
        WebElement btnGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btnGuardar']"));

        // Ingresa los datos del curso
        campoNombre.sendKeys("Desarrollo Personal");
        campoDescripcion.sendKeys("Curso para mejorar habilidades interpersonales");
        campoDuracion.sendKeys("20 horas");
        
        btnGuardar.click();
        
        Thread.sleep(4000);
        
        try {
            WebElement notificacionExito = driver.findElement(By.xpath("//vaadin-notification-card[contains(.,'Curso guardado exitosamente!')]"));

            String expected = "Curso guardado exitosamente!";
            String actual = notificacionExito.getText();
            assertEquals(expected, actual);

        } catch (Exception e) {
            // Si no se encuentra la notificación, lanza un error
            assertTrue(e.getMessage() == null, "No se encontró la notificación de éxito.");
        } finally {
            // Cierra el navegador
            driver.quit();
        }
	}
	
}
