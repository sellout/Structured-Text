# @inv this.outputter != null

class SimpleOutputter (StxtOutputter):
    "This outputter provides a number of fairly standard formats in a way that
     does not require an external stylesheet."

    class Format
        "An enumeration of the file formats this outputter is capable of
         producing."
    {
        /** XHTML 1.1 compliant HTML (http://www.w3.org/TR/xhtml11/) */
        public static final Format HTML = new Format("etc/stxt2html.xsl",
                                                     "html");
        /** LaTeX 2e compliant TeX */
        public static final Format LATEX = new Format("etc/stxt2latex.xsl",
                                                      "tex");
        /** STXT Schema */
        public static final Format RAW = new Format(null, "xml");
        /** RTF 1.5 compliant RTF */
        public static final Format RTF = new Format("etc/stxt2rtf.xsl", "rtf");
        /** STXT compliant with this distribution of the engine */
        public static final Format STXT = new Format("etc/stxt2stxt.xsl",
                                                     "stxt");
        /** UTF-8 text file, with ASCII formatting*/
        public static final Format TEXT = new Format("etc/stxt2txt.xsl", "txt");
       
//        private URL stylesheet;
        private File stylesheet;
        private String extension;
        
        def __init__(fileName, extension):
            if fileName == null:
                self.stylesheet = null
            else
                self.stylesheet = new File(fileName)
                
            self.extension = extension
        
        def get_file(self)
            return self.stylesheet
        
        def from_string(string):
            format

            if string == null || string.compareToIgnoreCase("raw") == 0:
                format = Format.RAW
            else if (string.compareToIgnoreCase("html") == 0
                    || string.compareToIgnoreCase("xhtml") == 0):
                format = Format.HTML
            else if (string.compareToIgnoreCase("latex") == 0
                     || string.compareToIgnoreCase("tex") == 0):
                format = Format.LATEX
            else if string.compareToIgnoreCase("rtf") == 0:
                format = Format.RTF
            else if string.compareToIgnoreCase("stxt") == 0:
                format = Format.STXT
            else if (string.compareToIgnoreCase("text") == 0
                     || string.compareToIgnoreCase("txt") == 0):
                format = Format.TEXT
            else
                throw new StxtException("'" + string + "' is not a valid output format")
            
            return format

        def getExtension():
            "Returns the file extennsion."
        
            return extension

    outputter;

	# @pre format != null
    def __init__(format):
        if (format.getFile() == null)
            outputter = new XsltOutputter(null)
        else
            outputter = new XsltOutputter(new FileReader(format.getFile()))
    
    def make_outputter(format):
        outputter

        if format == Format.HTML:
            outputter = new HtmlOutputter()
        else if format == Format.RAW:
            outputter = new XmlOutputter(Format.RAW)
        else
            outputter = new SimpleOutputter(format)
        
        return outputter

    def set_template(self, template):
        self.outputter.setParameter("template", template)
    
    def process_document(self, document, out)
        self.outputter.process_document(document, out)