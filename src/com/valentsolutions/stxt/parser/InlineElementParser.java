package com.valentsolutions.stxt.parser;

import java.lang.reflect.InvocationTargetException;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.InlineElement;
import com.valentsolutions.stxt.dom.InlineMarkup;

/**
 * Write a comment.
 */
public class InlineElementParser
{
    private Class containerClass;
    
    public InlineElementParser()
    {
    }
    
    public InlineElementParser(Class containerClass)
    {
        this.containerClass = containerClass;
    }
    
    public InlineElement parse(String buffer)
        throws StxtException
    {
        InlineMarkup container = null;
        try
        {
            container =
                (InlineMarkup) this.containerClass.getConstructor(null).newInstance(null);
        }
        catch (InstantiationException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }
        catch (InvocationTargetException e)
        {
        }
        catch (NoSuchMethodException e)
        {
        }

        // process the contents of this tag and append child tags
        InlineScanner scanner = new InlineScanner();
        container.appendChildren(scanner.scan(buffer));
        
        return container;
    }
}
