package col.com.study.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//Creamos clase e implementamos WebMvcConfigurer para el tema de poner nuestra app en otros idiomas
//@Configuration -> Indica que la clase sobre la que se encuentra aplicada debe ser usada como parte de la configuración de Sprin
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    //Creamos un objeto de LocaleResolver
    //@Bean -> Indica que el elemento sobre el que se encuentra aplicada define un bean.
    @Bean
    public LocaleResolver localeResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
    //Setearle el parametro para identificar el idioma
    @Bean
    public LocaleChangeInterceptor localChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    //Sobrescribimos el método para llamar el método localChangeInterceptor
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localChangeInterceptor());
    }
    
    //Sobrescribimos el método para descrbir los path a utilizar
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
            
}
