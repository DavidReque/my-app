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
import org.openqa.selenium.support.ui.WebDriverWait;


public class CursosTest {
	
	// test para probar funcionalidad de guardar curso
	@Test
	public void testGuardarCurso() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new ChromeDriver();
		// Abre la página web de inicio de sesión
		driver.get("http://localhost:8080/master-detail");
		
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Cursos"));

        // Localiza los campos de entrada para la información del curso
        WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txtNombreCurso']/input"));
        WebElement campoDescripcion = driver.findElement(By.xpath("//vaadin-text-field[@id='txtDescripcion']/input"));
        WebElement campoDuracion = driver.findElement(By.xpath("//vaadin-text-field[@id='txtDuracion']/input"));

        // Localiza el botón de guardar
        WebElement btnGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btnGuardarCurso']"));

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
	
	@Test
	public void testSeleccionarCursoExistente() {
        // Inicializa el WebDriver para Chrome
        WebDriver driver = new ChromeDriver();
        try {
            // Abre la página principal
            driver.get("http://localhost:8080/master-detail");

            // Espera a que el título de la página sea el esperado
            new WebDriverWait(driver, ofSeconds(30)).until(titleIs("Cursos"));

            // Localiza el grid de cursos
            WebElement grid = driver.findElement(By.xpath("//vaadin-grid"));

            // Espera a que el grid tenga al menos una fila visible
            new WebDriverWait(driver, ofSeconds(10)).until(d -> grid.findElements(By.xpath(".//vaadin-grid-row")).size() > 0);

            // Selecciona la primera fila del grid
            WebElement primeraFila = grid.findElement(By.xpath(".//vaadin-grid-row[1]"));
            primeraFila.click();

            // Verifica que los campos del formulario se hayan poblado
            WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[contains(@label, 'Nombre')]/input"));
            WebElement campoDescripcion = driver.findElement(By.xpath("//vaadin-text-field[contains(@label, 'Descripcion')]/input"));
            WebElement campoDuracion = driver.findElement(By.xpath("//vaadin-integer-field[contains(@label, 'Duración')]/input"));

            // Asegúrate de que los valores no estén vacíos o nulos
            assertFalse(campoNombre.getAttribute("value").isEmpty(), "El nombre del curso no se cargó");
            assertFalse(campoDescripcion.getAttribute("value").isEmpty(), "La descripción del curso no se cargó");
            assertNotNull(campoDuracion.getAttribute("value"), "La duración del curso no se cargó");

        } catch (Exception e) {
            throw new RuntimeException("Error durante la ejecución del test: " + e.getMessage(), e);
        } finally {
            // Cierra el navegador
            driver.quit();
        }
    }
}
