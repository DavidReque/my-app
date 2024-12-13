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

public class CursosEditar {
	@Test
	public void testEditarCurso() throws InterruptedException {
	    // Inicializa el WebDriver para Chrome
	    WebDriver driver = new EdgeDriver();
	    // Abre la página web de inicio de sesión
	    driver.get("http://localhost:8080/master-detail");
	    
	    new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Cursos"));

	    try {
	        // Localiza el curso a editar (puedes identificarlo por un botón específico o fila en la lista)
	        WebElement filaCurso = driver.findElement(By.xpath("//vaadin-grid-cell-content[contains(.,'Desarrollo Personal')]"));
	        filaCurso.click();

	        // Localiza los campos de entrada para la información del curso
	        WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txtNombreCurso']/input"));
	        WebElement campoDescripcion = driver.findElement(By.xpath("//vaadin-text-field[@id='txtDescripcion']/input"));
	        WebElement campoDuracion = driver.findElement(By.xpath("xpath=//vaadin-integer-field[@id='txtDuracion']/input"));

	        // Borra el contenido anterior y agrega nuevos valores
	        campoNombre.clear();
	        campoNombre.sendKeys("Desarrollo Personal Avanzado");
	        campoDescripcion.clear();
	        campoDescripcion.sendKeys("Curso avanzado para habilidades interpersonales");
	        campoDuracion.clear();
	        campoDuracion.sendKeys("30 horas");

	        // Localiza el botón de guardar cambios
	        WebElement btnGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btnGuardar']"));
	        btnGuardar.click();

	        // Espera para verificar si se muestra una notificación de éxito
	        Thread.sleep(4000);

	        WebElement notificacionExito = driver.findElement(By.xpath("//vaadin-notification-card[contains(.,'Curso actualizado exitosamente!')]"));

	        String expected = "Curso actualizado exitosamente!";
	        String actual = notificacionExito.getText();
	        assertEquals(expected, actual);

	    } catch (Exception e) {
	        // Si ocurre algún problema, lanza un error
	        assertTrue(e.getMessage() == null, "Ocurrió un problema durante la edición del curso.");
	    } finally {
	        // Cierra el navegador
	        driver.quit();
	    }
	}

}
