class BlockParser (object):
    "This is the base class for block parsers (para, list, etc.)."

    # @pre buffer != null
    def parse(self, buffer):
        "Parse the text supplied and create a new block element from it. This
         will also recurse to parse anything inside the block."
