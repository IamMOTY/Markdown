package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkup {
    UnorderedList(List<Markup> list) {
        super(list, "UnorderedList");
    }
}
