<%-- 
    Document   : registrar_proveedor
    Created on : 16 abr. 2025, 13:21:36
    Author     : TIVITO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title>Proveedores</title>
    </head>
    <body>
        <div class="contenedor-flex">
            <div class="buscador d-flex">
                <input class="form-control me-2" id="buscar_pr" type="text" placeholder="Buscar" aria-label="Search">
            </div>
            <div class="registrar">
                <form id="formRegistrarPr" class="row g-3" autocomplete = "off">
                    <div class="col-12" style="width: 85%">
                        <input type="hidden" class="form-control codigo" id="id_prov" name="id_proveedor" value="0">
                    </div>
                    <div class="col-md-6">
                        <label for="nombres" class="form-label">Nombres</label>
                        <input type="text" class="form-control" id="nombre-prov" name="nombre_prov">
                    </div>
                    <div class="col-md-6">
                        <label for="ape" class="form-label">RUC</label>
                        <input type="text" class="form-control" id="ruc" name="ruc">
                    </div>
                    <div class="col-md-6">
                        <label for="nombres" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" id="tel" name="tel">
                    </div>
                    <div class="col-md-6">
                        <label for="ape" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="dir" name="dir">
                    </div>
                    <div class="col-md-6">
                        <label for="ape" class="form-label">Paìs</label>
                        <input type="text" class="form-control" id="pais" name="pais">
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-4">
                            <label>Estado</label> 
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="1" required>
                            <label class="form-check-label" for="inlineRadio1">Activo</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="estado" id="estado_ch" value="0" required>
                            <label class="form-check-label" for="inlineRadio2">Inactivo</label>
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
            <table id="lista_proov" class="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Proveedor</th>
                        <th scope="col">RUC</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Dirección</th>
                        <th scope="col">País</th>
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
