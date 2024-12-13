package hn.proyectofinal.grupoone.test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CamposCertificacionesTest {
    @Test
    public void testCamposSoloLectura() throws InterruptedException {
        // Inicializa el WebDriver
        WebDriver driver = new EdgeDriver();
        // Abre la página web
        driver.get("http://localhost:8080/person-form");

        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Consultar Certificaciones"));

        // Encuentra los campos de texto
        WebElement txtNombre = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-21']"));
        WebElement txtDescripcion = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-22']"));
        WebElement txtValidez = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-23']"));
        WebElement txtIdCurso = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-24']"));
        WebElement txtIdEmpleado = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-25']"));

        // Depuración: Imprimir si los atributos "readonly" existen
        System.out.println("Atributos de los campos:");

        boolean isNombreReadonly = txtNombre.getAttribute("readonly") != null;
        boolean isDescripcionReadonly = txtDescripcion.getAttribute("readonly") != null;
        boolean isValidezReadonly = txtValidez.getAttribute("readonly") != null;
        boolean isIdCursoReadonly = txtIdCurso.getAttribute("readonly") != null;
        boolean isIdEmpleadoReadonly = txtIdEmpleado.getAttribute("readonly") != null;

        // Depuración: Verifica los valores de los atributos
        System.out.println("isNombreReadonly: " + isNombreReadonly);
        System.out.println("isDescripcionReadonly: " + isDescripcionReadonly);
        System.out.println("isValidezReadonly: " + isValidezReadonly);
        System.out.println("isIdCursoReadonly: " + isIdCursoReadonly);
        System.out.println("isIdEmpleadoReadonly: " + isIdEmpleadoReadonly);

        // Verifica que los campos sean de solo lectura
        assertTrue(isNombreReadonly, "El campo Nombre debería ser solo lectura");
        assertTrue(isDescripcionReadonly, "El campo Descripción debería ser solo lectura");
        assertTrue(isValidezReadonly, "El campo Validez debería ser solo lectura");
        assertTrue(isIdCursoReadonly, "El campo ID Curso debería ser solo lectura");
        assertTrue(isIdEmpleadoReadonly, "El campo ID Empleado debería ser solo lectura");

        // Intenta modificar los campos (debería fallar si son solo lectura)
        try {
            if (!isNombreReadonly) {
                txtNombre.sendKeys("Prueba");
                fail("El campo Nombre debería ser de solo lectura");
            }
            if (!isDescripcionReadonly) {
                txtDescripcion.sendKeys("Prueba");
                fail("El campo Descripción debería ser de solo lectura");
            }
            if (!isValidezReadonly) {
                txtValidez.sendKeys("Prueba");
                fail("El campo Validez debería ser de solo lectura");
            }
            if (!isIdCursoReadonly) {
                txtIdCurso.sendKeys("Prueba");
                fail("El campo ID Curso debería ser de solo lectura");
            }
            if (!isIdEmpleadoReadonly) {
                txtIdEmpleado.sendKeys("Prueba");
                fail("El campo ID Empleado debería ser de solo lectura");
            }
        } catch (Exception e) {
            // Si el campo es solo lectura, esta parte no debería ser alcanzada
            fail("Se generó un error inesperado: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
