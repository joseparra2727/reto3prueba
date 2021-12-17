/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoCiclo4.ProyectoCiclo4.Interface;

import ProyectoCiclo4.ProyectoCiclo4.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author 57300
 */
public interface InterfaceUsuario extends JpaRepository<Usuario, Integer>{
    
    @Query("SELECT d FROM Usuario d WHERE email=?1")
    public Usuario findByEmail(String email);
    
    @Query("SELECT d FROM Usuario d WHERE email=?1 and password=?2")
    public Usuario findUsuarioByEmailAndPassword(String email, String password);
}
