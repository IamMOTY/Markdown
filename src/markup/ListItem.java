package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.zip.CheckedOutputStream;

public class ListItem implements MarkupableContainer {

    private final String HTML_PREFIX = "<li>";
    private final String HTML_SUFFIX = "</li>";
    private final String TEX_PREFIX = "\\item ";
    private final String TEX_SUFFIX = "";

    List<Container> list;

    public ListItem(List<Container> list) {
        this.list = list;
    }

    @Override
    public void toTex(StringBuilder stringBuilder) {
        render(stringBuilder, TEX_PREFIX, TEX_SUFFIX, Container::toTex);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        render(stringBuilder, HTML_PREFIX, HTML_SUFFIX, Container::toHtml);
    }

    private void render(StringBuilder stringBuilder, final String prefix, final String suffix, final BiConsumer<Container, StringBuilder> biConsumer) {
        for (Container entry : list) {
            stringBuilder.append(prefix);
            biConsumer.accept(entry, stringBuilder);
            stringBuilder.append(suffix);
        }
    }
}
