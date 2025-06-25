<%-- 
    Document   : menu_principal
    Created on : 12 may. 2025, 14:30:03
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <title>Menu Principal</title>
    </head>
    <body class="cuerpo">
        <div class="container">
            <fieldset>
                <legend>Datos del Usuario</legend>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label"><strong>Nombre:</strong></label>
                        <input name="nombre_empl" class="form-control-plaintext" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label"><strong>Apellidos:</strong></label>
                        <input name="ape_empl" class="form-control-plaintext" readonly>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label"><strong>DNI:</strong></label>
                        <input name="dni_empl" class="form-control-plaintext" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label"><strong>Correo:</strong></label>
                        <input name="correo_empl" class="form-control-plaintext" readonly>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label"><strong>Dirección:</strong></label>
                        <input name="dir_empl" class="form-control-plaintext" readonly>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label"><strong>Teléfono</strong></label>
                        <input name="tel_empl" class="form-control-plaintext" readonly>
                    </div>
                </div>
            </fieldset>
        </div>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
    </body>
</html>
