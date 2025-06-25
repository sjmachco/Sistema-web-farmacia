<%-- 
    Document   : Nuevo_producto
    Created on : 23 abr. 2023, 11:59:11
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
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
        <title>Productos</title>
    </head>
    <body>
        <div class="contenedor-flex">
            <div class="buscador d-flex">
                <input class="form-control me-2" id="buscar_p" type="text" placeholder="Buscar" aria-label="Search">
            </div>
            <div class="registrar">
                <form id="formRegistrarPd" class="row g-3" autocomplete = "off">
                    <div class="col-12" style="width: 85%">
                        <input type="hidden" class="form-control codigo" id="id_prod" name="id_producto" value="0">
                    </div>
                    <div class="col-md-6">
                        <label for="nombre_prod" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre_prod" name="nombre" required>
                    </div>
                    <div class="col-md-6">
                        <label for="marca" class="form-label">Marca</label>
                        <input type="text" class="form-control" id="marca" name="marca" required>
                    </div>
                    <div class="col-md-6">
                        <label for="stock" class="form-label">Stock</label>
                        <input type="number" class="form-control" id="stock" name="stock" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Estado</label> 
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
                        <label class="form-label">Categoria</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select categorias" aria-label="Default select example" name="opt_cat" 
                                id="select_cat">
                            <option selected>Elegir...</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="nombre_prod" class="form-label">Proveedor</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select proveedores" aria-label="Default select example" name="opt_prov" 
                                id="select_prov">
                            <option selected>Elegir...</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Presentación</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select" aria-label="Default select example" name="opt_pre" id="select_pres">
                            <option selected>Elegir...</option>
                            <option value="tabletas">Tabletas</option>
                            <option value="capsulas">Capsulas</option>
                            <option value="jarabes">Jarabes</option>
                            <option value="otros">Otros</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="nombre_prod" class="form-label">Restricciones</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select" aria-label="Default select example" name="opt_res" id="select_res">
                            <option selected>Elegir...</option>
                            <option value="receta">Receta</option>
                            <option value="libre">Libre</option>
                            <option value="otros">Otros</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="pr_vent" class="form-label">Precio venta</label>
                        <input type="number" step="0.01" class="form-control" id="pr_vent" name="pr_vent" 
                               placeholder="S/" required>
                    </div>
                    <div class="col-md-6">
                        <label for="pr_comp" class="form-label">Precio compra</label>
                        <input type="number" step="0.01" class="form-control" id="pr_comp" name="pr_comp" 
                               placeholder="S/" required>
                    </div>
                    <div class="col-md-6">
                        <label for="fech_fa" class="form-label">Fecha fabricacion</label>
                        <input type="date" class="form-control" id="fech_fa" name="fech_fa" required>
                    </div>
                    <div class="col-md-6">
                        <label for="fech_ven" class="form-label">Fecha vencimiento</label>
                        <input type="date" class="form-control" id="fech_ven" name="fech_ven" required>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary" name="btnGuardar">Registrar</button>
                    </div>
                    <div class="col-md-6">
                        <button type="reset" class="btn btn-primary">Limpiar</button>
                    </div>
                    <!--<table>
                        <tr>
                            <td><button type = "submit" name = "btnGuardar">Guardar</button></td>
                            <td><input type="button" name="cancelar" value="Cancelar" onClick="location.href = 'Producto_controller?enlace=cancelar'"></td>
                        </tr>            
                    </table>-->
                </form>
            </div>
        </div>
        <div class="formato_listas">
            <table id="lista_prod" class="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Proveedor</th>
                        <th scope="col">Precio venta</th>
                        <th scope="col">Precio compra</th>
                        <th scope="col">Fecha fabricacion</th>
                        <th scope="col">Fecha vencimiento</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Presentacion</th>
                        <th scope="col">Restricciones</th>
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
