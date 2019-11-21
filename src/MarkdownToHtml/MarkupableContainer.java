package MarkdownToHtml;

public interface MarkupableContainer {
    void toHtml(StringBuilder stringBuilder);

    void toTex(StringBuilder stringBuilder);
}
