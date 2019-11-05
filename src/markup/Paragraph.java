package markup;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends AbstractMarkup implements Container {
    private List<Markup> list = new ArrayList<>();

    public Paragraph(List<Markup> list) {
        super(list, "Paragraph");
    }
}
