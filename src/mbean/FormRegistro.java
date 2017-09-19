package mbean;


import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

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

    @PostConstruct
    private void inicializando(){

        System.out.println("Subiendo el Beans de formulario...");
    }

    @PreDestroy
    private void destruyendo(){
        System.out.println("Destruyendo el Beans de formulario...");
    }



}
