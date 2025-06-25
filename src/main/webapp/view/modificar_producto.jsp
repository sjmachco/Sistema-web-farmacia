<%-- 
    Document   : Configurar_productos
    Created on : 23 abr. 2023, 12:05:22
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualización</title>      
    </head>
    <center>
    <body style="background-color:#DC7633; font-family:Arial">
        <h1>Actualizar producto</h1>
        <form action = "Producto_controller?enlace=actualizar" method = "post" autocomplete = "off">
            <table style="font-size:120%">
                <input type = "hidden" name = "id" value = "<c:out value = "${productos.id}"/>"/>
                <tr>
                    <td>Código:</td>
                    <td><input id="codigo" type = "text" name = "codigo" value = "<c:out value = "${productos.codigo}"/>"/></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><input id="nombre" type = "text" name = "nombre" value = "<c:out value = "${productos.nombre}"/>"/></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input id="precio" type = "text" name = "precio" value = "<c:out value = "${productos.precio}"/>"/></td>
                </tr>
                <tr> 
                    <td>Stock</td>
                    <td><input id="stock" type = "text" name = "stock" value = "<c:out value = "${productos.stock}"/>"/></td>
                </tr>
            </table>
                <br>
                <table>
                    <td><button type = "submit" name = "btnGuardar">Guardar</button></td>
                    <td><input type="button" name="cancelar" value="Cancelar" onClick="location.href='Producto_controller?enlace=cancelar'"></td>
                </table>
        </form>
    </body>
    </center>
</html>
