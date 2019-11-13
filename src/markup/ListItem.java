package markup;

import java.util.ArrayList;
import java.util.List;

public class ListItem {

    List<Container> list;

    public ListItem(List<Container> list) {
        this.list = list;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        for (Container entry : list) {
            stringBuilder.append("\\item ");
            stringBuilder = entry.toTex(stringBuilder);
        }
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        for (Container entry : list) {
            stringBuilder.append("<li>");
            stringBuilder = entry.toHtml(stringBuilder);
            stringBuilder.append("</li>");
        }
        return stringBuilder;
    }


}
