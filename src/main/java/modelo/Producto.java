
package modelo;

/**
 *
 * @author TIVE
 */
public class Producto {
    private Integer id_producto;
    private String nombre;
    private Double precio_compra;
    private Double precio_venta;
    private String fecha_fabricacion;
    private String fecha_vencimiento;
    private Integer stock;
    private String marca;
    private String presentacion;
    private String restriccion;
    private Integer estado;
    private String n_categoria;
    private String n_proveedor;

    public Producto(){
        
    }
    
    public Producto(Integer id_producto, String nombre, Double precio_compra, 
            Double precio_venta, String fecha_fabricacion, String fecha_vencimiento, 
            Integer stock, String marca, String presentacion, String restriccion, 
            Integer estado, String n_categoria, String n_proveedor) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.fecha_fabricacion = fecha_fabricacion;
        this.fecha_vencimiento = fecha_vencimiento;
        this.stock = stock;
        this.marca = marca;
        this.presentacion = presentacion;
        this.restriccion = restriccion;
        this.estado = estado;
        this.n_categoria = n_categoria;
        this.n_proveedor = n_proveedor;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getN_categoria() {
        return n_categoria;
    }

    public void setN_categoria(String n_categoria) {
        this.n_categoria = n_categoria;
    }

    public String getN_proveedor() {
        return n_proveedor;
    }

    public void setN_proveedor(String n_proveedor) {
        this.n_proveedor = n_proveedor;
    }       
}
