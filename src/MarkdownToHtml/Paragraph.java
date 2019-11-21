package MarkdownToHtml;

import java.util.List;

public class Paragraph extends AbstractMarkup implements Container {
    public Paragraph(List<Markup> list) {
        super(list, "PARAGRAPH");
    }
}
