/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author TIVITO
 */
public class Login {
    private Integer id_login;
    private String user_name;
    private String clave;
    private Integer id_empleado;
    
    public Login(){
        
    }
    
    public Login(Integer id_login, String user_name, String clave, Integer id_empleado){
        this.id_login = id_login;
        this.user_name = user_name;
        this.clave = clave;
        this.id_empleado = id_empleado;
    }

    public Integer getId_login() {
        return id_login;
    }

    public void setId_login(Integer id_login) {
        this.id_login = id_login;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }
}
