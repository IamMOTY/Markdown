package MarkdownToHtml;

import java.util.Map;

public class Code implements Markup {

    String string;

    Code(String string) {
        this.string = string;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {

    }

    @Override
    public void toTex(StringBuilder stringBuilder) {

    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        final String PREFIX = "<code>";
        final String SUFFIX = "</code>";
        stringBuilder.append(PREFIX);
        for (int i = 0; i < this.string.length(); i++) {
            char current = this.string.charAt(i);
            String tag;
            switch (current) {
                case '<':
                    tag = "&lt;";
                    break;
                case '>':
                    tag = "&gt;";
                    break;
                case '&':
                    tag = "&amp;";
                    break;
                default:
                    tag = Character.toString(current);
                    break;
            }
            stringBuilder.append(tag);
        }
        stringBuilder.append(SUFFIX);
    }
}
