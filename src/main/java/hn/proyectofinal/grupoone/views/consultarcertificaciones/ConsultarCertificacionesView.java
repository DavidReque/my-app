package hn.proyectofinal.grupoone.views.consultarcertificaciones;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import hn.proyectofinal.grupoone.data.Certificaciones;
import hn.proyectofinal.grupoone.controller.CertificacionesInteractor;
import hn.proyectofinal.grupoone.controller.CertificacionesInteractorImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.textfield.IntegerField;


@PageTitle("Consultar Certificaciones")
@Route("person-form")
@Menu(order = 3, icon = LineAwesomeIconUrl.ADDRESS_BOOK_SOLID)

public class ConsultarCertificacionesView extends Composite<VerticalLayout> implements ConsultarCertificacionesViewModel {

	private ComboBox<Certificaciones> certificacion;
	private List<Certificaciones> certificaciones;
    private TextField txtNombre;
    private TextField txtdescripcion;
    private TextField txtvalidez;
    private TextField txtcursoID;
    private TextField txtempleadoID;
    private CertificacionesInteractor controlador;   
   
    

    public ConsultarCertificacionesView() {
    	
    	controlador=new CertificacionesInteractorImpl(this);
    	
    	certificaciones=new ArrayList<>();
    	
    	VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3("Información de Certificaciones");
        certificacion = new ComboBox<>("Certificaciones");
        txtNombre = new TextField("Nombre de la certificacion");
        txtdescripcion = new TextField("Descripcion");
        txtvalidez = new TextField("Validez");
        txtcursoID = new TextField("ID del curso");
        txtempleadoID = new TextField("ID del empleado");
        txtNombre.setReadOnly(true);
        txtdescripcion.setReadOnly(true);
        txtvalidez.setReadOnly(true);
        txtcursoID.setReadOnly(true);
        txtempleadoID.setReadOnly(true);        
        FormLayout formLayout2Col = new FormLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonSecondary = new Button();
        //Button buttonPrimary = new Button("BUSCAR");
        //Button buttonSecondary = new Button("LIMPIAR");

        // Configuración del layout principal
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
   
        // Configuración del formulario
        
        formLayout2Col.setWidth("100%");
        formLayout2Col.add(txtNombre, txtdescripcion, txtvalidez, txtcursoID, txtempleadoID);
	    
         
         buttonSecondary.setText("Limpiar"); 
         buttonSecondary.setWidth("min-content");
         layoutColumn2.add(buttonSecondary);
         buttonSecondary.addClickListener(e -> {
             certificacion.clear();
             txtNombre.clear();
             txtvalidez.clear();
             txtdescripcion.clear();
             txtcursoID.clear();
             txtempleadoID.clear();
         });
         
         layoutRow.add(buttonSecondary);
         layoutColumn2.add(h3, certificacion, formLayout2Col, layoutRow);
         getContent().add(layoutColumn2);
         controlador.ConsultarCertificaciones();
    
    
    	}
    
	@Override
	public void mostrarCertificacionesEnComboBox(List<Certificaciones> items) {
		Collection<Certificaciones> itemsCollection = items;
		this.certificaciones = items;
		certificacion.setItems(itemsCollection);
		certificacion.setItemLabelGenerator(
		        person -> person.getNombre() + " " + person.getValidez());
		//Prueba para cargar
		
		certificacion.addValueChangeListener(event -> { Certificaciones selected =
				  certificacion.getValue(); 
		
				if (selected != null) {
				  txtNombre.setValue(selected.getNombre());
				  txtvalidez.setValue(selected.getValidez()); 
				  txtdescripcion.setValue(selected.getDescripcion()); 
				  
				  String idCurso = String.valueOf(selected.getCursoid());
				  System.out.print(selected.getCursoid());
				  txtcursoID.setValue(idCurso);
				  
				  String idEmpleado = String.valueOf(selected.getEmpleadoid());
				  txtempleadoID.setValue(idEmpleado);
				 
		
				}else { 
				  txtNombre.clear(); txtvalidez.clear(); txtdescripcion.clear();
				  txtcursoID.clear();txtempleadoID.clear();
				  } 
				});
		
		
	}
	@Override
	public void mostrarMensajeError(String mensaje) {
	
		Notification notification = new Notification();
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

		Div text = new Div(new Text(mensaje));

		Button closeButton = new Button(new Icon("lumo", "cross"));
		closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
		closeButton.setAriaLabel("Close");
		closeButton.addClickListener(event -> {
		    notification.close();
		});

		HorizontalLayout layout = new HorizontalLayout(text, closeButton);
		layout.setAlignItems(Alignment.CENTER);

		notification.add(layout);
		notification.open();
		
	}	
	


}


