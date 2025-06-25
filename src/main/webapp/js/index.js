/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    let tipo_usuario = $('#t_usuario').val();
    if (tipo_usuario !== 'admin') {
        $('#permission').addClass('d-none');
        $('#regisus-tab-pane').addClass('d-none'); 
    }
});


function cargarContenido(id, url, params) {
    if (params) {
        url += "?" + params;
    } else {
        url += "?timestamp=" + new Date().getTime();
    }

    fetch(url, {method: 'GET'})
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error en la solicitud: " + response.statusText);
                }
                return response.text(); // Obtenemos el contenido de la respuesta
            })
            .then(data => {
                //document.getElementById(id).innerHTML = data; // Actualizamos el contenido del div
                const contenedor = document.getElementById(id);
                contenedor.innerHTML = data;
                if (id === 'menu-tab-pane') {
                    menuPrincipal();
                } else if (id === 'regiscat-tab-pane') { // Condicional si es el tab 'registrar categoria'
                    listarCategorias();
                } else if (id === 'regisprod-tab-pane') {
                    cargarCategorias();
                    cargarProveedores();
                    listarProductos();
                } else if (id === 'regisprov-tab-pane') {
                    listarProveedores();
                } else if (id === 'regisus-tab-pane') {
                    listarEmpleados();
                } else if (id === 'regismov-tab-pane') {
                    cargarProductos();
                    listarMovimientos();
                }
            })
            .catch(error => {
                console.error("Hubo un problema con la solicitud:", error);
            });
}


