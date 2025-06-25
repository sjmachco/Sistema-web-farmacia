<%-- 
    Document   : registrar_usuario
    Created on : 5 nov. 2024, 23:28:09
    Author     : TIVITO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%>
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
        <title>Empleados</title>
    </head>
    <body>
        <div class="contenedor-flex">
            <div class="buscador d-flex">
                <input class="form-control me-2" id="buscar_e" type="text" placeholder="Buscar" aria-label="Search">
            </div>
            <div class="registrar">
                <form id="formRegistrarEmp" class="row g-3" autocomplete="off">
                    <div class="col-12" style="width: 85%">
                        <input type="hidden" class="form-control codigo" id="id_empl" name="id_empleado" value="0">
                    </div>
                    <div class="col-md-6">
                        <label for="nombres" class="form-label">Nombres</label>
                        <input type="text" class="form-control" id="nombres_empl" name="nombres" required>
                    </div>
                    <div class="col-md-6">
                        <label for="ape" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="ape" name="ape" required>
                    </div>
                    <div class="col-md-6">
                        <label for="dni" class="form-label">DNI</label>
                        <input type="text" class="form-control" id="dni" name="dni" required>
                    </div>
                    <div class="col-md-6">
                        <label for="tel" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" id="tel" name="tel" required>
                    </div>
                    <div class="col-12">
                        <label for="direc" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direc" name="direc" required>
                    </div>
                    <label for="correo">Correo</label>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" id="correo" name="correo" required>
                        <span class="input-group-text form-control" id="basic-addon2">@example.com</span>
                    </div>
                    <div class="col-md-6">
                        <label for="sueldo" class="form-label">Sueldo</label>
                        <input type="number" class="form-control" id="sueldo" name="sueldo" required>
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-4">
                            <label>Sexo</label> 
                        </div>
                        <div>
                            <!--<div class="form-check form-check-inline">-->
                            <div>
                                <input class="form-check-input" type="radio" name="sexo" id="sexo_ch" value="m">
                                <label class="form-check-label" for="inlineRadio1">Masculino</label>
                            </div>
                            <!--<div class="form-check form-check-inline"> inlineRadioOptions-->
                            <div>
                                <input class="form-check-input" type="radio" name="sexo" id="sexo_ch" value="f">
                                <label class="form-check-label" for="inlineRadio2">Femenino</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label>Estado</label> 
                    </div>
                    <div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="1">
                            <label class="form-check-label" for="inlineRadio1">Activo</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="0">
                            <label class="form-check-label" for="inlineRadio2">Inactivo</label>
                        </div>
                    </div>
                    <div class="col-12">
                        <label for="usuario" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" required>
                    </div>
                    <div class="col-md-6">
                        <label for="pass" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="pass" name="pass" required>
                    </div>
                    <div class="col-md-6">
                        <label for="repe" class="form-label">Repetir contraseña</label>
                        <input type="password" class="form-control" id="repe" name="rep_pass" required>
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
            <table id="lista_empl" class="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellidos</th>                            
                        <th scope="col">Direccion</th>
                        <th scope="col">DNI</th>
                        <th scope="col">Telefóno</th>
                        <th scope="col">Sexo</th>
                        <th scope="col">Correo</th>
                        <th scope="col">Sueldo</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Clave</th>
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
