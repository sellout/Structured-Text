<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:xlink="http://www.w3.org/1999/xlink"
				xmlns:stxt="http://valentsoftware.com/stxt"
				version="1.0">
	<xsl:output method="text"
	            encoding="UTF-8"/>

	<xsl:template match="stxt:stxtDocument">\documentclass{article}
\usepackage{hyperref}
<xsl:apply-templates select="stxt:metadata" />
\begin{document}
<xsl:if test="stxt:metdata/stxt:header[@key='title']">
\maketitle
</xsl:if>
<xsl:apply-templates select="stxt:body" />
\end{document}
\end
</xsl:template>

	<xsl:template match="stxt:metadata">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='title']">
\title{<xsl:value-of select="."/>}
</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='date']">
\date{<xsl:value-of select="."/>}
</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='author']">
\author{<xsl:value-of select="."/>}
</xsl:template>

	<xsl:template match="stxt:metadata/stxt:header[@key='revision']">
\revision{<xsl:value-of select="."/>}	
</xsl:template>

	<xsl:template match="stxt:body"><xsl:apply-templates/></xsl:template>

	<!-- block -->
	<xsl:template match="stxt:heading[@level=1]">
\section{<xsl:apply-templates/>}
</xsl:template>

	<xsl:template match="stxt:heading[@level=2]">
\subsection{<xsl:apply-templates/>}
</xsl:template>

	<xsl:template match="stxt:heading[@level>=3]">
\subsubsection{<xsl:apply-templates/>}
</xsl:template>

	<xsl:template match="stxt:para"><xsl:text>

</xsl:text>
<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="stxt:list[@ordered='true']">
\begin{enumerate}<xsl:apply-templates select="stxt:item"/>
\end{enumerate}
</xsl:template>

	<xsl:template match="stxt:list[@ordered='false']">
\begin{itemize}<xsl:apply-templates select="stxt:item"/>
\end{itemize}
</xsl:template>
	
	<xsl:template match="stxt:item">
\item <xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:deflist">
\begin{description}<xsl:apply-templates select="stxt:entry/stxt:term | stxt:entry/stxt:definition"/>
\end{description}
</xsl:template>
	
	<xsl:template match="stxt:term">
\item[<xsl:apply-templates/>] </xsl:template>

	<xsl:template match="stxt:definition"><xsl:apply-templates/>
</xsl:template>

	<xsl:template match="stxt:code">
\begin{verbatim}
<xsl:value-of select="."/>
\end{verbatim}
</xsl:template>

	<xsl:template match="stxt:blockquote">
\begin{quotation}
<xsl:apply-templates/>
\end{quotation}
</xsl:template>
	
	<!-- inline -->
	<xsl:template match="stxt:link">\href{<xsl:value-of select="@href"/>}{<xsl:value-of select="."/>}</xsl:template>

	<xsl:template match="stxt:image">\hyperimage{<xsl:value-of select="@href"/>} (<xsl:value-of select="@title"/>)</xsl:template>

	<xsl:template match="stxt:bold">\textbf{<xsl:apply-templates/>}</xsl:template>

	<xsl:template match="stxt:italic">\textit{<xsl:apply-templates/>}</xsl:template>

	<xsl:template match="stxt:monospace">\texttt{<xsl:apply-templates/>}</xsl:template>

	<xsl:template match="stxt:superscript">
		<sup><xsl:apply-templates/></sup>
	</xsl:template>

	<xsl:template match="stxt:subscript">
		<sub><xsl:apply-templates/></sub>
	</xsl:template>
</xsl:stylesheet>