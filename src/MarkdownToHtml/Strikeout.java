package MarkdownToHtml;

import java.util.List;

public class Strikeout extends AbstractMarkup implements Markup {
    public Strikeout(List<Markup> list) {
        super(list, "STRIKEOUT");
    }
}
