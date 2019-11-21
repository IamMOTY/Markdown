package MarkdownToHtml;

import java.util.List;
import java.util.function.BiConsumer;

public class ListItem implements MarkupableContainer {

    List<Container> list;

    public ListItem(List<Container> list) {
        this.list = list;
    }

    @Override
    public void toTex(StringBuilder stringBuilder) {
        final String TEX_PREFIX = "\\item ";
        final String TEX_SUFFIX = "";
        render(stringBuilder, TEX_PREFIX, TEX_SUFFIX, Container::toTex);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        final String HTML_PREFIX = "<li>";
        final String HTML_SUFFIX = "</li>";
        render(stringBuilder, HTML_PREFIX, HTML_SUFFIX, Container::toHtml);
    }

    private void render(StringBuilder stringBuilder, final String prefix, final String suffix, final BiConsumer<Container, StringBuilder> biConsumer) {
        stringBuilder.append(prefix);
        for (Container entry : list) {
            biConsumer.accept(entry, stringBuilder);
        }
        stringBuilder.append(suffix);
    }
}
