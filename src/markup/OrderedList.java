package markup;

import java.util.List;

public class OrderedList extends AbstractContainer {
    OrderedList(List<ListItem> list) {
        super(list, "ORDERED_LIST");
    }
}
