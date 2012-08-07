class XmlOutputter (SimpleOutputter):

    stylesheet
    
    def __init__(format):
        super(format)

    def set_stylesheet(self, stylesheet):
        self.stylesheet = stylesheet;
        self.outputter.set_parameter("stylesheet", stylesheet);