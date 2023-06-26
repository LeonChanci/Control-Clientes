package col.com.study.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


//Marca esta clase como Entidad
@Entity
@Data
//Especifica el nombre tal cual como está en BD
@Table(name = "rol")
public class Rol implements Serializable {
    private static final long  serialVersionUID = 1L; 
    
    //Indicar el ID de la tabla
    @Id
    //Forma de generar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String descripcion;
}
