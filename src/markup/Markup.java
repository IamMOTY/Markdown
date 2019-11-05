package markup;

public interface Markup {
    StringBuilder toMarkdown(StringBuilder stringBuilder);
    StringBuilder toTex(StringBuilder stringBuilder);
    StringBuilder toHtml(StringBuilder stringBuilder);
}
