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
	<xsl:param name="html_style">
		<xsl:if test="$stylesheet">
			<xsl:choose>
				<xsl:when test="$stylesheet_content">
					<style type="text/css"><xsl:comment>
						<xsl:value-of select="$stylesheet_content"/>
					</xsl:comment></style>
				</xsl:when>
				<xsl:otherwise>
					<link rel="stylesheet" href="{$stylesheet}" type="text/css" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:param>

	<!--<xsl:include href="{template}"/>-->
	<xsl:include href="etc/template.html"/>

	<!-- block -->
	<xsl:template match="stxt:heading">
		<xsl:variable name="heading_level"
		              select="@level + $initial_header - 1"/>
		<xsl:choose>
			<xsl:when test="$heading_level &lt; 6">
				<xsl:element name="{concat('h',$heading_level)}">
					<xsl:apply-templates/>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<h6><xsl:apply-templates/></h6>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="stxt:para">
		<p><xsl:apply-templates/></p>
	</xsl:template>
	
	<xsl:template match="stxt:list[@ordered='true']">
		<ol><xsl:apply-templates select="stxt:item"/></ol>
	</xsl:template>

	<xsl:template match="stxt:list[@ordered='false']">
		<ul><xsl:apply-templates select="stxt:item"/></ul>
	</xsl:template>
	
	<xsl:template match="stxt:item">
		<li><xsl:apply-templates/></li>
	</xsl:template>

	<!-- Uncomment this block to strip paras out of list items to be more
	     consistent with common HTML. One problem here is that sequential
	     paragraphs become one.
	<xsl:template match="stxt:item/stxt:para">
		<xsl:apply-templates/>
	</xsl:template>
	-->

	<xsl:template match="stxt:deflist">
		<dl><xsl:apply-templates select="stxt:entry/stxt:term | stxt:entry/stxt:definition"/></dl>
	</xsl:template>
	
	<xsl:template match="stxt:term">
		<dt><xsl:apply-templates/></dt>
	</xsl:template>

	<xsl:template match="stxt:definition">
		<dd><xsl:apply-templates/></dd>
	</xsl:template>

	<xsl:template match="stxt:code">
		<pre><xsl:value-of select="."/></pre>
	</xsl:template>

	<xsl:template match="stxt:blockquote">
		<blockquote><xsl:apply-templates/></blockquote>
	</xsl:template>
	
	<!-- inline -->
	<xsl:template match="stxt:link">
		<xsl:element name="a">
			<xsl:attribute name="href"><xsl:value-of select="@href"/></xsl:attribute>
			<xsl:value-of select="."/>
		</xsl:element>	
	</xsl:template>

	<xsl:template match="stxt:image">
		<xsl:element name="object">
			<xsl:attribute name="data"><xsl:value-of select="@href"/></xsl:attribute>
			<!-- try to deduce type? -->
			<xsl:value-of select="@title"/>
		</xsl:element>	
	</xsl:template>

	<xsl:template match="stxt:bold">
		<strong><xsl:apply-templates/></strong>
	</xsl:template>

	<xsl:template match="stxt:italic">
		<em><xsl:apply-templates/></em>
	</xsl:template>

	<xsl:template match="stxt:monospace">
		<kbd><xsl:apply-templates/></kbd>
	</xsl:template>

	<xsl:template match="stxt:superscript">
		<sup><xsl:apply-templates/></sup>
	</xsl:template>

	<xsl:template match="stxt:subscript">
		<sub><xsl:apply-templates/></sub>
	</xsl:template>
</xsl:stylesheet>