<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
<xsl:param name="versionParam" select="'1.0'"/> 

<!-- ========================= -->
<!-- root element: etiqueta -->
<!-- ========================= -->
<xsl:template match="etiqueta">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

      <fo:layout-master-set>

        <fo:simple-page-master master-name="simpleA4" 
            page-height="297mm" 
            page-width="210mm" 
            margin-top="5mm" 
            margin-bottom="0mm" 
            margin-left="5mm" 
            margin-right="0mm"
            padding="0cm">

            <fo:region-body   margin="0cm"/>
            <fo:region-before extent="0cm"/>
            <fo:region-after  extent="0cm"/>
            <fo:region-start  extent="0cm"/>
            <fo:region-end    extent="0cm"/>
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">
        <fo:block text-align="center" display-align="center">
            <fo:table height="295mm" width="208mm">
                <fo:table-body >
                  <xsl:call-template name="recurse_row"/>
                </fo:table-body>
            </fo:table>
        </fo:block>            
        </fo:flow>
      </fo:page-sequence>
</fo:root>
</xsl:template>

<!-- Template para las filas -->
<xsl:template name="recurse_row">
	<xsl:param name="num">0</xsl:param> <!-- param has initial value of
1 -->
	<xsl:if test="not($num = 2)">

        <fo:table-row>
            <xsl:call-template name="recurse_cell"/>
        </fo:table-row>
		<xsl:call-template name="recurse_row">
			<xsl:with-param name="num">
				<xsl:value-of select="$num + 1"/>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<!-- Template para las celdas -->
<xsl:template name="recurse_cell">
	<xsl:param name="num">0</xsl:param> <!-- param has initial value of
1 -->
	<xsl:if test="not($num = 1)">

<fo:table-cell keep-together.within-page="always" width="202mm" border="0px solid gray" min-height="142mm">
            <xsl:call-template name="temp_etiqueta"/>
        </fo:table-cell >
		<xsl:call-template name="recurse_cell">
			<xsl:with-param name="num">
				<xsl:value-of select="$num + 1"/>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<!-- Template para las etiquetas -->

<xsl:template name="temp_etiqueta">
    <fo:table overflow="hidden" border-collapse="collapse" padding="0mm">
        <fo:table-body>
            <fo:table-row>
                <fo:table-cell number-columns-spanned="3"  >
                    <fo:block overflow="hidden" font-size="34pt" font-weight="bold" text-align="center">
                        <xsl:value-of select="reactivo"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
                <fo:table-cell number-rows-spanned="2" number-columns-spanned="2" >
                    <xsl:apply-templates select="formula"/>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block overflow="hidden" font-size="20pt" text-align="center">
                        Frases H
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
                <fo:table-cell >
                    <fo:block overflow="hidden" font-size="20pt" text-align="center" font-weight="bold">
                        <xsl:value-of select="frasesh"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
               <fo:table-cell >
                    <fo:block overflow="hidden" font-size="20pt" text-align="center">
                        Fecha Elaboración
                    </fo:block>
               </fo:table-cell> 
               <fo:table-cell >
                    <fo:block overflow="hidden" font-size="20pt" text-align="center">
                        Fecha Caducidad 
                    </fo:block>
               </fo:table-cell> 
               <fo:table-cell>
                    <fo:block overflow="hidden" font-size="20pt"  text-align="center">
                        Frases P
                    </fo:block>
               </fo:table-cell> 
            </fo:table-row>
            <fo:table-row>
                <fo:table-cell >
                    <fo:block overflow="hidden" font-size="20pt" text-align="center" font-weight="bold" >
                        <xsl:value-of select="fechaelab"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell >
                    <fo:block overflow="hidden" font-size="20pt" text-align="center" font-weight="bold" >
                        <xsl:value-of select="fechacad"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block overflow="hidden" font-size="20pt"  text-align="center" font-weight="bold" >
                        <xsl:value-of select="frasesp"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
                <fo:table-cell number-columns-spanned="3">
                    <fo:block overflow="hidden" font-size="24pt" font-weight="bold" text-align="center">
                        <xsl:value-of select="palabraadvertencia"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
            <fo:table-row>
                <fo:table-cell number-columns-spanned="3">
                    <fo:block overflow="hidden" font-size="16pt">
                        Observaciones: <xsl:value-of select="observaciones"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>

            <fo:table-row>
                <fo:table-cell number-columns-spanned="3">
                    <xsl:apply-templates select="pictogramas"/>
                </fo:table-cell>
            </fo:table-row>
        </fo:table-body>
    </fo:table>


    <!-- Fila para los pictogramas -->
                           
</xsl:template>

<xsl:template match="pictogramas">
        <fo:block font-size="10pt" text-align="center">
           <xsl:for-each select="pictograma">
                <xsl:variable name="base_path" select="base-path"/>
                <xsl:variable name="fich" select="fichero"/>
                <fo:external-graphic content-width="180px" content-height="180px" src="url({$base_path}/{$fich})"/>
           </xsl:for-each> 
        </fo:block>

</xsl:template>

<xsl:template match="formula">
  <fo:block overflow="hidden" max-width="66%" font-size="20pt" text-align="center"  font-weight="bold">
      <xsl:apply-templates/>
  </fo:block>
</xsl:template>

<xsl:template match="sub">
    <fo:inline vertical-align="sub" font-size="75%">
        <xsl:apply-templates/>
    </fo:inline>
</xsl:template>

</xsl:stylesheet>
