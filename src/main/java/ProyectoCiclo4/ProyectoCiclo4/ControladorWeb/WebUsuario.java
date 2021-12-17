/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoCiclo4.ProyectoCiclo4.ControladorWeb;

import ProyectoCiclo4.ProyectoCiclo4.Modelo.Usuario;
import ProyectoCiclo4.ProyectoCiclo4.Servicios.ServiciosUsuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 57300
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*",methods={RequestMethod.GET,RequestMethod.POST,
                                  RequestMethod.PUT,RequestMethod.DELETE})
public class WebUsuario {
    @Autowired
    public ServiciosUsuarios servicios;
    
    @GetMapping("/all")
    public List<Usuario> getUsuarios(){
        return servicios.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario nuevoUsuario(@RequestBody Usuario usuario){
        return servicios.save(usuario);
    }
    
    @GetMapping("/{email}")
    public boolean validarPorEmail(@PathVariable String email){
        return (servicios.validarUsuarioByEmail(email)!=null)?true:false;
    }
    
    @GetMapping("/{email}/{password}")
    public Usuario validarUsuario(@PathVariable String email,
                                    @PathVariable String password){
        Usuario respuesta= servicios.validarUsuario(email, password);
        if (respuesta.getId()!=null){
            return respuesta;
            
        }else{
            respuesta.setEmail(email);
            respuesta.setPassword(password);
            respuesta.setName("NO DEFINIDO");
            return respuesta;
        }
            
    }
    
    
    
    
}
