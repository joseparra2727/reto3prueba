/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoCiclo4.ProyectoCiclo4.Repositorio;

import ProyectoCiclo4.ProyectoCiclo4.Interface.InterfaceUsuario;
import ProyectoCiclo4.ProyectoCiclo4.Modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 57300
 */
@Repository
public class RepositorioUsuario{
    @Autowired
    public InterfaceUsuario crud;
    
    public List<Usuario> getAll(){
        return (List<Usuario>) crud.findAll();
    }
    
    public Optional<Usuario> getUsuario(int id){
        return crud.findById(id);
    }
    
    
    
    public Usuario save(Usuario usuario){
        return crud.save(usuario);
    }
    
    public void delete(Usuario usuario){
        crud.delete(usuario);
    }
    
    public Usuario getUsuarioByEmail(String email){
        return crud.findByEmail(email);
    }
    
    public Usuario validarUsuario(String email, String password){
        Usuario validar = crud.findUsuarioByEmailAndPassword(email, password);
        if(validar!=null){
            return validar;
        }else{
            return new Usuario();
        }
        
    }
}
