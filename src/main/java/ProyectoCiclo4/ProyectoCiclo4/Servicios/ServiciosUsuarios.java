/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoCiclo4.ProyectoCiclo4.Servicios;

import ProyectoCiclo4.ProyectoCiclo4.Modelo.Usuario;
import ProyectoCiclo4.ProyectoCiclo4.Repositorio.RepositorioUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 57300
 */
@Service
public class ServiciosUsuarios {
    @Autowired
    private RepositorioUsuario metodosCrud;
    
    public List<Usuario> getAll(){
        return (List<Usuario>) metodosCrud.getAll();
    }
    
    public Optional<Usuario> getUsuario(int id){
        return metodosCrud.getUsuario(id);
    }
    
    public Usuario save(Usuario usuario){
        if(usuario.getId()==null){
            return metodosCrud.save(usuario);
        }else{
            Optional<Usuario> auxiliar= metodosCrud.getUsuario(usuario.getId());
            if(auxiliar.isEmpty()){
                return metodosCrud.save(usuario);
            }else{
                return usuario;
            }
        }
    }
    
    public Usuario update(Usuario usuario){
        if(usuario.getId()!=null){
            Optional<Usuario> auxiliar = metodosCrud.getUsuario(usuario.getId());
            if(auxiliar.isPresent()){
                if(usuario.getEmail()!=null){
                    auxiliar.get().setEmail(usuario.getEmail());
                }
                if(usuario.getPassword()!=null){
                    auxiliar.get().setPassword(usuario.getPassword());
                }
                if(usuario.getName()!=null){
                    auxiliar.get().setName(usuario.getName());
                }
                metodosCrud.save(auxiliar.get());
                return auxiliar.get();
            }else{
                return usuario;
            }
            
        }else{
            return usuario;
        }
    }
    
    public boolean deleteUsuario(int id) {
        Boolean prueba = getUsuario(id).map(usuario -> {
            metodosCrud.delete(usuario);
            return true;
        }).orElse(false);
        return prueba;
    }
    
    public Usuario validarUsuarioByEmail(String email){
        return metodosCrud.getUsuarioByEmail(email);
        
    }
    
    public Usuario validarUsuario(String email, String password){
        return metodosCrud.validarUsuario(email, password);
        
    }
    
}
