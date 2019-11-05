package markup;

import java.util.List;

public class ListItem extends AbstractMarkup {


    ListItem(List<Markup> list) {
        super(list, "ListItem");
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        for (Markup entry : list) {
            stringBuilder.append("\\item ");
            stringBuilder = entry.toTex(stringBuilder);
        }
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        for (Markup entry : list) {
            stringBuilder.append("<li>");
            stringBuilder = entry.toHtml(stringBuilder);
            stringBuilder.append("</li>");
        }
        return stringBuilder;
    }


}
