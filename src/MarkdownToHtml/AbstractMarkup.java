package MarkdownToHtml;

import java.util.List;
import java.util.function.BiConsumer;


public abstract class AbstractMarkup {
    private enum MarkdownTag {
        EMPHASIS("*"),
        STRONG("__"),
        STRIKEOUT("~"),
        PARAGRAPH("");

        private String tag;

        MarkdownTag(String tag) {
            this.tag = tag;
        }

        public String getPrefix() {
            return tag;
        }

        public String getSuffix() {
            return tag;
        }
    }

    private enum TexTag {
        EMPHASIS("emph"),
        STRONG("textbf"),
        STRIKEOUT("textst"),
        PARAGRAPH("");

        private String tag;

        TexTag(String tag) {
            this.tag = tag;
        }

        public String getPrefix() {
            if (tag.length() == 0) {
                return tag;
            } else {
                return "\\" + tag + "{";
            }
        }

        public String getSuffix() {
            if (tag.length() == 0) {
                return tag;
            } else {
                return "}";
            }
        }
    }

    private enum HtmlTag {
        EMPHASIS("em"),
        STRONG("strong"),
        STRIKEOUT("s"),
        PARAGRAPH("p");

        private String tag;

        HtmlTag(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        public String getPrefix() {
            if (tag.length() == 0) {
                return tag;
            } else {
                return "<" + tag + ">";
            }
        }

        public String getSuffix() {
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

    public void toMarkdown(StringBuilder stringBuilder) {
        render(stringBuilder, MarkdownTag.valueOf(type).getPrefix(), MarkdownTag.valueOf(type).getSuffix(), Markup::toMarkdown);
    }

    public void toTex(StringBuilder stringBuilder) {
        render(stringBuilder, TexTag.valueOf(type).getPrefix(), TexTag.valueOf(type).getSuffix(), Markup::toTex);
    }

    public void toHtml(StringBuilder stringBuilder) {
        render(stringBuilder, HtmlTag.valueOf(type).getPrefix(), HtmlTag.valueOf(type).getSuffix(), Markup::toHtml);
    }

    private void render(StringBuilder stringBuilder, final String prefix, final String postfix, final BiConsumer<Markup, StringBuilder> biConsumer) {
        stringBuilder.append(prefix);
        for (Markup entry : list) {
            biConsumer.accept(entry, stringBuilder);
        }
        stringBuilder.append(postfix);
    }
}
