<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:xlink="http://www.w3.org/1999/xlink"
				xmlns:stxt="http://valentsoftware.com/stxt"
				version="1.0">
	<xsl:output method="text"
	            encoding="UTF-8"/>

	<xsl:template match="stxt:stxtDocument">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:metadata">+++++
<xsl:apply-templates/>
+++++
</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='title']">
Title: <xsl:value-of select="."/>
	</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='date']">
Last-Modified: <xsl:value-of select="."/>
	</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='author']">
Author: <xsl:value-of select="."/>
	</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='revision']">
revision: <xsl:value-of select="."/>
	</xsl:template>

	<xsl:template match="stxt:body">
		<xsl:apply-templates/>
	</xsl:template>

	<!-- block -->
	<xsl:template match="stxt:heading"><!-- need some kind of for loop with the level --><xsl:text>

</xsl:text>= <xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:para"><xsl:text>
</xsl:text><xsl:apply-templates/><xsl:text>
</xsl:text></xsl:template>

	<xsl:template match="stxt:list//stxt:para">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:blockquote/stxt:para">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:list"><xsl:text>
</xsl:text><xsl:apply-templates/><xsl:text>
</xsl:text></xsl:template>

	<xsl:template match="stxt:list[@ordered='true']/stxt:item">
# <xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:list[@ordered='false']/stxt:item">
* <xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:deflist"><xsl:text>
</xsl:text><xsl:apply-templates select="stxt:entry/stxt:term | stxt:entry/stxt:definition"/><xsl:text>
</xsl:text></xsl:template>
	
	<xsl:template match="stxt:term">
; <xsl:apply-templates/> 
</xsl:template>

	<xsl:template match="stxt:definition">
	-- <xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:code">
::
<xsl:value-of select="."/>
::
</xsl:template>

	<xsl:template match="stxt:blockquote">
&gt;&gt; <xsl:apply-templates/><xsl:text>
</xsl:text></xsl:template>
	
	<!-- inline -->
	<xsl:template match="stxt:link">[<xsl:value-of select="@href"/> | <xsl:value-of select="."/>]</xsl:template>

	<xsl:template match="stxt:image">{<xsl:value-of select="@href"/> | <xsl:value-of select="@title"/>}</xsl:template>

	<xsl:template match="stxt:bold">*<xsl:apply-templates/>*</xsl:template>

	<xsl:template match="stxt:italic">_<xsl:apply-templates/>_</xsl:template>

	<xsl:template match="monospace">:<xsl:apply-templates/>:</xsl:template>

	<xsl:template match="superscript">^<xsl:apply-templates/>^</xsl:template>

	<xsl:template match="subscript">~<xsl:apply-templates/>~</xsl:template>
</xsl:stylesheet>