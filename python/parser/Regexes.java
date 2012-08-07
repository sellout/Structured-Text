package com.valentsolutions.stxt.parser;

/**
 * LB = lookbehind
 * LA = lookahead
 */
public class Regexes
{
    public static final String NEWLINE = "(?:\\r\\n?+|\\n)";
    public static final String NEWLINES = "(?:" + NEWLINE + "(?:\\s*" + NEWLINE +")+)";

    // ================== BLOCK ===============================================

    public static final String BLOCK_LB = "(?<=\\A|(?:" + NEWLINE + "(?:\\s{0,99}" + NEWLINE + ")))";
    public static final String BLOCK_END_LA = "(?=" + NEWLINES + "|\\Z)";
//    public static final String BLOCK_CONTAINER_END = "(?=(?:" + NEWLINES + "\\s{0," + indent + "}\\S)|\\Z)";

    public static final String HEADING_START = "\\s*=+\\s";
    public static final String HEADING_END = BLOCK_END_LA;

    public static final String LIST_START = "\\s*(?=[\\*|#]\\s+)";
//    public static final String LIST_END = BLOCK_CONTAINER_END;

    public static final String CODE_START = "\\s*:{2,}\\s*" + NEWLINE;
    public static final String CODE_END = NEWLINE + "\\s*:{2,}\\s*" + BLOCK_END_LA;

    public static final String BLOCKQUOTE_START = "\\s*>{2,}\\s+";
//    public static final String BLOCKQUOTE_END = BLOCK_CONTAINER_END;

    public static final String DEFLIST_START = "\\s*;\\s+";
    public static final String DEFLIST_END = BLOCK_END_LA;

    public static final String PARA_START = "(?:\\s*\\\\(?=|\\*|#|:|>|;)|\\s*(?=\\S))";
    public static final String PARA_END = BLOCK_END_LA;

    // ================== INLINE ==============================================

    public static final String INLINE_LB = "(?<=\\A|\\s|[\\p{P}&&[^\\p{Pe}]])";
    public static final String INLINE_LA = "(?=\\S)";
    public static final String EINLINE_LB = "(?<=[\\S&&[^\\\\]])";
    public static final String EINLINE_LA = "(?=\\s|[\\p{P}&&[^\\p{Ps}]]|\\Z)";

    public static final String BOLD = "\\*";
    public static final String ITALIC = "\\_";
    public static final String SUPERSCRIPT = "\\^";
    public static final String SUBSCRIPT = "\\~";
    public static final String MONOSPACE = "\\:";
    public static final String LINK_START = "\\[";
    public static final String LINK_END = "\\]";
    public static final String IMAGE_START = "\\{";
    public static final String IMAGE_END = "\\}";
    
    public static final String IGNORED_ELEMENT = "\\\\";
}
