package col.com.study.dao;

import col.com.study.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface de tipo JPA Repositorio
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    //Retornara un objeto de tipo Usuario, al buscarlo por nombre de usuario
    Usuario findByUsername(String username);
}
