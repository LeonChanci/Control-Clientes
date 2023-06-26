package col.com.study.web;

import col.com.study.domain.Persona;
import col.com.study.servicio.PersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Se usa solamente controlador cuando usaremos Spring MVC
@Controller

//Se usa @Slf4j para el control del log en consola
@Slf4j
public class ControladorInicio {
    
    //Inyectar cualquier el objeto administrado por el contenedor
    @Autowired
    //Inyectar la clase PersonaService en el controlador
    private PersonaService personaService;
    
    //Se usa para mapear por medio del Get la página a mostrar al subir el server http://localhost:7070/
    //Los métodos con la anotación @GetMapping pueden recibir diferentes parametros como por ejemplo Model
    @GetMapping("/")
    //Para pasar información del controlador al index.thml se usa la interfaz Model
    //@AuthenticationPrincipal -> Indica que el parámetro de tipo Principal se inyectará con la información del usuario que hace la petición contra el servicio. 
    public String inicio(Model model, @AuthenticationPrincipal User user){

        //Recuperar los datos de la tabla Persona desde la clase Services
        var personas = personaService.listarPersonas();
        
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo Login:"+ user);
        
        //Añadimos la variable personas al modelo
        model.addAttribute("personas", personas);
        
        //Retornar el nombre del Template index con Thymeleaf
        return "index";
    }
    
    //Cuando se llame la ruta /agregar ejecuta el método agregar
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        //Retornar la vista modificar
        return "modificar";
    }
    
    //Cuando se llame la ruta /guardar ejecuta el método guardar
    @PostMapping("/guardar")
    //@Valid -> Etiqueda de valid.javax para validar al momento de guardar
    //Errors -> parámetro de spring para control de errores
    public String guardar(@Valid Persona persona, Errors errores){
         if(errores.hasErrors()){
            //Retornar la vista modificar
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    //Cuando se llame la ruta /editar ejecuta el método editar
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        
        //Le pasamos el objeto persona al modelo -> a la vista modificar
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    //Cuando se llame la ruta /eliminar ejecuta el método eliminar
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
