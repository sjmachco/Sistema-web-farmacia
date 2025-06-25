<%-- 
    Document   : registrar_categoria
    Created on : 6 nov. 2024, 13:59:49
    Author     : TIVITO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" type = "text/css" href="${pageContext.request.contextPath}/css/estilos.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://unpkg.com/htmx.org@2.0.4/dist/htmx.js" 
                integrity="sha384-oeUn82QNXPuVkGCkcrInrS1twIxKhkZiFfr2TdiuObZ3n3yIeMiqcRzkIcguaof1" 
        crossorigin="anonymous"></script>
        <title>Categorias</title>
    </head>
    <body>
        <div class="contenedor-flex">
            <div class="buscador d-flex">
                <input class="form-control me-2" id="buscar_c" type="text" placeholder="Buscar" aria-label="Search">
            </div>
            <div class="registrar">
                <form id="formRegistrarCat" class="row g-3" autocomplete = "off">
                    <div class="col-12" style="width: 85%">
                        <input type="hidden" class="form-control codigo" id="id_cat" name="id_categoria" value="0">
                    </div>
                    <div class="col-md-6">
                        <label for="nombre_cat" class="form-label">Categoría</label>
                        <input type="text" class="form-control" id="nombre_cat" name="categoria" required>
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-4">
                            <label>Estado</label> 
                        </div>
                        <div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="1" required>
                                <label class="form-check-label" for="inlineRadio1">Activo</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="0" required>
                                <label class="form-check-label" for="inlineRadio2">Inactivo</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                    <div class="col-md-6">
                        <button type="reset" class="btn btn-primary">Limpiar</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="formato_listas">
            <table id="lista_cat" class="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Categoría</th>
                        <th scope="col">Estado</th>
                        <th style="text-align: center; vertical-align: middle;" colspan="2" scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>     
                </tbody>
            </table>
        </div>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
    </body>
</html>
