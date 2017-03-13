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

<p class="ayuda">
    Para introducir una fórmula escriba el subíndice junto al elemento que
    modifica en texto normal. Utilice paréntesis si es necesario. El programa
    traducirá los números en subíndices y los escribirá la fórmula correctamente.
</p>
<p class="ayuda">

    En algunos casos es posible que el programa no sepa traducir del todo la fórmula,
    por ejemplo, la siguiente fórmula debería verse como se especifica:
</p>

<div class="formula">
    Au<sub>2</sub>(SeO<sub>4</sub>)<sub>3</sub><br>
</div>
<p class="ayuda">
    Sin embargo se muestra así:
</p>
<div class="formula">
    Au2(SeO<sub>4</sub>)<sub>3</sub>
</div>
<p class="ayuda">
    Para solucionar este problema deberíamos indicar qué números de los introducidos
    son subíndices y cuales no. Para ello tenemos que escribir la fórmula encerrando los números
    que queramos que sean subíndices entre las marcas &lt;sub&gt; y &lt;/sub&gt;.
    Si la fórmula anterior la introducimos como sigue:
</p>
<div class="formula">
    Au&lt;sub&gt;2&lt;/sub&gt;(SeO&lt;sub&gt;4&lt;/sub&gt;)&lt;sub&gt;3&lt;/sub&gt;
</div>
<p class="ayuda">
    La fórmula se mostrará correctamente.
</p>


<%@include file="pie.jsp"%>
