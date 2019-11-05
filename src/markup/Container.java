package markup;

public interface Container {
    StringBuilder toHtml(StringBuilder stringBuilder);
    StringBuilder toTex(StringBuilder stringBuilder);
}
