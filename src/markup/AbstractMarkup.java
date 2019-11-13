package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public abstract class AbstractMarkup {
    private enum MarkdownTag {
        EMPHASIS ("*"),
        STRONG ("__"),
        STRIKEOUT ("~"),
        PARAGRAPH ("");

        private String tag;

        MarkdownTag (String tag) {
            this.tag = tag;
        }

        public String getTag(){
            return tag;
        }

        public String getPrefix(){
            return tag;
        }

        public String getSuffix(){
            return tag;
        }
    }

    private enum TexTag {
        EMPHASIS ("emph"),
        STRONG ("textbf"),
        STRIKEOUT ("textst"),
        PARAGRAPH ("");

        String tag;

        TexTag (String tag) {
            this.tag = tag;
        }

        public String getTag(){
            return tag;
        }

        public String getPrefix(){
            if (tag.length() == 0) {
                return tag;
            } else {
                return "\\" + tag + "{";
            }
        }

        public String getSuffix(){
            if (tag.length() == 0) {
                return tag;
            } else {
                return "}";
            }
        }
    }

    public enum HtmlTag {
        EMPHASIS ("em"),
        STRONG ("strong"),
        STRIKEOUT ("s"),
        PARAGRAPH ("");

        String tag;

        HtmlTag (String tag) {
            this.tag = tag;
        }

        public String getTag(){
            return tag;
        }

        public String getPrefix(){
            if (tag.length() == 0) {
                return tag;
            } else {
                return "<" + tag + ">";
            }
        }

        public String getSuffix(){
            if (tag.length() == 0) {
                return tag;
            } else {
                return "</" + tag + ">";
            }
        }
    }

    protected List<Markup> list;
    private final String type;

    AbstractMarkup(List<Markup> list, String type) {
        this.list = list;
        this.type = type;
    }



    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(MarkdownTag.valueOf(type).getPrefix());
        for (Markup entry : list) {
            stringBuilder = entry.toMarkdown(stringBuilder);
        }
        stringBuilder.append(MarkdownTag.valueOf(type).getSuffix());
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        stringBuilder.append(TexTag.valueOf(type).getPrefix());
        for (Markup entry : list) {
            stringBuilder = entry.toTex(stringBuilder);
        }
        stringBuilder.append(TexTag.valueOf(type).getSuffix());
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(HtmlTag.valueOf(type).getPrefix());
        for (Markup entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        stringBuilder.append(HtmlTag.valueOf(type).getSuffix());
        return stringBuilder;
    }
}
