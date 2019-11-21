package MarkdownToHtml;

public class Text implements Markup {
    private StringBuilder stringBuilder;


    public Text(String string) {
        this.stringBuilder = new StringBuilder(string);
    }
    public Text(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
    }

    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
    }

    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(this.stringBuilder);
    }
}
