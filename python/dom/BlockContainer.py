# @inv children != null
# @inv forall Object object in this.children
#      | object instanceof Block

class BlockContainer:
    "Represents an element that is capable of containing Block elements as children."

    children = new ArrayList()

    def getElementName():
        "Returns the name of this element as used in an XML document."

    # @pre child != null
    def append_child(self, child):
        self.children.add(child)

    # @pre children != null
    def append_children(self, children):
        for (int i = 0; i < children.length; ++i)
            this.children.add(children[i])

    # @post return != null
    def get_children(self):
        return self.children

    # @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
    def to_DOM(self, document):
        dom_element = document.createElementNS(StxtElement.NAMESPACE_URI, self.get_element_name())
        child_it = self.children.iterator()
        while (child_it.hasNext()):
            child = child_it.next()
            dom_element.append_child(child.to_DOM(document))

        return dom_element
    
    def __eq__(self, other):
        are_equal = isinstance(other, type(self))

        if are_equal:
            this_children = self.get_children()
            other_children = other.get_children()

            are_equal = len(this_children) == len(other_children)

            for (i = 0; are_equal  && i < len(this_children); ++i):
                are_equal = this_children[i] == other_children[i]
    	
    	return are_equal
