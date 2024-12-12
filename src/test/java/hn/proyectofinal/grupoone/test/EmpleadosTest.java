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

public class EmpleadosTest {

    // Test para probar la funcionalidad de guardar un empleado
    @Test
    public void testGuardarEmpleado() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/empleados");

        new WebDriverWait(driver, ofSeconds(30)).until(titleIs("Empleados"));

        // Localiza los campos del formulario
        WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txtNombreEmpleado']/input"));
        WebElement campoApellido = driver.findElement(By.xpath("//vaadin-text-field[@id='txtApellidoEmpleado']/input"));
        WebElement campoEmail = driver.findElement(By.xpath("//vaadin-text-field[@id='txtEmailEmpleado']/input"));

        // Localiza el botón de guardar
        WebElement btnGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btnGuardarEmpleado']"));

        // Ingresa datos en el formulario
        campoNombre.sendKeys("Ana");
        campoApellido.sendKeys("Lopez");
        campoEmail.sendKeys("ana.lopez@example.com");

        // Haz clic en guardar
        btnGuardar.click();

        // Espera y verifica que se muestre la notificación de éxito
        Thread.sleep(4000);
        try {
            WebElement notificacionExito = driver.findElement(By.xpath("//vaadin-notification-card[contains(.,'Empleado guardado exitosamente!')]"));

            String expected = "Empleado guardado exitosamente!";
            String actual = notificacionExito.getText();
            assertEquals(expected, actual);

        } catch (Exception e) {
            assertTrue(e.getMessage() == null, "No se encontró la notificación de éxito.");
        } finally {
            driver.quit();
        }
    }

    // Test para verificar la selección de un empleado existente en el grid
    @Test
    public void testSeleccionarEmpleadoExistente() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/empleados");

            new WebDriverWait(driver, ofSeconds(30)).until(titleIs("Empleados"));

            // Localiza el grid de empleados
            WebElement grid = driver.findElement(By.xpath("//vaadin-grid"));

            // Espera a que el grid tenga al menos una fila
            new WebDriverWait(driver, ofSeconds(10)).until(d -> grid.findElements(By.xpath(".//vaadin-grid-row")).size() > 0);

            // Selecciona la primera fila del grid
            WebElement primeraFila = grid.findElement(By.xpath(".//vaadin-grid-row[1]"));
            primeraFila.click();

            // Verifica que los campos del formulario se hayan llenado
            WebElement campoNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txtNombreEmpleado']/input"));
            WebElement campoApellido = driver.findElement(By.xpath("//vaadin-text-field[@id='txtApellidoEmpleado']/input"));
            WebElement campoEmail = driver.findElement(By.xpath("//vaadin-text-field[@id='txtEmailEmpleado']/input"));

            assertFalse(campoNombre.getAttribute("value").isEmpty(), "El nombre del empleado no se cargó");
            assertFalse(campoApellido.getAttribute("value").isEmpty(), "El apellido del empleado no se cargó");
            assertNotNull(campoEmail.getAttribute("value"), "El email del empleado no se cargó");

        } catch (Exception e) {
            throw new RuntimeException("Error durante la ejecución del test: " + e.getMessage(), e);
        } finally {
            driver.quit();
        }
    }
}
