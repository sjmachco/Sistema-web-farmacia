<%-- 
    Document   : registrar_movimiento
    Created on : 29 abr. 2025, 19:04:51
    Author     : TIVITO
--%>

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
        <title>Movimientos</title>
    </head>
    <body>
        <div class="contenedor-flex">
            <div class="buscador d-flex">
                <input class="form-control me-2" id="buscar_m" type="text" placeholder="Buscar" aria-label="Search">
            </div>
            <div class="registrar">
                <form id="formRegistrarMo" class="row g-3" autocomplete = "off">
                    <div class="col-12" style="width: 85%">
                        <input type="hidden" class="form-control codigo" id="id_mov" name="id_movimiento" value="0">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Producto</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-select productos" aria-label="Default select example" name="opt_prod" 
                                id="select_prod">
                            <option selected>Elegir...</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="cantidad" class="form-label">Cantidad</label>
                        <input type="number" class="form-control" id="cantidad" name="cantidad" min="1" required>
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-6">
                            <label>Tipo movimiento</label> 
                        </div>
                        <div>
                            <div>
                                <input class="form-check-input" type="radio" name="tipo_m" id="tipo_mov" value="compra" required>
                                <label class="form-check-label" for="inlineRadio1">Compra</label>
                            </div>
                            <div>
                                <input class="form-check-input" type="radio" name="tipo_m" id="tipo_mov" value="venta" required>
                                <label class="form-check-label" for="inlineRadio2">Venta</label>
                            </div>
                        </div>    
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                    <div class="col-md-6">
                        <button type="reset" class="btn btn-primary limpiar">Limpiar</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="formato_listas">
            <table id="lista_mov" class="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">T. movimiento</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Fecha/hora</th>
                        <th scope="col">P.Unt</th>
                        <th scope="col">Total</th>
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
