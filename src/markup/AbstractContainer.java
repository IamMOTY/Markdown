package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractContainer implements Container {
    protected List<ListItem> list = new ArrayList<>();
    private final String type;
    private static final Map<String, List<String>> TEX_ADDING = Map.of("OrderedList", List.of("\\begin{enumerate}", "\\end{enumerate}"), "UnorderedList", List.of("\\begin{itemize}", "\\end{itemize}"));
    private static final Map<String, List<String>> HTML_ADDING = Map.of( "OrderedList", List.of("<ol>", "</ol>"), "UnorderedList", List.of("<ul>", "</ul>"));

    AbstractContainer(List<ListItem> list, String type) {
        this.list = list;
        this.type = type;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        List<String> adding = HTML_ADDING.get(type);
        stringBuilder.append(adding.get(0));
        for (ListItem entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }
    public StringBuilder toTex(StringBuilder stringBuilder) {
        List<String> adding = TEX_ADDING.get(type);
        stringBuilder.append(adding.get(0));
        for (ListItem entry : list) {
            stringBuilder = entry.toTex(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }
}
