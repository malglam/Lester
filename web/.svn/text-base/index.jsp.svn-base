<%@page import="java.io.*"%>
<%
/*  Generador de Etiquetas para Reactivos Químicos
 *
 *  Copyright (C) 2011  Carmen López Martínez (klopez@ugr.es)
 *                      Area de Asesoría y Apoyo para el Desarrollo de Aplicacones de Gestión
 *          			CSIRC
 *			            Universidad de Granada
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
%>
<%@include file="cabecera.jsp"%>
    
<%
Etiqueta etiqueta = (Etiqueta)session.getAttribute("etiqueta");

if (etiqueta == null) {
    etiqueta = new Etiqueta();
}

//Leemos los pictogramas del fichero de configuracion
ServletContext context = getServletContext();
String path = context.getRealPath("conf/");
        
Properties pictogramas = new Properties();
pictogramas.load(new FileInputStream(path+"/pictogramas.properties"));
        
Hashtable selected_pictogramas = new Hashtable();

ArrayList listaPictogramas = etiqueta.getPictogramas();

/** Cogemos los pictogramas que ya haya seleccionado el usuario, para marcarlos en la página */
if (listaPictogramas != null) {
    for (int i = 0; i < listaPictogramas.size(); i++) {
        selected_pictogramas.put((String)listaPictogramas.get(i), (String)listaPictogramas.get(i));
    }
}

//Leemos el fichero de configuración de las etiquetas
Properties conf_etiquetas = new Properties();
conf_etiquetas.load(new FileInputStream(path+"/etiquetas.properties"));

session.setAttribute("conf_etiquetas",conf_etiquetas);

%>

<div id="loading" style="display: none; height: 50px; width: 50px; position: absolute; top: 0; left: 400; z-index: 1000; background: url('imagenes/loader.gif') no-repeat;"></div>          
<div class="error centrado" id="errores" style="display: none;"></div>
<form name="etiqueta" id="etiqueta" action="leeDatos.jsp" method="post" accept-charset="utf-8">
    <div class="centrado">
        <fieldset>
            <legend>Datos para generar la etiqueta</legend>
            <span class="campobusqueda">
                <label class="etiqueta" for="reactivo">Reactivo</label>
                <input type="text" id="reactivo" name="reactivo" value="<%=etiqueta.getReactivo()%>" maxlength="30">
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="formula">Fórmula</label>
                <input type="text" id="formula" name="formula" value="<%=etiqueta.getFormula()%>" maxlength="15"> <a href="ayuda.jsp" class="masinfo" id="ayuda">?</a>
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="frasesh">Frases de Peligro H</label>
                <input type="text" id="frasesh" name="frasesh" value="<%=etiqueta.getFrasesh()%>" maxlength="30">
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="frasesp">Frases de Precaución P</label>
                <input type="text" id="frasesp" name="frasesp" value="<%=etiqueta.getFrasesp()%>" maxlength="30">
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="advertencia">Palabra de Advertencia</label>
                <select id="advertencia" name="advertencia">
                    <option value=""> --Ninguna-- </option>
                    <option <%=etiqueta.getAdvertencia().compareTo("Atención")==0?"selected":""%> value="Atención"> Atención </option>
                    <option <%=etiqueta.getAdvertencia().compareTo("Peligro")==0?"selected":""%> value="Peligro"> Peligro </option>
                </select>                
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="felaboracion">Fecha de Elaboración</label>
                <input type="text"id="felaboracion" name="felaboracion" value="<%=etiqueta.getFelaboracion()%>">
                <span>(dd/mm/yyyy)</span>
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="fcaducidad">Fecha de Caducidad</label>
                <input type="text" id="fcaducidad" name="fcaducidad" value="<%=etiqueta.getFcaducidad()%>">
                <span>(dd/mm/yyyy)</span>
            </span>
            <span class="campobusqueda">
                <label class="etiqueta" for="observaciones">Observaciones</label>
                <input type="text" id="observaciones" name="observaciones" maxlength="65" size="60" value="<%=etiqueta.getObservaciones()%>">
            </span>
        </fieldset>
        <fieldset>
            <legend>Tamaño de la etiqueta</legend>            
            <span style="display: block;">
<%                      

            
            String key = "etq0";
            int i = 0;
            
            while (conf_etiquetas.containsKey(key)) {
                
                String desc = conf_etiquetas.getProperty(key+".desc");
                
%>                
                <span id="<%=key%>" style="display: block;"><input <%=etiqueta.getTamanno().compareTo(key)==0?"checked":""%> type="radio" name="tamanno"  id="tam_<%=key%>" value="<%=key%>"><%=desc%></span>                
<%    
                i++;
                key = "etq"+i;
                
            }
%>               
                
            </span>
        </fieldset>

        <fieldset>
            <legend>Pictogramas de peligrosidad (Seleccionar un máximo de 3)</legend>
<%
            Enumeration it = pictogramas.keys();     
                  
            i = 0;
            String directorio = conf_etiquetas.getProperty("dir_pictogramas");

            if (directorio == null || directorio.compareTo("") == 0) {
                directorio = "pictogramas";
            }
            for (; it.hasMoreElements(); i++) {
                key = (String)it.nextElement();
                String p = (String)pictogramas.getProperty(key);
                String selected = "";

                if (selected_pictogramas.contains((p))) {
                    selected = "checked";
                }
                if ((i % 5) == 0) {
%>
                <div style="display: block; text-align:center;">
<%
                }
%>
                  <img src="<%=directorio%>/<%=p%>" alt="<%=p%>" style="border: 1px solid #BBBBBB; width: 80px; height: 80px;"><input <%=selected%> type="checkbox" name="p<%=i%>" value="<%=p%>">
<%
                if ((i%5) == 4 || i == pictogramas.size()-1) {
%>  
                </div>
<%
                }
            }
%>
        </fieldset>
        <fieldset>             
            <legend></legend>
             <input id="boton_genera" class="boton" name="enviar" type="submit" value="Generar"/>
            
        </fieldset>
        
    </div>
</form>

<%@include file="pie.jsp"%>
