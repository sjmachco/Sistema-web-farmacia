<%-- 
    Document   : menu_principal
    Created on : 5 nov. 2024, 22:38:48
    Author     : TIVITO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@page language="java" import="java.util.*"%>
<%@page session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html, 
              width=device-width, initial-scale=1.0; charset=UTF-8" 
              name="viewport">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" type = "text/css" href="${pageContext.request.contextPath}/css/estilos.css"/>
        <% 
            String usuario = (String) session.getAttribute("usuario");
        %>
        <title>Navegación</title>
    </head>
    <body>
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <input type="hidden" id="t_usuario" value="<%=usuario%>">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="menu-tab" 
                        data-bs-toggle="tab" data-bs-target="#menu-tab-pane" 
                        type="button" role="tab" aria-controls="menu-tab-pane" 
                        aria-selected="true" onclick="cargarContenido('menu-tab-pane',
                                        'Empleado_controller', 'action=mostrar_datos')"><strong>Menu principal</strong></button>
            </li>

            <li id="permission" class="nav-item" role="presentation">
                <button class="nav-link" id="regisus-tab" 
                        data-bs-toggle="tab" data-bs-target="#regisus-tab-pane" 
                        type="button" role="tab" aria-controls="regisus-tab-pane" 
                        aria-selected="false" onclick="cargarContenido('regisus-tab-pane',
                                            'Empleado_controller', 'action=regist_usuario')"><strong>Usuario</strong></button>
            </li>

            <li class="nav-item" role="presentation">
                <button class="nav-link" id="regisprod-tab" 
                        data-bs-toggle="tab" data-bs-target="#regisprod-tab-pane" 
                        type="button" role="tab" aria-controls="regisprod-tab-pane" 
                        aria-selected="false" onclick="cargarContenido('regisprod-tab-pane',
                                        'Producto_controller', 'action=regist_prod')"><strong>Producto</strong></button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="regiscat-tab" 
                        data-bs-toggle="tab" data-bs-target="#regiscat-tab-pane" 
                        type="button" role="tab" aria-controls="regiscat-tab-pane" 
                        aria-selected="false" onclick="cargarContenido('regiscat-tab-pane',
                                        'Categoria_controller', 'action=regist_cat')"><strong>Categoria</strong></button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="regisprov-tab" 
                        data-bs-toggle="tab" data-bs-target="#regisprov-tab-pane" 
                        type="button" role="tab" aria-controls="regisprov-tab-pane" 
                        aria-selected="false" onclick="cargarContenido('regisprov-tab-pane',
                                        'Proveedor_controller', 'action=regist_prov')"><strong>Proveedor</strong></button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="regismov-tab" 
                        data-bs-toggle="tab" data-bs-target="#regismov-tab-pane" 
                        type="button" role="tab" aria-controls="regismov-tab-pane" 
                        aria-selected="false" onclick="cargarContenido('regismov-tab-pane',
                                        'Movimiento_controller', 'action=regist_mov')"><strong>Movimientos</strong></button>
            </li>
            <l1 class="logout nav-item" role="presentation">
                <button class="nav-link btn-logout" id="logout-option" type="button" 
                        data-bs-target="#logout" aria-controls="logout" aria-selected="false">
                    <a href="Login_controller?action_l=logout" onclick="return confirm('¿Seguro que desea cerrar sesión?');">
                        <img src="${pageContext.request.contextPath}/imagenes/logout-img.ico"></a>
                </button>
            </l1>
        </ul>
        <div class="tab-content" id="myTabContent">         
            <div class="tab-pane fade" id="menu-tab-pane" role="tabpanel" aria-labelledby="menu-tab" tabindex="0"></div>
            <div class="tab-pane fade" id="regisus-tab-pane" role="tabpanel" aria-labelledby="regisus-tab" tabindex="0"></div>
            <div class="tab-pane fade" id="regisprod-tab-pane" role="tabpanel" aria-labelledby="regisprod-tab" tabindex="0"></div>
            <div class="tab-pane fade" id="regiscat-tab-pane" role="tabpanel" aria-labelledby="regiscat-tab" tabindex="0"></div>
            <div class="tab-pane fade" id="regisprov-tab-pane" role="tabpanel" aria-labelledby="regisprov-tab" tabindex="0"></div>
            <div class="tab-pane fade" id="regismov-tab-pane" role="tabpanel" aria-labelledby="regismov-tab" tabindex="0"></div>
            <div class="d-flex" id="logout" aria-labelledby="logout-option"></div>
        </div>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
    </body>
</html>
