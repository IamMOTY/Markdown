package md2html;

public interface Markup {
    void toMarkdown(StringBuilder stringBuilder);

    void toTex(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);
}
