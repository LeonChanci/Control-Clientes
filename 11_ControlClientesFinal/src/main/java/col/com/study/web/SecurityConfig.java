package col.com.study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Clase de congifuración de Spring en cargada de la securidad web
@Configuration

//@EnableWebSecurity -> Habilitar la seguridad web
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //Instancia de UsuarioService
    @Autowired
    private UserDetailsService userDetailsService;

    //Método para encriptar la contraseña
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    //Spring buscará una implementación del objeto AuthenticationManagerBuilder para poder usarlo
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    //Método en cargado de agregar y autenticar usuarios y personalizarlos (AUTENTICACIÓN)
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                //Se crea usuario admin
                .withUser("admin")
                    //noop-> para no encriptar
                    .password("{noop}123")
                    //se le asignan los roles al usuario
                    .roles("ADMIN", "USER")
                .and()
                //Se crea usuario user
                .withUser("user")
                    .password("{noop}123")
                    .roles("USER")
                ;
    }*/
    
    //Método encargado de restringir o configurar las URL de la aplicación de acuerdo al rol asignado (AUTORIZACIÓN)
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                //Confgurar para el rol ADMIN todas las rutas
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                    .hasRole("ADMIN")
                //Confgurar para el rol USER y ADMIN solamente la ruta inicial
                .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    //Configurar la página de inicio
                    .formLogin()
                    .loginPage("/login")
                .and()
                    //Configurar la página de errores, acceso denegado a la página
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}