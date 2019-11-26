package md2html;

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
        for (int i = 0; i < this.stringBuilder.length(); i++) {
            char current = this.stringBuilder.charAt(i);
            String tag;
            switch (current) {
                case '<':
                    tag = "&lt;";
                    break;
                case '>':
                    tag = "&gt;";
                    break;
                case '&':
                    tag = "&amp;";
                    break;
                default:
                    tag = Character.toString(current);
                    break;
            }
            stringBuilder.append(tag);
        }
    }
}
