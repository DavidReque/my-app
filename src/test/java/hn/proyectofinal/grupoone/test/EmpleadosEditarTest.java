package hn.proyectofinal.grupoone.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmpleadosEditarTest {

    // Prueba para editar un empleado
    @Test
    public void testEditarEmpleado() throws InterruptedException {
        // Inicializa el WebDriver
        WebDriver driver = new EdgeDriver();
        
        // Abre la página web
        driver.get("http://localhost:8080");

        // Espera a que la página cargue y tenga el título "Empleados"
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Empleados"));

     // Localiza el curso a editar (puedes identificarlo por un botón específico o fila en la lista)
        WebElement filaEmpleado = driver.findElement(By.xpath("//flow-container-root-2521314[@id='ROOT-2521314']/vaadin-app-layout/div/vaadin-split-layout/div/vaadin-grid/vaadin-grid-cell-content[17]"));
        filaEmpleado.click();

        WebElement campoNombre = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-14']"));
        WebElement campoApellido = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-15']"));
        WebElement campoEmail = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-16']"));

        // Borra el contenido anterior y agrega nuevos valores
        campoNombre.clear();
        campoNombre.sendKeys("Ana");
        campoApellido.clear();
        campoApellido.sendKeys("Romero");
        campoEmail.clear();
        campoEmail.sendKeys("ana@gmail.com");

        // Localiza el botón de guardar cambios
        WebElement btnGuardar = driver.findElement(By.xpath("//flow-container-root-2521314[@id='ROOT-2521314']/vaadin-app-layout/div/vaadin-split-layout/div[2]/vaadin-horizontal-layout/vaadin-button"));
        btnGuardar.click();

        // Espera y verifica que se muestre la notificación de éxito
        Thread.sleep(4000);
        try {
            WebElement notificacionExito = driver.findElement(By.xpath("//vaadin-notification-card[contains(.,'Empleado editado exitosamente!')]"));

            String expected = "Empleado editado exitosamente!";
            String actual = notificacionExito.getText();
            assertEquals(expected, actual);

        } catch (Exception e) {
            assertTrue(e.getMessage() == null, "No se encontró la notificación de éxito.");
        } finally {
            // Cierra el navegador después de la prueba
            driver.quit();
        }
    }
}
