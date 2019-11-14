package markup;

public class Text implements Markup {
    private StringBuilder stringBuilder;

    public Text(String string) {
        this.stringBuilder = new StringBuilder(string);
    }

    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
        return stringBuilder;
    }

    public StringBuilder toTex(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
        return stringBuilder;
    }


    public StringBuilder toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
        return stringBuilder;
    }
}
