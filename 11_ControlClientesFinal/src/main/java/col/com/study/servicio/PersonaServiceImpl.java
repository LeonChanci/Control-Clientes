package col.com.study.servicio;

import col.com.study.dao.PersonaDao;
import col.com.study.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Para que Spring reconozca la clase PersonaServiceImpl como Clase de Servicios
@Service
public class PersonaServiceImpl implements PersonaService{

    //Inyectar una instancia del objeto PersonaDao
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    //Marcar el método como Transaccional pero como (readOnly = true) solo va a consultar información
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    //Marcar el método como Transaccional
    @Transactional()
    //Guardar el objeto persona en la BD
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    //Marcar el métodoscomo Transaccional
    @Transactional()
    //Eiliminar el objeto persona en la BD
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    //Marcar el método como Transaccional pero como (readOnly = true) solo va a consultar información
    @Transactional(readOnly = true)
    //Buscar el objeto persona en la BD
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
