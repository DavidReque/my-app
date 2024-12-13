package hn.proyectofinal.grupoone.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;		

public class LimpiarCamposTest {
	@Test
	public void LimpiarCampos () throws InterruptedException {
		// Inicializa el WebDriver
        WebDriver driver = new EdgeDriver();
        // Abre la página web
        driver.get("http://localhost:8080/person-form");

        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Consultar Certificaciones"));

        WebElement comboBoxCertificacion = driver.findElement(By.xpath("//input[@id='input-vaadin-combo-box-20']"));

		// Selecciona una opción del ComboBox
        comboBoxCertificacion.click();
        WebElement primeraOpcion = driver.findElement(By.xpath("//vaadin-combo-box-item[@id='vaadin-combo-box-item-0']"));
        primeraOpcion.click();

		 // Encuentra los campos de texto
        WebElement txtNombre = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-21']"));
        WebElement txtDescripcion = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-22']"));
        WebElement txtValidez = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-23']"));
		WebElement txtIdCurso = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-24']"));
		WebElement txtIdEmpleado = driver.findElement(By.xpath("//input[@id='input-vaadin-text-field-25']"));


		// Verifica que los campos estén llenos después de la selección
        assertFalse(txtNombre.getAttribute("value").isEmpty());
        assertFalse(txtDescripcion.getAttribute("value").isEmpty());
        assertFalse(txtValidez.getAttribute("value").isEmpty());

        // Encuentra y hace clic en el botón Limpiar
        WebElement buttonLimpiar = driver.findElement(By.xpath("//flow-container-root-2521314[@id='ROOT-2521314']/vaadin-app-layout/vaadin-vertical-layout/vaadin-vertical-layout/vaadin-horizontal-layout/vaadin-button"));
        buttonLimpiar.click();

        // Verificar que el ComboBox se haya limpiado
        assertTrue(comboBoxCertificacion.getAttribute("value").isEmpty());

        // Verificar que los campos de texto se hayan limpiado
        assertTrue(txtNombre.getAttribute("value").isEmpty());
        assertTrue(txtDescripcion.getAttribute("value").isEmpty());
        assertTrue(txtValidez.getAttribute("value").isEmpty());
	}
}
