package mbean;


import Entidades.Contacto;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by darle on 9/18/2017.
 */

@Named("FormRegistro")
@SessionScoped
@ManagedBean
@ViewScoped
public class FormRegistro implements Serializable{

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    private String direccion;
    @NotEmpty
    private String telefono;
    @NotEmpty
    private String correo;

    private Set<Contacto> contactos;
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

        String id = "contacto " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cantidad");
        Contacto contacto = new Contacto(this.nombre, this.apellido, this.direccion, this.telefono, this.correo);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(String.valueOf(id), contacto);

        int aux = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cantidad") + 1;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidad", aux);

        contactos.add(contacto);

        return "index?faces-redirect=true";
    }

    public Set<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(Set<Contacto> contactos) {
        this.contactos = contactos;
    }
}
