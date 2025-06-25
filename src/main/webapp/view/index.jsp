<%-- 
    Document   : index
    Created on : 23 abr. 2023, 11:57:00
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Modelo_producto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Farmacia</title>
        <link rel="stylesheet" type = "text/css" href="estilos.css"/>
        <% List<Modelo_producto> list = (List<Modelo_producto>)request.getAttribute("lista");%>
    </head>
    <center>
    <body style="background-color:#DC7633; font-family:Arial">
        <h1>Medicamentos</h1>
        <a href = "Producto_controller?enlace=nuevo_producto" style="font-size:120%">Nuevo producto</a>
        <p></p>
        <table border = "1" style="background-color:white">
            <thead style="font-size:120%">
                <tr style="color:white; background-color:#800000">    
                    <th width = "200">Código producto</th>
                    <th width = "300">Nombre</th>
                    <th width = "150">Precio</th>
                    <th width = "150">Stock</th>
                    <th width = "250">Solicita</th>
                    <th width = "250">Solicita</th>
                </tr>
            </thead>
            <tbody style="font-size:115%">
                <%
                    if(list != null){
                        for(Modelo_producto producto : list){
                %>
                    <tr>
                        <td><%=producto.getCodigo()%></td>
                        <td><%=producto.getNombre()%></td>
                        <td><%=producto.getPrecio()%></td>
                        <td><%=producto.getStock()%></td>
                        <td><a href = "Producto_controller?enlace=modificar_producto&id=<%producto.getId();%>">Modificar producto</a></td>
                        <td><a href = "Producto_controller?enlace=eliminar&id=<%producto.getId();%>">Eliminar producto</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
            </tbody>
        </table>
    </body>
    </center>
</html>
