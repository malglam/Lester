# Lester - Generador de Etiquetas para Reactivos Químicos

¿Qué es Lester?
---------------

Lester genera etiquetas para identificar los reactivos químicos almacenados en frascos de diversos tamaños. Se ha implementado un formulario web donde el usuario introducirá los datos requeridos para la generación de la etiqueta (reactivo, fórmula, frases de peligro H, frases de precaución P, palabra de advertencia, fecha de elaboración,  fecha de caducidad y observaciones). El usuario puede elegir el tamaño de la etiqueta. Actualmente se proporcionan plantillas para cuatro tipos de etiquetas estándar, pero la aplicación es configurable para añadir más tamaños. Los campos mostrados en la etiqueta serán variables, dependiendo del tamaño de la etiqueta se eliminarán algunos por razones de espacio. La etiqueta llevará una serie de pictogramas de peligrosidad que el usuario habrá elegido de entre los instalados en la aplicación. Una vez recopilados todos los datos necesarios, se genera la etiqueta y se muestra al usuario en formato PDF para facilitar su impresión.

Configuración
-------------

El programa necesita los siguientes ficheros y directorios para funcionar correctamente:

* Directorio conf: Aquí se encuentran los ficheros de configuración. La aplicación usa dos ficheros
de configuración:
	- etiquetas.properties: Aquí especificamos el directorio donde guardaremos los pictogramas. También
le diremos las etiquetas que generará el programa. Nombrandolas desde etq0 en adelante, especificaremos
el tamaño de la etiqueta, la leyenda que aparecerá en la página y el fichero xsl que implementa la 
transformación del xml al PDF.

	- pictogramas.properties: Lista los pictogramas de peligrosidad mostraremos al usuario para incluirlos
en las etiquetas. Debe incluir una lista de ficheros donde se encuentren las imágenes. 
Estos ficheros deben estar almacenados en el directorio pictogramas.

* Directorio xml: En este directorio se almacenan los ficheros xslt que se encargan de la
transformación de los ficheros xml que genera la aplicación en PDFs que se mostrarán al usuario.
Se incluyen cuando ficheros base que cubren los formatos de pegatinas estándar siguentes:
        - 38.1 x 29.6
        - 70 x 50.8
        - 105 x 74.1
        - 210 x 148.1

Se pueden incluir más formatos de pegatinas simplemente añadiendo el fichero de xslt correspondiente
e incluyendo los datos necesarios en el fichero de configuración "etiquetas.properties"

Requisitos
----------
- Lester es una aplicación web desarrollada en java, por lo tanto necesita Java SDK o Java Runtime Engine version 1.6 o superior. Se ha desarrollado usando JDK 1.6 y Apache Tomcat 7.0.22.0.
- Se ha usado jQuery para mejorar la interfaz de usuario.
- Para la generación de los PDFs que muestran las etiquetas se ha usado la librería FOP de Apache http://xmlgraphics.apache.org/fop/



