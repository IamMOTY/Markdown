package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractMarkup {
    protected List<Markup> list = new ArrayList<>();
    private final String type;
    private static final Map<String, List<String>> MARKDOWN_ADDING = Map.of("Emphasis", List.of("*", "*"), "Strong", List.of("__", "__"), "Strikeout", List.of("~", "~"), "Paragraph", List.of("",""));
    private static final Map<String, List<String>> TEX_ADDING = Map.of("Emphasis", List.of("\\emph{", "}"), "Strong", List.of("\\textbf{", "}"), "Strikeout", List.of("\\textst{", "}"), "Paragraph", List.of("",""));
    private static final Map<String, List<String>> HTML_ADDING = Map.of("Emphasis", List.of("<em>", "</em>"), "Strong", List.of("<strong>", "</strong>"), "Strikeout", List.of("<s>", "</s>"), "Paragraph", List.of("",""));

    AbstractMarkup(List<Markup> list, String type) {
        this.list = list;
        this.type = type;
    }

    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        List<String> adding = MARKDOWN_ADDING.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toMarkdown(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        List<String> adding = TEX_ADDING.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toTex(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        List<String> adding = HTML_ADDING.get(type);
        stringBuilder.append(adding.get(0));
        for (Markup entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        stringBuilder.append(adding.get(1));
        return stringBuilder;
    }
}
