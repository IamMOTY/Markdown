package markup;

public interface MarkupableContainer {
    StringBuilder toHtml(StringBuilder stringBuilder);

    StringBuilder toTex(StringBuilder stringBuilder);
}
