class HtmlOutputter (XmlOutputter):

    FORMAT = Format.HTML;
    embedStylesheet;
    
    def __init__():
        super(FORMAT)

    def process_document(self, document, out):
        if embedStylesheet:
            style_content = ""
            characters = new char[1024]
            prefix = new URL("file:///" + (new File(".")).getAbsolutePath())
            stylesheetUrl = new URL(prefix, stylesheet)
            reader = new InputStreamReader(stylesheetUrl.openStream())

            while reader.read(characters) != -1:
                styleContent += new String(characters)

            self.outputter.set_parameter("stylesheet_content", styleContent)
        }

        self.outputter.process_document(document, out)
