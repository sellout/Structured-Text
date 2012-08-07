<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:xlink="http://www.w3.org/1999/xlink"
				xmlns:stxt="http://valentsoftware.com/stxt"
				xmlns="http://www.w3.org/1999/xhtml"
				version="1.0">
	<!-- FIXME: this makes the excess namespaces disappear, but it can't be right. -->
	<xsl:namespace-alias stylesheet-prefix="xlink" result-prefix="foo"/>
	<xsl:namespace-alias stylesheet-prefix="stxt" result-prefix="bar"/>
	<xsl:output method="xml"
	            encoding="UTF-8"
	            indent="yes"
	            doctype-public="-//W3C//DTD XHTML 1.1//EN"
	            doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"/>
	<xsl:strip-space elements="*"/>
	<xsl:preserve-space elements="code"/>

	<xsl:param name="template" select="'etc/template.html'"/>
	<xsl:param name="stylesheet" select="''"/>
	<xsl:param name="stylesheet_content" select="''"/>

	<xsl:variable name="initial_header" select="1"/>

	<!-- Content of the stylesheet parts. Don't know how to abstract this correctly -->
	<xsl:param name="xml_style" select="$stylesheet ">
		<xsl:if test="$stylesheet">
			<xsl:processing-instruction name="xml-stylesheet">href="<xsl:value-of select="$stylesheet"/>" type="text/css"</xsl:processing-instruction>
		</xsl:if>
	</xsl:param>

	<xsl:template match="/">
		<xsl:value-of select="$stylesheet"/>
		<book>
			<bookinfo>
				<xsl:if test="stxt:stxtDocument/stxt:metadata/stxt:header[@key='title']">
						<title><xsl:value-of select="stxt:stxtDocument/stxt:metadata/stxt:header[@key='title']"/></title>
				</xsl:if>
				<xsl:if test="stxt:stxtDocument/stxt:metadata/stxt:header[@key='author']">
					<author><xsl:value-of select="stxt:stxtDocument/stxt:metadata/stxt:header[@key='author']"/></author>	
				</xsl:if>
			</bookinfo>
			<xsl:apply-templates select="stxt:stxtDocument/stxt:body"/>
		</html>
	</xsl:template>

	<!-- block -->
	<xsl:template match="stxt:heading">
		<xsl:element name="title">
			<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>

	<xsl:template match="stxt:para">
		<para><xsl:apply-templates/></para>
	</xsl:template>
	
	<xsl:template match="stxt:list[@ordered='true']">
		<orderedlist><xsl:apply-templates select="stxt:item"/></orderedlist>
	</xsl:template>

	<xsl:template match="stxt:list[@ordered='false']">
		<itemizedlist><xsl:apply-templates select="stxt:item"/></itemizedlist>
	</xsl:template>
	
	<xsl:template match="stxt:item">
		<listitem><xsl:apply-templates/></listitem>
	</xsl:template>

	<xsl:template match="stxt:deflist">
		<qandaset><xsl:apply-templates select="stxt:entry/stxt:term | stxt:entry/stxt:definition"/></qandaset>
	</xsl:template>
	
	<xsl:template match="stxt:term">
		<question><xsl:apply-templates/></question>
	</xsl:template>

	<xsl:template match="stxt:definition">
		<answer><xsl:apply-templates/></answer>
	</xsl:template>

	<xsl:template match="stxt:code">
		<programlisting><xsl:value-of select="."/></programlisting>
	</xsl:template>

	<xsl:template match="stxt:blockquote">
		<blockquote><xsl:apply-templates/></blockquote>
	</xsl:template>
	
	<xsl:template match="stxt:separator">
		<hr />
	</xsl:template>
	
	<!-- inline -->
	<xsl:template match="stxt:link">
		<xsl:element name="ulink">
			<xsl:attribute name="url"><xsl:value-of select="@href"/></xsl:attribute>
			<xsl:apply-templates/>
		</xsl:element>	
	</xsl:template>

	<xsl:template match="stxt:image">
		<inlinemediaobject>
			<imageobject>
				<xsl:element name="imagedata">
					<xsl:attribute name="fileref"><xsl:value-of select="@href"/></xsl:attribute>
				</xsl:element>
			</imageobject>
			<textobject><xsl:value-of select="@title"/></textobject>
		</inlinemediaobject>
	</xsl:template>

	<xsl:template match="stxt:bold">
		<strong><xsl:apply-templates/></strong>
	</xsl:template>

	<xsl:template match="stxt:italic">
		<emphasis><xsl:apply-templates/></emphasis>
	</xsl:template>

	<xsl:template match="stxt:monospace">
		<userinput><xsl:apply-templates/></userinput>
	</xsl:template>

	<xsl:template match="stxt:superscript">
		<superscript><xsl:apply-templates/></superscript>
	</xsl:template>

	<xsl:template match="stxt:subscript">
		<subscript><xsl:apply-templates/></subscript>
	</xsl:template>
</xsl:stylesheet>
