package mbean;


import Entidades.Contacto;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by darle on 9/18/2017.
 */

//@Named("dtFormRegistro")
@SessionScoped
@ManagedBean(name="dtFormRegistro")
@RequestScoped

public class FormRegistro implements Serializable{

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;

    private Set<Contacto> contactos;

    private Contacto contactoSeleccionado;

    @PostConstruct
    private void inicializando(){

        contactos = new HashSet<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().putIfAbsent("cantidad", 0);
        System.out.println("Subiendo el Beans de formulario...");
    }

    @PreDestroy
    private void destruyendo(){
        System.out.println("Destruyendo el Beans de formulario...");
    }


    public String guardarContacto(){

        String id = "Contacto " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cantidad");
        Contacto contacto = new Contacto(id, this.nombre, this.apellido, this.direccion, this.telefono, this.correo);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(String.valueOf(id), contacto);

        int aux = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cantidad") + 1;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidad", aux);

        mostrarTo();

        return "index?faces-redirect=true";
    }

    public String eliminar(){

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(contactoSeleccionado.getId(), contactoSeleccionado);
        mostrarTo();
        return "index?faces-redirect=true";
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Contacto Editado", ((Contacto) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Se ha cancelado la modifiación", ((Contacto) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public Object mostrarTo() {
        contactos = new HashSet<>();
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> entry : map.entrySet()){

            if (entry.getKey().contains("Contacto") ){
                System.out.println(entry.getKey() + ": " + entry.getValue());
                contactos.add((Contacto) entry.getValue());
            }
        }



        return null;
    }

    public Set<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(Set<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Contacto getContactoSeleccionado() {
        return contactoSeleccionado;
    }

    public void setContactoSeleccionado(Contacto contactoSeleccionado) {
        this.contactoSeleccionado = contactoSeleccionado;
    }
}
