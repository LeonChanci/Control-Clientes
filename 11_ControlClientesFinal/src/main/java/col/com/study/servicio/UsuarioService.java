package col.com.study.servicio;
import col.com.study.dao.UsuarioDao;
import col.com.study.domain.Rol;
import col.com.study.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Para que Spring reconozca la clase UsuarioService como Clase de Servicios -> estrictamente usar el nombre de userDetailService
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    //Inyectar una instancia de alguna clase en este caso UsuarioDao
    @Autowired
    private UsuarioDao usuarioDao;
    
    //Sobrescribimos el método loadUserByUsername para obtener el usuario con: nombre de usuario, constraseña y roles otenidos previamente
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        //Obtener los roles que tiene el usuario obtenido 
        //GrantedAuthority -> se usa para que funcione correctamente SpringSecurity
        var roles = new ArrayList<GrantedAuthority> ();
        for (Rol rol: usuario.getRoles()) {
            
            //Roles otenidos de tipo SimpleGrantedAuthority que implementa GrantedAuthority
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //User -> Clase de Spring que impliementa UserDetails
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
