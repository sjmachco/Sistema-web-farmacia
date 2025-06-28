/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author TIVITO
 */
public class Movimiento {

    private Integer id_movimiento;
    private String producto;
    private Integer cantidad;
    private String tipo_movimiento;
    private String user_name;
    private String fecha_hora;
    private Double precio_unitario;
    private Double precio_total;

    public Movimiento() {

    }

    public Movimiento(Integer id_movimiento, String producto, Integer cantidad,
            String tipo_movimiento, String user_name, String fecha_hora, 
            Double precio_unitario, Double precio_total) {
        this.id_movimiento = id_movimiento;
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipo_movimiento = tipo_movimiento;
        this.user_name = user_name;
        this.fecha_hora = fecha_hora;
        this.precio_unitario = precio_unitario;
        this.precio_total = precio_total;
    }

    public Integer getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Integer id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public Double getTotal() {
        return precio_total;
    }

    public void setTotal(Double precio_total) {
        this.precio_total = precio_total;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    @Override
    public String toString() {
        return "Movimiento{id movimiento: " + id_movimiento + ", Cantidad: " + cantidad + "producto: " + producto + "tipo movimiento: " + tipo_movimiento + "}";
    }
}