function validarCategoria() {
    $('#formRegistrarCat').off('submit').on('submit', function (event) {
        event.preventDefault();
        let id_categoria = $('#id_cat').val();
        let nombreCategoria = $('#nombre_cat').val();
        //var estado = $('input[name="estado"]:checked').val();
        let estado = $('#estado_ch').prop('checked');
        if (estado)
            estado = 1;
        else
            estado = 0;
        $.ajax({
            type: 'POST',
            url: 'Categoria_controller',
            dataType: 'json',
            data: $.param({
                nombreCategoria: nombreCategoria,
                estado: estado,
                id_categoria: id_categoria
            }),
            success: function (data) {
                if (data.existe === 'true') {
                    alert('La categoría ya existe.');
                } else {
                    alert(data.hecho);
                    $('#formRegistrarCat')[0].reset();
                    listarCategorias();
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function validarProveedor() {
    $('#formRegistrarPr').off('submit').on('submit', function (event) {
        event.preventDefault();
        let id_proveedor = $('#id_prov').val();
        let nombre = $('#nombre-prov').val();
        let ruc = $('#ruc').val();
        let telefono = $('#tel').val();
        let direccion = $('#dir').val();
        let pais = $('#pais').val();
        let estado = $('#estado_ch').prop('checked');
        if (estado)
            estado = 1;
        else
            estado = 0;
        $.ajax({
            type: 'POST',
            url: 'Proveedor_controller',
            dataType: 'json',
            data: $.param({
                id_proveedor: id_proveedor,
                nombre: nombre,
                ruc: ruc,
                telefono: telefono,
                direccion: direccion,
                pais: pais,
                estado: estado
            }),
            success: function (data) {
                if (data.existe === 'true') {
                    alert('El proveedor ya existe.');
                } else {
                    alert(data.hecho);
                    $('#formRegistrarPr')[0].reset();
                    listarProveedores();
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function validarProducto() {
    $('#formRegistrarPd').off('submit').on('submit', function (event) {
        event.preventDefault();
        let id_prod = $('#id_prod').val();
        let nombre = $('#nombre_prod').val();
        let marca = $('#marca').val();
        let stock = $('#stock').val();
        let estado = $('input[name="estado"]:checked').val();
        let select_cat = $('#select_cat').val();
        let select_prov = $('#select_prov').val();
        let presentacion = $('#select_pres').val();
        let restriccion = $('#select_res').val();
        let precio_venta = $('#pr_vent').val();
        let precio_compra = $('#pr_comp').val();
        let fecha_fa = $('#fech_fa').val();
        let fecha_ven = $('#fech_ven').val();
        $.ajax({
            type: 'POST',
            url: 'Producto_controller',
            dataType: 'json',
            data: $.param({
                id_prod: id_prod,
                nombre: nombre,
                marca: marca,
                stock: stock,
                estado: estado,
                select_cat: select_cat,
                select_prov: select_prov,
                presentacion: presentacion,
                restriccion: restriccion,
                precio_venta: precio_venta,
                precio_compra: precio_compra,
                fecha_fa: fecha_fa,
                fecha_ven: fecha_ven
            }),
            success: function (data) {
                if (data.existe === 'true') {
                    alert('El producto ya existe.');
                    $('#formRegistrarPd')[0].reset();
                } else {
                    alert(data.hecho);
                    $('#formRegistrarPd')[0].reset();
                    listarProductos();
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function validarEmpleado() {
    $('#formRegistrarEmp').off('submit').on('submit', function (event) {
        event.preventDefault();
        let id_empleado = $('#id_empl').val();
        let nombre = $('#nombres_empl').val();
        let apellidos = $('#ape').val();
        let dni = $('#dni').val();
        let tel = $('#tel').val();
        let direc = $('#direc').val();
        let correo = $('#correo').val();
        let sueldo = $('#sueldo').val();
        let sexo = $('input[name="sexo"]:checked').val();
        let estado = $('input[name="estado"]:checked').val();
        let usuario = $('#usuario').val();
        let pass = $('#pass').val();
        let r_pass = $('#repe').val();
        if (pass === r_pass) {
            $.ajax({
                type: 'POST',
                url: 'Empleado_controller',
                dataType: 'json',
                data: $.param({
                    id: id_empleado,
                    nombre: nombre,
                    apellidos: apellidos,
                    dni: dni,
                    telefono: tel,
                    direccion: direc,
                    correo: correo,
                    sueldo: sueldo,
                    sexo: sexo,
                    estado: estado,
                    usuario: usuario,
                    pass: pass
                }),
                success: function (data) {
                    if (data.existe_dni !== 'true') {
                        if (data.existe_us !== 'true') {
                            alert(data.hecho);
                            $('#formRegistrarEmp')[0].reset();
                            listarEmpleados();
                        } else {
                            alert('Este usuario ya existe.');
                        }
                    } else {
                        alert('Este dni ya existe.');
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        } else {
            alert('Las contraseñas no coinciden.');
        }
    });
}

function validarMovimiento() {
    $('#formRegistrarMo').off('submit').on('submit', function (event) {
        event.preventDefault();
        let id_movimiento = $('#id_mov').val();
        let select_prod = $('#select_prod').val();
        let cantidad = $('#cantidad').val();
        let t_mov = $('input[name="tipo_m"]:checked').val();
        $.ajax({
            type: 'POST',
            url: 'Movimiento_controller',
            dataType: 'json',
            data: $.param({
                id_movimiento: id_movimiento,
                select_prod: select_prod,
                cantidad: cantidad,
                t_mov: t_mov
            }),
            success: function (data) {
                alert(data.hecho);
                $('#formRegistrarMo')[0].reset();
                listarMovimientos();
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}


/*$(document).ready(function () {
 $('#formRegistrarLo').off('submit').on('submit', function (event) {
 event.preventDefault();
 let user_name = $('#usuario').val();
 let pass = $('#pass').val();
 $.ajax({
 type: 'POST',
 url: 'Login_controller',
 data: $.param({
 user_name: user_name,
 pass: pass
 }),
 success: function (data) {
 if(data.hecho === 'true')
 window.location.href = data.ruta;
 else
 alert(data.incorrecto);
 },
 error: function (error) {
 console.log(error);
 }
 });
 });
 }),*/

function edit_cat() {
    $(".btn-edit").click(function () {
        document.getElementById("formRegistrarCat").scrollIntoView({
            behavior: "smooth",
            block: "start"
        });
        /*alert("boton editar");
         let fil = this.parentElement.parentElement.parentElement;
         let td = fil.getElementsByTagName("td");
         console.log(td[1].innerText);
         $("input[name=categoria]").val(td[1].innerText);
         $("input[name=estado]").checked(td[2].innerText);*/
        let id = $(this).attr('data-id');
        $.ajax({
            type: 'GET',
            url: 'Categoria_controller?id=' + id,
            success: function (data) {
                $('input[name=id_categoria]').val(data.id_categoria);
                $('input[name=categoria]').val(data.nombre);
                $('input[name=estado][value="' + data.estado + '"]').prop('checked', true);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function delete_cat() {
    $(".btn-delete").click(function () {
        let fila = this.parentElement.parentElement;
        let id_cat = $(this).attr('data-id');
        if (confirm("¿Seguro que desea eliminar esta categoria?")) {
            $.ajax({
                type: "DELETE",
                url: "Categoria_controller?id=" + id_cat,
                success: function (data) {
                    if (data === 'true') {
                        $(fila).addClass('animate__animated animate__backOutRight');
                        setTimeout(function () {
                            fila.remove();
                        }, 800);
                    } else
                        alert('Error en la eliminacion');
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });
}

function edit_prov() {
    $('.btn-edit').click(function () {
        document.getElementById("formRegistrarPr").scrollIntoView({
            behavior: "smooth",
            block: "start"
        });
        let id = $(this).attr("data-id");
        $.ajax({
            type: 'GET',
            url: 'Proveedor_controller?id=' + id,
            success: function (data) {
                $('input[name=id_proveedor]').val(data.id_proveedor);
                $('input[name=nombre_prov]').val(data.nombre);
                $('input[name=ruc]').val(data.ruc);
                $('input[name=tel]').val(data.telefono);
                $('input[name=dir]').val(data.direccion);
                $('input[name=pais]').val(data.pais);
                $('input[name=estado][value="' + data.estado + '"]').prop('checked', true);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function del_prov() {
    $('.btn-delete').click(function () {
        let fila = this.parentElement.parentElement;
        let id = $(this).attr("data-id");
        if (confirm("¿Seguro que desea eliminar este proveedor?")) {
            $.ajax({
                type: 'DELETE',
                url: 'Proveedor_controller?id=' + id,
                success: function (data) {
                    if (data === 'true') {
                        $(fila).addClass('animate__animated animate__backOutRight');
                        setTimeout(function () {
                            fila.remove();
                        }, 800);
                    } else {
                        alert('Error en la eliminacion');
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });
}

function edit_prod() {
    $('.btn-edit').click(function () {
        document.getElementById("formRegistrarPd").scrollIntoView({
            behavior: "smooth",
            block: "start"
        });
        let id_producto = $(this).attr('data-id');
        $.ajax({
            type: 'GET',
            url: 'Producto_controller?id=' + id_producto,
            success: function (data) {
                let precio_venta = data.precio_venta.toFixed(2);
                let precio_compra = data.precio_compra.toFixed(2);
                $('input[name=id_producto]').val(data.id_producto);
                $('input[name=nombre]').val(data.nombre);
                $('input[name=marca]').val(data.marca);
                $('input[name=stock]').val(data.stock);
                $('input[name=estado][value="' + data.estado + '"]').prop('checked', true);
                $('#select_cat').val(data.n_categoria);
                $('#select_prov').val(data.n_proveedor);
                $('#select_pres').val(data.presentacion);
                $('#select_res').val(data.restriccion);
                $('input[name=pr_vent]').val(precio_venta);
                $('input[name=pr_comp]').val(precio_compra);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function del_prod() {
    $('.btn-delete').click(function () {
        let fila = this.parentElement.parentElement;
        let id_producto = $(this).attr('data-id');
        if (confirm('¿Seguro que desea eliminar este producto?')) {
            $.ajax({
                type: 'DELETE',
                url: 'Producto_controller?id=' + id_producto,
                success: function (data) {
                    if (data === 'true') {
                        $(fila).addClass('animate__animated animate__backOutRight');
                        setTimeout(function () {
                            fila.remove();
                        }, 800);
                    } else {
                        alert('Error en la eliminacion.');
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });
}

function edit_empleado() {
    $('.btn-edit').click(function () {
        document.getElementById("formRegistrarEmp").scrollIntoView({
            behavior: "smooth",
            block: "start"
        });
        let id_empleado = $(this).attr('data-id');
        $.ajax({
            type: 'GET',
            url: 'Empleado_controller?id=' + id_empleado,
            success: function (data) {
                let sueldo = data.sueldo.toFixed(2);
                $('input[name=id_empleado]').val(data.id_empleado);
                $('input[name=nombres]').val(data.nombres);
                $('input[name=ape]').val(data.apellidos);
                $('input[name=dni]').val(data.dni).prop('disabled', true);
                $('input[name=tel]').val(data.telefono);
                $('input[name=direc]').val(data.direccion);
                $('input[name=correo]').val(data.correo);
                $('input[name=sueldo]').val(sueldo);
                $('input[name=sexo][value="' + data.sexo + '"]').prop('checked', true);
                $('input[name=estado][value="' + data.estado + '"]').prop('checked', true);
                $('input[name=usuario]').val(data.usuario).prop('disabled', true);
                $('input[name=pass]').val(data.clave);
                $('input[name=rep_pass]').val(data.clave);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

function del_empleado() {
    $('.btn-delete').click(function () {
        let fila = this.parentElement.parentElement;
        let id_empleado = $(this).attr('data-id');
        if (confirm('¿Seguro que desea eliminar este empleado?')) {
            $.ajax({
                type: 'DELETE',
                url: 'Empleado_controller?id=' + id_empleado,
                success: function (data) {
                    if (data === 'true') {
                        $(fila).addClass('animate__animated animate__backOutRight');
                        setTimeout(function () {
                            fila.remove();
                        }, 800);
                    } else {
                        alert('Error en la eliminacion.');
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    });
}

function edit_movimiento() {
    $('.btn-edit').click(function () {
        let id_movimiento = $(this).attr('data-id');
        $.ajax({
            type: 'GET',
            url: 'Movimiento_controller?id=' + id_movimiento,
            success: function () {

            },
            error: function () {
                console.log(error);
            }
        });
    });
}

function cargarCategorias() {
    let select_cat = $('.categorias');
    $.ajax({
        method: 'GET',
        url: 'Categoria_controller?action=getListCat',
        success: function (data) {
            data.forEach(lista => {
                select_cat.append(`
                        <option value="${lista.id_categoria}">${lista.nombre}</option>
                `);
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function cargarProveedores() {
    let select_prov = $('.proveedores');
    $.ajax({
        method: 'GET',
        url: 'Proveedor_controller?action=getListProv',
        success: function (data) {
            data.forEach(lista => {
                select_prov.append(`
                    <option value="${lista.id_proveedor}">${lista.nombre}</option>
                `);
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function cargarProductos() {
    let select_prod = $('.productos');
    $.ajax({
        type: 'GET',
        url: 'Producto_controller?action=getListProd',
        success: function (data) {
            data.forEach(lista => {
                select_prod.append(`
                    <option value="${lista.id_producto}">${lista.nombre}</option>
                `);
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function listarCategorias() {
    $.ajax({
        type: 'GET',
        url: 'Categoria_controller?action=getListCat',
        success: function (data) {
            const categorias = data.map(cate => ({
                    ...cate,
                    estado: cate.estado === 1 ? "activo" : "inactivo"
                }));
            const tabla_categorias = $('#lista_cat tbody');
            tabla_categorias.empty();
            categorias.forEach(lista => {
                tabla_categorias.append(`
                    <tr>
                        <td>${lista.id_categoria}</td>
                        <td>${lista.nombre}</td>
                        <td>${lista.estado}</td>
                        <td style="text-align: center">
                            <button class="btn btn-warning btn-edit" data-id="${lista.id_categoria}">Editar</button>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-danger btn-delete" data-id="${lista.id_categoria}">Eliminar</button>
                        </td>
                    </tr>
                `);
            });
            edit_cat();
            delete_cat();
            buscarCategoria();
        },
        error: function (error) {
            console.log(error);
        }
    });
    validarCategoria();
}

function listarProveedores() {
    $.ajax({
        type: 'GET',
        url: 'Proveedor_controller?action=getListProv',
        success: function (data) {
            const proveedores = data.map(prov => ({
                    ...prov,
                    estado: prov.estado === 1 ? "activo" : "inactivo"
                }));
            let tabla_proveedores = $('#lista_proov tbody');
            tabla_proveedores.empty();
            proveedores.forEach(lista => {
                tabla_proveedores.append(`
                    <tr>
                        <td>${lista.id_proveedor}</td>
                        <td>${lista.nombre}</td>
                        <td>${lista.ruc}</td>
                        <td>${lista.telefono}</td>
                        <td>${lista.direccion}</td>
                        <td>${lista.pais}</td>
                        <td>${lista.estado}</td>
                        <td style="text-align: center">
                            <button class="btn btn-warning btn-edit" data-id="${lista.id_proveedor}">Editar</button>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-danger btn-delete" data-id="${lista.id_proveedor}">Eliminar</button>
                        </td>
                    </tr>
                    `);
            });
            edit_prov();
            del_prov();
            buscarProveedor();
        },
        error: function (error) {
            console.log(error);
        }
    });
    validarProveedor();
}

function listarProductos() {
    $.ajax({
        type: 'GET',
        url: 'Producto_controller?action=getListProd',
        success: function (data) {
            const productos = data.map(prod => ({
                    ...prod,
                    estado: prod.estado === 1 ? "activo" : "inactivo"
                }));
            const tabla_productos = $('#lista_prod tbody');
            tabla_productos.empty();
            productos.forEach(lista => {
                let p_venta = lista.precio_venta.toFixed(2);
                let p_compra = lista.precio_compra.toFixed(2);
                tabla_productos.append(`
                    <tr>
                        <td>${lista.id_producto}</td>
                        <td>${lista.nombre}</td>
                        <td>${lista.n_categoria}</td>
                        <td>${lista.n_proveedor}</td>
                        <td>S/${p_venta}</td>
                        <td>S/${p_compra}</td>
                        <td>${lista.fecha_fabricacion}</td>
                        <td>${lista.fecha_vencimiento}</td>
                        <td>${lista.stock}</td>
                        <td>${lista.marca}</td>
                        <td>${lista.presentacion}</td>
                        <td>${lista.restriccion}</td>
                        <td>${lista.estado}</td>
                        <td style="text-align: center">
                            <button class="btn btn-warning btn-edit" data-id="${lista.id_producto}">Editar</button>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-danger btn-delete" data-id="${lista.id_producto}">Eliminar</button>
                        </td>
                    </tr> 
                `);
            });
            edit_prod();
            del_prod();
            buscarProducto();
        },
        error: function (error) {
            console.log(error);
        }
    });
    validarProducto();
}

function listarEmpleados() {
    $.ajax({
        type: 'GET',
        url: 'Empleado_controller?action=list_empl',
        success: function (data) {
            const empleados = data.map(empl => ({
                    ...empl,
                    estado: empl.estado === 1 ? 'activo' : 'inactivo'
                }));
            const tabla_empleados = $('#lista_empl tbody');
            tabla_empleados.empty();
            empleados.forEach(lista => {
                let sueldo = lista.sueldo.toFixed(2);
                tabla_empleados.append(`
                    <tr>
                        <td>${lista.id_empleado}</td>
                        <td>${lista.nombres}</td>
                        <td>${lista.apellidos}</td>
                        <td>${lista.direccion}</td>
                        <td>${lista.dni}</td>
                        <td>${lista.telefono}</td>
                        <td>${lista.sexo}</td>
                        <td>${lista.correo}</td>
                        <td>${sueldo}</td>
                        <td>${lista.usuario}</td>
                        <td>${lista.clave}</td>
                        <td>${lista.estado}</td>
                        <td style="text-align: center">
                            <button class="btn btn-warning btn-edit" data-id="${lista.id_empleado}">Editar</button>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-danger btn-delete" data-id="${lista.id_empleado}">Eliminar</button>
                        </td>
                    </tr>
               `);
            });
            edit_empleado();
            del_empleado();
            buscarEmpleado();
        },
        error: function (error) {
            console.log(error);
        }
    });
    validarEmpleado();
}

function listarMovimientos() {
    $.ajax({
        type: 'GET',
        url: 'Movimiento_controller?action=getListMov',
        success: function (data) {
            const tabla_mov = $('#lista_mov tbody');
            tabla_mov.empty();
            data.forEach(lista => {
                let p_unitario = lista.precio_unitario.toFixed(2);
                let p_total = lista.precio_total.toFixed(2);
                tabla_mov.append(`
                    <tr>
                        <td>${lista.id_movimiento}</td>
                        <td>${lista.producto}</td>
                        <td>${lista.cantidad}</td>
                        <td>${lista.tipo_movimiento}</td>
                        <td>${lista.user_name}</td>
                        <td>${lista.fecha_hora}</td>
                        <td>S/${p_unitario}</td>
                        <td>S/${p_total}</td>
                        <td style="text-align: center">
                            <button class="btn btn-warning btn-edit" data-id="${lista.id_movimiento}">Editar</button>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-danger btn-delete" data-id="${lista.id_movimiento}">Eliminar</button>
                        </td>
                    </tr>
                `);
            });
            buscarMovimiento();
        },
        error: function (error) {
            console.log(error);
        }
    });
    validarMovimiento();
}

function menuPrincipal() {
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: 'Empleado_controller?action=datos_empl',
        success: function (data) {
            $('input[name=nombre_empl]').val(data.nombres);
            $('input[name=ape_empl]').val(data.apellidos);
            $('input[name=dni_empl]').val(data.dni);
            $('input[name=dir_empl]').val(data.direccion);
            $('input[name=tel_empl]').val(data.telefono);
            $('input[name=correo_empl]').val(data.correo);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function buscarEmpleado() {
    document.getElementById('buscar_e').addEventListener('input', function () {
        const filas = document.querySelectorAll('#lista_empl tbody tr');
        const valor = this.value.toLowerCase();
        filas.forEach(fila => {
            const texto = fila.textContent.toLowerCase();
            fila.style.display = texto.includes(valor) ? "" : "none";
        });
    });
}

function buscarProducto() {
    document.getElementById('buscar_p').addEventListener('input', function () {
        const filas = document.querySelectorAll('#lista_prod tbody tr');
        const valor = this.value.toLowerCase();
        filas.forEach(fila => {
            const texto = fila.textContent.toLowerCase();
            fila.style.display = texto.includes(valor) ? "" : "none";
        });
    });
}

function buscarCategoria() {
    document.getElementById('buscar_c').addEventListener('input', function () {
        const filas = document.querySelectorAll('#lista_cat tbody tr');
        const valor = this.value.toLowerCase();
        filas.forEach(fila => {
            const texto = fila.textContent.toLowerCase();
            fila.style.display = texto.includes(valor) ? "" : "none";
        });
    });
}

function buscarProveedor() {
    document.getElementById('buscar_pr').addEventListener('input', function () {
        const filas = document.querySelectorAll('#lista_proov tbody tr');
        const valor = this.value.toLowerCase();
        filas.forEach(fila => {
            const texto = fila.textContent.toLowerCase();
            fila.style.display = texto.includes(valor) ? "" : "none";
        });
    });
}

function buscarMovimiento() {
    document.getElementById('buscar_m').addEventListener('input', function () {
        const filas = document.querySelectorAll('#lista_mov tbody tr');
        const valor = this.value.toLowerCase();
        filas.forEach(fila => {
            const texto = fila.textContent.toLowerCase();
            fila.style.display = texto.includes(valor) ? "" : "none";
        });
    });
}



