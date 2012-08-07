class StxtProcessor:
    "Takes either a stxt file or an XML file conforming to the stxt schema via standard in, and translates it, sending the output to standard out. This is the most basic of the UIs."

    parser = StxtParser()
    outputter

    def __init__(format):
        self.outputter = SimpleOutputter.makeOutputter(format)
        return self
        
    def set_template(self, template):
        self.outputter.set_template(template)
        
    def set_stylesheet(self, stylesheet):
        if outputter is XmlOutputter:
            self.outputter.set_stylesheet(stylesheet)
            
    def embed_stylesheet(self, embed):
        if outputter is HtmlOutputter:
            self.outputter.embed_stylesheet(embed)
        
    def process_document(self, in, out):
        document = self.parser.parse(in)
        out = self.outputter.processDocument(document, out)