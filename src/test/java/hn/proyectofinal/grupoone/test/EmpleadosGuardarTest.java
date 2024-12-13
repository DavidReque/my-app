package hn.proyectofinal.grupoone.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmpleadosGuardarTest {
	// Prueba para guardar un empleado
    @Test
    public void testGuardarEmpleado() throws InterruptedException {
        // Inicializa el WebDriver
        WebDriver driver = new EdgeDriver();
        // Abre la página web
        driver.get("http://localhost:8080");

        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Empleados"));

        // Localiza los campos del formulario
        WebElement campoNombre = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-14']"));
        WebElement campoApellido = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-15']"));
        WebElement campoEmail = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-16']"));

        // Localiza el botón de guardar
        WebElement btnGuardar = driver.findElement(By.xpath("//flow-container-root-2521314[@id='ROOT-2521314']/vaadin-app-layout/div/vaadin-split-layout/div[2]/vaadin-horizontal-layout/vaadin-button"));

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
}
