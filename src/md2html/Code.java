package md2html;

import java.util.List;

public class Code extends AbstractMarkup implements Markup {
    public Code(List<Markup> list) {
        super(list, "CODE");
    }
}
