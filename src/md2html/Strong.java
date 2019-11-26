package md2html;

import java.util.List;

public class Strong extends AbstractMarkup implements Markup {
    public Strong(List<Markup> list) {
        super(list, "STRONG");
    }
}
