package markup;

public interface MarkupableContainer {
    void toHtml(StringBuilder stringBuilder);

    void toTex(StringBuilder stringBuilder);
}
