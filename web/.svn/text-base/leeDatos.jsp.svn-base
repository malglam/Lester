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

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>    
<%@page import="java.util.*"%>
<%@page import="laboratorios.*"%>

<%

ArrayList listaErrores = new ArrayList();

Etiqueta etiqueta = new Etiqueta();

String reactivo = (String)request.getParameter("reactivo");

if (reactivo != null && reactivo.compareTo("") != 0) {
    etiqueta.setReactivo(reactivo);
}
else {
    listaErrores.add("Debe introducir un nombre de reactivo");
}

String formula = (String)request.getParameter("formula");

if (formula != null && formula.compareTo("") != 0) {
    Formula f_parser = new Formula();
    formula = f_parser.parseaFormula(formula);
    etiqueta.setFormula(formula);
}
else {
    listaErrores.add("Debe introducir una f&oacute;rmula");
}

String felaboracion = (String)request.getParameter("felaboracion");

if (felaboracion !=  null && felaboracion.compareTo("") != 0) {
    
    int error = FormatFecha.testFecha(felaboracion);
    if (error == 0) {
        etiqueta.setFelaboracion(felaboracion);
    }
    else {
        listaErrores.add("Fecha de elaboración err&oacute;nea");
    }
}

String fcaducidad = (String)request.getParameter("fcaducidad");
if (fcaducidad != null && fcaducidad.compareTo("") != 0) {
    int error = FormatFecha.testFecha(fcaducidad);
    if (error == 0) {
        etiqueta.setFcaducidad(fcaducidad);
    }
    else {
        listaErrores.add("Fecha de caducidad err&oacute;nea");
    }
}

String frasesh = (String)request.getParameter("frasesh");
etiqueta.setFrasesh(frasesh);

String frasesp = (String)request.getParameter("frasesp");
etiqueta.setFrasesp(frasesp);

String advertencia = (String)request.getParameter("advertencia");
etiqueta.setAdvertencia(advertencia);

String observaciones = (String)request.getParameter("observaciones");
if (observaciones != null && observaciones.length() > 65) {
    observaciones = observaciones.substring(0,65);
}
etiqueta.setObservaciones(observaciones);

String tamanno = (String)request.getParameter("tamanno");

if (tamanno == null || tamanno.compareTo("") == 0) {
    listaErrores.add("Debe seleccionar un tama&ntilde;o de etiqueta");
    tamanno = new String();
}
else {
    etiqueta.setTamanno(tamanno);
}

Properties conf_etiquetas = (Properties)session.getAttribute("conf_etiquetas");
if (conf_etiquetas == null || conf_etiquetas.isEmpty()) {
    listaErrores.add("No se encuentra el fichero de configuración de las etiquetas");
}
else {
if (tamanno != null && tamanno.compareTo("") != 0) {
    
    String key = tamanno;
    
    if (conf_etiquetas.getProperty(key) != null) {
        String xslt = conf_etiquetas.getProperty(key+".file");
        
        if (xslt != null && xslt.compareTo("") != 0) {
            etiqueta.setXsl(xslt);
        }
        else {
            listaErrores.add("Tama&ntilde;o incorrecto");
        }
    }
    else {
        listaErrores.add("Tama&ntilde;o incorrecto");
    }
}
else {
     listaErrores.add("Tama&ntilde;o incorrecto");
}
}

ArrayList pictogramas = new ArrayList();

String p = new String();

for (int i = 0; i < 10; i++) {
    p = (String)request.getParameter("p"+i);

    if (p != null && p.compareTo("") != 0) {
        pictogramas.add(p);
    }
}

if (pictogramas.size() == 0) {
    p = "noimage";
    pictogramas.add(p);
}

etiqueta.setPictogramas(pictogramas);

if (listaErrores.size() == 0) {
%>
    ok
<%
}
else {
%>    
    
    <ul>
<%
    for (int i = 0; i < listaErrores.size(); i++) {
%>
            <li><%=listaErrores.get(i)%></li>
<%
    }
%>
        </ul>    
<%            
}

session.setAttribute("etiqueta", etiqueta);

%>
