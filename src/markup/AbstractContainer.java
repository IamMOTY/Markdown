package markup;

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

        public String getTag() {
            return tag;
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

    public StringBuilder toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(HtmlTag.valueOf(type).getPrefix());
        for (ListItem entry : list) {
            stringBuilder = entry.toHtml(stringBuilder);
        }
        stringBuilder.append(HtmlTag.valueOf(type).getSuffix());
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        stringBuilder.append(TexTag.valueOf(type).getPrefix());
        render(stringBuilder, Container::toTex);
        stringBuilder.append(TexTag.valueOf(type).getSuffix());
        return stringBuilder;
    }

    private void render(StringBuilder stringBuilder, final BiConsumer<ListItem, StringBuilder> biConsumer) {
        for (ListItem entry : list) {
            biConsumer.accept(entry, stringBuilder);
        }
    }


}
