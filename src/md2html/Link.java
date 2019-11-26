package md2html;

import java.util.List;

public class Link implements Markup {

    private Text link;
    private List<Markup> list;

    Link(List<Markup> list, Text link) {
        this.list = list;
        this.link = link;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {

    }

    @Override
    public void toTex(StringBuilder stringBuilder) {

    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        final String PREFIX = "<a href='";
        final String MIDDLE = "'>";
        final String SUFFIX = "</a>";


        stringBuilder.append(PREFIX);
        link.toHtml(stringBuilder);
        stringBuilder.append(MIDDLE);
        for (Markup entry: list) {
            entry.toHtml(stringBuilder);
        }
        stringBuilder.append(SUFFIX);
    }
}
