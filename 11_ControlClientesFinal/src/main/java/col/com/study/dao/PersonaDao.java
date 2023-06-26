package col.com.study.dao;

import col.com.study.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface de tipo JPA Repositorio 
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
