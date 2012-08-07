package com.valentsolutions.stxt;

import com.valentsolutions.stxt.dom.*;

/**
 * 
 */
public class DocumentFactory
{
    public static StxtDocument makeSampleDocument()
    {
        StxtDocument document = new StxtDocument();

		Metadata metadata = makeSampleMetadata();
		document.setMetadata(metadata);

        Body body = makeSampleBody();
        document.setBody(body);

        return document;
    }
    
    /**
	 * @return
	 */
	public static Metadata makeSampleMetadata() {
		return new Metadata();
	}

	public static Body makeSampleBody()
    {
		Body body = new Body();

		int level = 2;
		body.appendChild(makeHeading(level));
		body.appendChild(makeSamplePara());
		body.appendChild(makeBlockquote());
		body.appendChild(makeCode());
		boolean ordered = true;
		body.appendChild(makeList(ordered));
		body.appendChild(makeDefList());
		
		return body;
    }

    public static DefList makeDefList()
    {
        DefList deflist = new DefList();
        Term term1 = new Term();
        term1.appendChild(new Text("term 1"));
        Entry entry1 = new Entry(term1);
        Definition def1 = new Definition();
        def1.appendChild(new Text("def 1"));
        entry1.appendDefinition(def1);
        deflist.appendChild(entry1);
        Term term2 = new Term();
        term2.appendChild(new Text("term 2"));
        Entry entry2 = new Entry(term2);
        Definition def2 = new Definition();
        def2.appendChild(new Text("def 2"));
        entry2.appendDefinition(def2);
        Definition def3 = new Definition();
        def3.appendChild(new Text("def 3"));
        entry2.appendDefinition(def3);
        deflist.appendChild(entry2);
        return deflist;
    }

    public static List makeList(boolean ordered)
    {
        List list = new List(ordered);

        Para para1 = new Para();
        para1.appendChild(new Text("item 1"));
        Item item1 = new Item();
        item1.appendChild(para1);
        
        Para para2 = new Para();
        para2.appendChild(new Text("item 2"));
        Item item2 = new Item();
        item2.appendChild(para2);

        Para para3 = new Para();
        para3.appendChild(new Text("item 3"));
        Item item3 = new Item();
        item3.appendChild(para3);

        list.appendChild(item1);
        list.appendChild(item2);
        list.appendChild(item3);
        return list;
    }

    public static Code makeCode()
    {
        Code code = new Code();
        code.appendChild("code text");
        return code;
    }

    public static Blockquote makeBlockquote()
    {
        Blockquote blockquote = new Blockquote();
        Para bqPara = new Para();
        bqPara.appendChild(new Text("blockquote text"));
        blockquote.appendChild(bqPara);
        return blockquote;
    }

    public static Para makeSamplePara()
    {
        Para para = new Para();
        para.appendChild(new Text("para text"));
        Bold bold = new Bold();
        bold.appendChild(new Text("bold text"));
        para.appendChild(bold);
        Italic italic = new Italic();
        italic.appendChild(new Text("italic text"));
        para.appendChild(italic);
        Superscript superscript = new Superscript();
        superscript.appendChild(new Text("super text"));
        para.appendChild(superscript);
        Subscript subscript = new Subscript();
        subscript.appendChild(new Text("sub text"));
        para.appendChild(subscript);
        Monospace monospace = new Monospace();
        monospace.appendChild(new Text("mono text"));
        para.appendChild(monospace);
        Link link = new Link("URI");
        link.appendChild(new Text("link text"));
        para.appendChild(link);
        Image image = new Image("URI");
        image.setTitle("alt text");
        para.appendChild(image);
        para.appendChild(new Text("trailing para text"));
        return para;
    }

    /**
     * @pre level >= 1
     */
    public static Heading makeHeading(int level)
    {
        Heading heading = new Heading(level);
        heading.appendChild(new Text("heading text"));
        return heading;
    }
}