package col.com.study.servicio;
 
import col.com.study.domain.Persona;
import java.util.List;

//Interface con los métodos a implementar 
public interface PersonaService {
    
    public List<Persona> listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
}
