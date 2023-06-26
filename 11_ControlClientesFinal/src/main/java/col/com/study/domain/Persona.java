package col.com.study.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


//Anotacines JPA (Java Persistence API)

//Se agrega la etiqueta @Data de la libreria Lombok para agregar los getter y setters (Java BEAN)
@Data
//Marca esta clase como Entidad
@Entity
//Especifica el nombre tal cual como está en BD
@Table(name = "persona")
public class Persona implements Serializable{
       
    private static final long  serialVersionUID = 1L; 
    
    //Indicar el ID de la tabla
    @Id
    //Forma de generar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    //@NotEmpty -> Para que el atributo (de tipo cadena [String]) no sea vacío
    //@NotNull -> Para que el atributo no sea null
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    
    //@Email -> Etiqueda de valid.javax para validar un atributo de tipo email
    @Email
    private String email;
    
    private String telefono;
}
