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

import java.util.*;
import java.util.regex.Pattern;

public class FormatFecha {
    static ArrayList meses = new ArrayList();
    
    /**
     * Formatea la fecha devuelta por la base de datos que esta en formato yyyy-mm-dd hh:mm:ss.s
     * y la devuelve en formato dd-mm-yyyy
     */
    public static String formatFecha(String fecha) {
        String nuevaFecha = new String();

        if (fecha != null && fecha.length() >= 10) {
            nuevaFecha = fecha.substring(8,10)+"-"+fecha.substring(5,7)+"-"+fecha.substring(0,4);
        }
        else {
            nuevaFecha = "";
        }
        return nuevaFecha;

    }

    /**
     * Devuelve el anno actual
     */
    public static int annoActual() {
        Calendar hoy = Calendar.getInstance();
        return hoy.get(Calendar.YEAR);
    }
    
 
    /**
     * Devuelve la fecha actual en formato dd-mm-yyyy
     */
    public static String fechaActual() {
        Calendar hoy = Calendar.getInstance();
        String fecha = new String();

        if (hoy.get(Calendar.DAY_OF_MONTH) < 10) {
            fecha = "0"+hoy.get(Calendar.DAY_OF_MONTH)+"/";
        }
        else {
            fecha = ""+hoy.get(Calendar.DAY_OF_MONTH)+"/";
        }

        if (hoy.get(Calendar.MONTH) < 9) {
            fecha += "0"+(hoy.get(Calendar.MONTH)+1)+"/";
        }
        else {
            fecha += ""+(hoy.get(Calendar.MONTH)+1)+"/";
        }

        fecha += hoy.get(Calendar.YEAR);
        return fecha;
    }

    /**
     * Devuelve la fecha actual en formato dd de mes de yyy
     */
    public static String fechaActualLarga() {
        Calendar hoy = Calendar.getInstance();
        String fecha = new String();
        if (hoy.get(Calendar.DAY_OF_MONTH) < 10) {
            fecha = "0"+hoy.get(Calendar.DAY_OF_MONTH);
        }
        else {
            fecha = ""+hoy.get(Calendar.DAY_OF_MONTH);
        }

        fecha += " de "+getMeses().get(hoy.get(Calendar.MONTH));

        fecha += " de "+hoy.get(Calendar.YEAR);
        return fecha;
    }

    /**
     * Devuelve el �ltimo d�a del mes especificado en formato dd/mm/yyyy
     */
    public static String getLastDay(int mes, int anno) {
        Calendar fecha = Calendar.getInstance();
        String ultimoDia = new String();
        //Damos valor al mes y al anno para el que queremos calcular el ultimo dia.
        if (mes > 0 && mes <= 12) {
            fecha.set(Calendar.MONTH,mes-1);    

            fecha.set(Calendar.YEAR,anno);
       
            //El dia
            ultimoDia = ""+fecha.getActualMaximum(Calendar.DAY_OF_MONTH); 

            //El mes
            if (mes < 10) {
                ultimoDia += "/0"+mes; 
            }
            else {
                ultimoDia += "/"+mes;
            }

            //El anno
            ultimoDia += "/"+anno;
        }

        return ultimoDia;
    }
       
    public static ArrayList getMeses() {
        meses = new ArrayList();
        meses.add(0,"ENERO");
        meses.add(1,"FEBRERO");
        meses.add(2,"MARZO");
        meses.add(3,"ABRIL");
        meses.add(4,"MAYO");
        meses.add(5,"JUNIO");
        meses.add(6,"JULIO");
        meses.add(7,"AGOSTO");
        meses.add(8,"SEPTIEMBRE");
        meses.add(9,"OCTUBRE");
        meses.add(10,"NOVIEMBRE");
        meses.add(11,"DICIEMBRE");
        return meses;
    }

    //Testeamos una fecha con formato dd-mm-yyyy
    public static int testFecha(String fecha) {
        int error = 0;

        int dia;
        int mes;
        int anno;

        fecha = fecha.replaceAll("-", "/");
         if (Pattern.matches("(([0-9]){1,2}/){2}20([0-9]){2}",fecha)) {
            String spl_fecha[] = fecha.split("/");

            try {
                dia = new Integer(spl_fecha[0]).intValue();
                mes = new Integer(spl_fecha[1]).intValue();
                anno = new Integer(spl_fecha[2]).intValue();

                error = 0;

                if (mes < 1 || mes > 12) {
                    error = 1;
                }

                if (dia <= 0) {
                    error = 1;
                }
                if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    if (dia > 30) {
                        error = 1;
                    }
                }
                else if (mes == 2) {
                    if (dia > 28) {
                        if (anno % 4 != 0) {
                            error = 1;
                        }
                        else if (dia > 29) {
                            error = 1;
                        }
                    }
                }
                else {
                    if (dia > 31) {
                        error = 1;
                    }
                }

            }
            catch (NumberFormatException e) {
                error = 1;
            }
        }
        else {
            error = 1;
        }

        return error;


    }

    

}
