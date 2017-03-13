/*  Generador de Etiquetas para Reactivos Químicos
 *
*  Copyright (C) 2011  Carmen López Martínez (klopez@ugr.es)
 *                     Area de Asesoría y Apoyo para el Desarrollo de Aplicacones de Gestión
 *			           CSIRC
 *			           Universidad de Granada
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

import java.io.*;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;

import org.apache.fop.apps.MimeConstants;

import org.apache.fop.servlet.ServletContextURIResolver;

/**
 * Example servlet to generate a PDF from a servlet.
 * <br/>
 * Servlet param is:
 * <ul>
 *   <li>fo: the path to a XSL-FO file to render
 * </ul>
 * or
 * <ul>
 *   <li>xml: the path to an XML file to render</li>
 *   <li>xslt: the path to an XSLT file that can transform the above XML to XSL-FO</li>
 * </ul>
 * <br/>
 * Example URL: http://servername/fop/servlet/FopServlet?fo=readme.fo
 * <br/>
 * Example URL: http://servername/fop/servlet/FopServlet?xml=data.xml&xslt=format.xsl
 * <br/>
 * For this to work with Internet Explorer, you might need to append "&ext=.pdf"
 * to the URL.
 *
 * @author <a href="mailto:fop-dev@xmlgraphics.apache.org">Apache FOP Development Team</a>
 * @version $Id: FopServlet.java 732631 2009-01-08 07:59:58Z jeremias $
 * (todo) Ev. add caching mechanism for Templates objects
 */
public class CreatePDF {


    /** The TransformerFactory used to create Transformer instances */

    protected TransformerFactory transFactory = TransformerFactory.newInstance();
    /** The FopFactory used to create Fop instances */
    protected FopFactory fopFactory = FopFactory.newInstance();
    /** URIResolver for use by this servlet */
 
    private void sendPDF(byte[] content, HttpServletResponse response) throws IOException {
        //Send the result back to the client
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment; filename=\"etiqueta.pdf\"");
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
        response.getOutputStream().flush();
    }

    /**
     * Renders an XML file into a PDF file by applying a stylesheet
     * that converts the XML to XSL-FO. The PDF is written to a byte array
     * that is returned as the method's result.
     * @param xml the XML file
     * @param xslt the XSLT file
     * @param response HTTP response object
     * @throws FOPException If an error occurs during the rendering of the
     * XSL-FO
     * @throws TransformerException If an error occurs during XSL
     * transformation
     * @throws IOException In case of an I/O problem
     */
    public void renderXML(String xml, String xsl, HttpServletResponse response, ServletContext context)
                throws FOPException, TransformerException, IOException {

        URIResolver uriResolver = new ServletContextURIResolver(context);

        //Setup sources
        Source xmlSrc = new StreamSource(new StringReader(xml));       

        String realpath = context.getRealPath(xsl);


        Source xsltSrc = new StreamSource(new File(realpath));

        //Le damos el path real de la aplicacion para que encuentre los ficheros
        realpath = context.getRealPath(".");
        

        //this.transFactory.setURIResolver(uriResolver);
        Transformer transformer = this.transFactory.newTransformer(xsltSrc);
        transformer.setURIResolver(uriResolver);

        //Start transformation and rendering process
        render(xmlSrc, transformer, response, realpath);
    }

    /**
     * Renders an input file (XML or XSL-FO) into a PDF file. It uses the JAXP
     * transformer given to optionally transform the input document to XSL-FO.
     * The transformer may be an identity transformer in which case the input
     * must already be XSL-FO. The PDF is written to a byte array that is
     * returned as the method's result.
     * @param src Input XML or XSL-FO
     * @param transformer Transformer to use for optional transformation
     * @param response HTTP response object
     * @throws FOPException If an error occurs during the rendering of the
     * XSL-FO
     * @throws TransformerException If an error occurs during XSL
     * transformation
     * @throws IOException In case of an I/O problem
     */
    public void render(Source src, Transformer transformer, HttpServletResponse response, String realpath)
                throws FOPException, TransformerException, IOException {

        FOUserAgent foUserAgent = getFOUserAgent(realpath);

        //Setup output
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //Setup FOP
        fopFactory.setBaseURL(realpath);
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);


        //Make sure the XSL transformation's result is piped through to FOP
        Result res = new SAXResult(fop.getDefaultHandler());

        //Start the transformation and rendering process
        transformer.transform(src, res);

        //Return the result
        sendPDF(out.toByteArray(), response);
    }

    /** @return a new FOUserAgent for FOP */
    public FOUserAgent getFOUserAgent(String realpath) {
        FOUserAgent userAgent = fopFactory.newFOUserAgent();
        userAgent.setBaseURL(realpath);
        //Configure foUserAgent as desired
        return userAgent;
    }

}
