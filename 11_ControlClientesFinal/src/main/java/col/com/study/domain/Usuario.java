package col.com.study.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

//Marca esta clase como Entidad
@Entity
@Data
//Especifica el nombre tal cual como está en BD
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //Indicar el ID de la tabla
    @Id
    //Forma de generar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    //Relacion de uno a muchos (UN Usuario puede tener MUCHOS Roles)
    @OneToMany
    //Especificamos la columna que se debe relacionar entre usuario y rol
    @JoinColumn(name="id_usuario")
    
    private List<Rol> roles;
}
