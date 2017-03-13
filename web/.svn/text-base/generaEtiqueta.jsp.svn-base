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
 
<%@page import="laboratorios.*"%>
<%@page import="java.util.*"%>

<%

    Etiqueta etiqueta = (Etiqueta)session.getAttribute("etiqueta");
    

    if (etiqueta != null) {

        String xml = "<etiqueta>"+
            "<reactivo>"+etiqueta.getReactivo()+"</reactivo>"+
            "<formula>"+etiqueta.getFormula()+"</formula>"+
            "<frasesh>"+etiqueta.getFrasesh()+"</frasesh>"+
            "<frasesp>"+etiqueta.getFrasesp()+"</frasesp>"+
            "<fechaelab>"+etiqueta.getFelaboracion()+"</fechaelab>"+
            "<fechacad>"+etiqueta.getFcaducidad()+"</fechacad>"+
            "<observaciones>"+etiqueta.getObservaciones()+"</observaciones>"+
            "<palabraadvertencia>"+etiqueta.getAdvertencia()+"</palabraadvertencia>";

        ArrayList pictogramas = etiqueta.getPictogramas();
        
        if (!pictogramas.isEmpty()) {
            xml +="<pictogramas>";
            ServletContext context = getServletContext();
            String path = context.getRealPath("pictogramas/");

            for (int i = 0; i < pictogramas.size(); i++) {
                String p_name = (String)pictogramas.get(i);


                    xml += "<pictograma>"+
                            "<base-path>"+path+"</base-path>"
                          + "<nombre>"+p_name+"</nombre>"
                          + "<fichero>"+p_name+"</fichero>"
                          + "</pictograma>";

            }

            xml += "</pictogramas>";
        }
        xml += "</etiqueta>";
        CreatePDF cbp = new CreatePDF();
        cbp.renderXML(xml, "xml/"+etiqueta.getXsl()+".xsl", response,getServletContext());
    }

%>
