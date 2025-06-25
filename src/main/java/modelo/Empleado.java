/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author TIVITO
 */
public class Empleado {

    private Integer id_empleado;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String dni;
    private String telefono;
    private String sexo;
    private String correo;
    private Double sueldo;
    private Integer estado;
    private String usuario;
    private String clave;

    public Empleado() {

    }

    public Empleado(Integer id_empleado, String nombres, String apellidos, String direccion, 
            String dni, String telefono, String sexo, String correo, Double sueldo, 
                Integer estado, String usuario, String clave) {
        this.id_empleado = id_empleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
        this.sexo = sexo;
        this.correo = correo;
        this.sueldo = sueldo;
        this.estado = estado;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
