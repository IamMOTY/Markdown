package MarkdownToHtml;

import java.util.List;

public class OrderedList extends AbstractContainer {
    public OrderedList(List<ListItem> list) {
        super(list, "ORDERED_LIST");
    }
}
