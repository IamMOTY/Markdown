package MarkdownToHtml;

public interface Markup {
    void toMarkdown(StringBuilder stringBuilder);

    void toTex(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);
}
