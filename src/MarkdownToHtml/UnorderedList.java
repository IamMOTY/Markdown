package MarkdownToHtml;

import java.util.List;

public class UnorderedList extends AbstractContainer {
    public UnorderedList(List<ListItem> list) {
        super(list, "UNORDERED_LIST");
    }
}
