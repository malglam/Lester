/*  Generador de Etiquetas para Reactivos Químicos
 *
 *  Copyright (C) 2011  Carmen López Martínez (klopez@ugr.es)
 *                      Area de Asesoría y Apoyo para el Desarrollo de Aplicacones de Gestión
 *		            	CSIRC
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
package laboratorios;

import java.util.regex.*;

/**
 *
 * @author clopez
 */
public class Formula {
    String formula = new String();
    String formula_xml = new String();

    public String parseaFormula(String p_formula) {

        if (p_formula != null && p_formula.compareTo("") != 0) {
            this.formula = p_formula;

            Pattern p = Pattern.compile("([A-Z][a-z]?|\\))(\\d+)(?=[^\\(]|$)");

            Matcher m = p.matcher(formula);

            //Buffer de salida principal
            StringBuffer sb = new StringBuffer();

            boolean result = m.find();

            //Patrón para sustituir los números
            Pattern p1 = Pattern.compile("\\d+");

            // Loop through and create a new String
            // with the replacements
            while(result) {

                String grupo = m.group();
                //Hacemos matching con los números
                Matcher m1 = p1.matcher(grupo);
                //Buffer de salida del grupo

                StringBuffer sb1 = new StringBuffer();

                while (m1.find()) {
                    //Añadimos las marcas que nos hacen falta
                    m1.appendReplacement(sb1, "<sub>"+m1.group()+"</sub>");
                }
                //Añadimos el resto
                m1.appendTail(sb1);
                //Sustituimos en la cadena principal
                m.appendReplacement(sb,sb1.toString());

                result = m.find();
            }
            // Add the last segment of input to
            // the new String
            m.appendTail(sb);

            this.formula_xml = sb.toString();
        }
        else {
            this.formula_xml = "";
        }

        return formula_xml;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula_xml() {
        return formula_xml;
    }

    public void setFormula_xml(String formula_xml) {
        this.formula_xml = formula_xml;
    }




}
