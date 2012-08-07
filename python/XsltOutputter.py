# @inv this.transformer != null

class XsltOutputter (StxtOutputter):
    "Translates an StxtDocument to some other format as defined in an XSLT
     stylesheet."

    transformer

    def __init__(stylesheet):
        "Creates a new outputter with the given stylesheet. If the stylesheet is
         null, we just output our internal XML format."
    
        factory = TransformerFactory.newInstance()

        if stylesheet != null:
            self.transformer = factory.newTransformer(new StreamSource(stylesheet))
        else
            this.transformer = factory.newTransformer()

    def set_parameter(self, parameter, value):
        self.transformer.setParameter(parameter, value)

    def process_document(self, document, out):
        self.transformer.transform(new DOMSource(document.toDOM()),
                                       new StreamResult(out))
