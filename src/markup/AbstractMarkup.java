package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractMarkup implements Markup {
    protected List<Markup> list = new ArrayList<>();
    private String type;
    private Map<String, List<String>> markdownAdding = Map.of("Emphasis", List.of("*", "*"), "Strong", List.of("__", "__"), "Strikeout", List.of("~", "~"), "Paragraph", List.of("", ""));
    private Map<String, List<String>> TexAdding = Map.of("Emphasis", List.of("\\emph{", "}"), "Strong", List.of("\\textbf{", "}"), "Strikeout", List.of("\\textst{", "}"), "Paragraph", List.of("", ""), "OrderedList", List.of("\\begin{enumerate}", "\\end{enumerate}"), "UnorderedList", List.of("\\begin{itemize}", "\\end{itemize}"));
    private Map<String, List<String>> HtmlAdding = Map.of("Emphasis", List.of("<em>", "</em>"), "Strong", List.of("<strong>", "</strong>"), "Strikeout", List.of("<s>", "</s>"), "Paragraph", List.of("", ""), "OrderedList", List.of("<ol>", "</ol>"), "UnorderedList", List.of("<ul>", "</ul>"));


    AbstractMarkup(List<Markup> list, String type) {
        this.list = list;
        this.type = type;
    }

    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        List<String> adding = markdownAdding.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toMarkdown(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        List<String> adding = TexAdding.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toTex(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        List<String> adding = HtmlAdding.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }
}
