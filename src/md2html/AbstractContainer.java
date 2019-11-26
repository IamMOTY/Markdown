package md2html;

import java.util.List;
import java.util.function.BiConsumer;

public abstract class AbstractContainer implements Container {

    private enum TexTag {
        ORDERED_LIST("enumerate"),
        UNORDERED_LIST("itemize");

        private String tag;

        TexTag(String tag) {
            this.tag = tag;
        }

        public String getPrefix() {
            return "\\begin{" + tag + "}";

        }

        public String getSuffix() {
            return "\\end{" + tag + "}";
        }
    }

    private enum HtmlTag {

        ORDERED_LIST("ol"),
        UNORDERED_LIST("ul");

        private String tag;

        HtmlTag(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        public String getPrefix() {
            return "<" + tag + ">";

        }

        public String getSuffix() {
            return "</" + tag + ">";
        }
    }

    protected List<ListItem> list;
    private final String type;

    AbstractContainer(List<ListItem> list, String type) {
        this.list = list;
        this.type = type;
    }

    public void toHtml(StringBuilder stringBuilder) {
        render(stringBuilder, HtmlTag.valueOf(type).getPrefix(), HtmlTag.valueOf(type).getSuffix(), MarkupableContainer::toHtml);
    }

    public void toTex(StringBuilder stringBuilder) {
        render(stringBuilder, TexTag.valueOf(type).getPrefix(), TexTag.valueOf(type).getSuffix(), MarkupableContainer::toTex);
    }

    private void render(StringBuilder stringBuilder, final String prefix, final String suffix, final BiConsumer<ListItem, StringBuilder> biConsumer) {
        stringBuilder.append(prefix);
        for (ListItem entry : list) {
            biConsumer.accept(entry, stringBuilder);
        }
        stringBuilder.append(suffix);
    }

}
