/*  Generador de Etiquetas para Reactivos Químicos
 *
 *  Copyright (C) 2011  Carmen López Martínez (klopez@ugr.es)
 *                      Area de Asesoría y Apoyo para el Desarrollo de Aplicacones de Gestión
 *		            	CSIRC
 *		            	Universidad de Granada
 *  
 *  Version: 1.0
 *  Last Modified: 15 Mayo 2012
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
$(document).ready(function() {
    
        $("#etiqueta").submit(function(e) {
            e.preventDefault(); 
            var queryString = $(this).serialize();
            $("#loading").show();
            $.post("leeDatos.jsp",queryString, function(data) {
                if (data.indexOf("ok") != -1) {
                    $("#errores").hide();
                    window.location = "generaEtiqueta.jsp";
                }
                else {
                    $("#errores").html(data);
                    $("#errores").show();
                }
                $("#loading").hide();
            }); 
            
        });

        $("span[id*='etq']").click(function() {
            var identificador = $(this).attr("id");
            identificador = identificador.substring(3,4);
            identificador = "input\[id=tamanno"+identificador+"\]";
            elemento = $(this).find(identificador);
            elemento.attr('checked',!elemento.attr('checked'));
            
        });

        $("input[id*='tamanno']").click(function() {
            $(this).attr('checked',!$(this).attr('checked'));
        });

       $('#ayuda').click(function(e) {
            e.preventDefault();

            var props = 'status=yes,scrollBars=yes,resizable=yes,toolbar=no,menubar=no,location=no,directories=no,width=500,height=500,top=70,left=40';
            window.open("ayuda.jsp", "Introducción de fórmulas", props);

	});

     });
