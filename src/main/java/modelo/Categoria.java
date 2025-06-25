/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author TIVITO
 */
public class Categoria {
    private Integer id_categoria;
    private String nombre;
    private Integer estado;
    
    public Categoria(){
        
    }
    
    public Categoria(Integer id_categoria, String nombre, Integer estado){
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getEstado(){
        return estado;
    }
    
    public void setEstado(Integer estado){
        this.estado = estado;
    }
}
