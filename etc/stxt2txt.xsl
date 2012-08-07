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

	<xsl:template match="stxt:metadata">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='title']">
		<xsl:value-of select="."/>
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
	<xsl:template match="stxt:heading[@level='1']">
		============ <xsl:apply-templates/> ============
</xsl:template>

	<xsl:template match="stxt:heading[@level='2']">
		====== <xsl:apply-templates/> ======
</xsl:template>

	<xsl:template match="stxt:heading[@level='3']">
		------ <xsl:apply-templates/> ------
</xsl:template>

	<xsl:template match="stxt:heading[@level='4']">
		--- <xsl:apply-templates/> ---
</xsl:template>

	<xsl:template match="stxt:heading[@level='5']">
		... <xsl:apply-templates/> ...
</xsl:template>

	<xsl:template match="stxt:heading[@level>=6]">
		. <xsl:apply-templates/> .
</xsl:template>

	<xsl:template match="stxt:para"><xsl:text>

</xsl:text><xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:item//stxt:para">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:list">
<xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:list[@ordered='true']/stxt:item"><xsl:text>
</xsl:text><xsl:number format="1. "/><xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:list[@ordered='false']/stxt:item">
 * <xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="stxt:term"><xsl:text>
</xsl:text><xsl:apply-templates/>:</xsl:template>

	<xsl:template match="stxt:definition">
	-- <xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:code">

----------------------------------------------------------
<xsl:value-of select="."/>
----------------------------------------------------------
</xsl:template>

	<xsl:template match="stxt:blockquote">

&gt;&gt; <xsl:apply-templates/>
</xsl:template>
	
	<!-- inline -->
	<xsl:template match="stxt:link"><xsl:value-of select="."/> (<xsl:value-of select="@href"/>)</xsl:template>

	<xsl:template match="stxt:image"><xsl:value-of select="@title"/> (<xsl:value-of select="@href"/>)</xsl:template>

	<xsl:template match="stxt:bold">*<xsl:apply-templates/>*</xsl:template>

	<xsl:template match="stxt:italic">_<xsl:apply-templates/>_</xsl:template>

	<xsl:template match="stxt:monospace">`<xsl:apply-templates/>`</xsl:template>

	<xsl:template match="stxt:superscript">[<xsl:apply-templates/>]</xsl:template>

	<xsl:template match="stxt:subscript">(<xsl:apply-templates/>)</xsl:template>
</xsl:stylesheet>