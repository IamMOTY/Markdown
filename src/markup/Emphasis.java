package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup implements Markup {
    public Emphasis(List<Markup> list) {
        super(list, "Emphasis");
    }
}
