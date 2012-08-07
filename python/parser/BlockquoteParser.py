class BlockquoteParser (BlockParser):

    def parse(self, buffer):
        typePattern = Pattern.compile("^\\s*>{2,}\\s*(.*)\\s+", Pattern.DOTALL)
        match = typePattern.matcher(buffer)
        
        if !match.find():
            raise StxtException("Illegal blockquote format")
            
        blockquote = Blockquote()

        content = match.group(1)

        scanner = BlockScanner()
        blockquote.appendChildren(scanner.scan(content))
        
        return blockquote
