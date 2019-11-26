package md2html;

import java.util.List;

public class Header {
    private List <Markup> list;
    private int level;

    Header (List<Markup> list, int level) {
        this.list = list;
        this.level = level;
    }

    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(getPrefix());
        for (Markup entry : list) {
            entry.toHtml(stringBuilder);
        }
        stringBuilder.append(getSuffix());
    }

    private String getPrefix() {
        return "<h" + Integer.toString(level) + ">";
    }

    private String getSuffix() {
        return  "</h" + Integer.toString(level) + ">";
    }

}
