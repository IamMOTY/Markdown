package markup;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements Container {
    private List<Markup> list = new ArrayList<>();

    public Paragraph(List<Markup> list) {
        this.list = list;
    }

    StringBuilder toMarkdown(StringBuilder stringBuilder) {
        for (Markup entry : list) {
            stringBuilder = entry.toMarkdown(stringBuilder);
        }
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        for (Markup entry : list) {
            stringBuilder = entry.toTex(stringBuilder);
        }
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        for (Markup entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        return stringBuilder;
    }
}
