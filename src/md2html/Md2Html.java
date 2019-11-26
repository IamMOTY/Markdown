package md2html;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new File(args[1]));

        Block block = new Block(in);

        while (block.getStringBuilder() != null) {
            int level = block.getHeaderLevel();
            if (level != -1) {
                StringBuilder stringBuilder = new StringBuilder(block.getStringBuilder().substring(level + 1));
                Header header = new Header(toObject(stringBuilder), level);
                stringBuilder = new StringBuilder();
                header.toHtml(stringBuilder);
                out.println(stringBuilder);
            } else {
                Paragraph paragraph = new Paragraph(toObject(block.getStringBuilder()));
                StringBuilder stringBuilder = new StringBuilder();
                paragraph.toHtml(stringBuilder);
                out.println(stringBuilder);
            }
            block = new Block(in);
        }
        in.close();
        out.close();
    }

    public static List<Markup> toObject(StringBuilder stringBuilder) {
        List<Markup> list = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.length() - i >= 2 && stringBuilder.charAt(i) == '*' && stringBuilder.charAt(i + 1) == '*') {
                int close = stringBuilder.indexOf("**", i + 2);
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 2, close));
                    list.add(new Strong(toObject(temp)));
                    temp = new StringBuilder();
                    i = close + 1;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.length() - i >= 2 && stringBuilder.charAt(i) == '_' && stringBuilder.charAt(i + 1) == '_') {
                int close = stringBuilder.indexOf("__", i + 2);
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 2, close));
                    list.add(new Strong(toObject(temp)));
                    temp = new StringBuilder();
                    i = close + 1;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.length() - i >= 2 && stringBuilder.charAt(i) == '-' && stringBuilder.charAt(i + 1) == '-') {
                int close = stringBuilder.indexOf("--", i + 2);
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 2, close));
                    list.add(new Strikeout(toObject(temp)));
                    temp = new StringBuilder();
                    i = close + 1;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.charAt(i) == '*') {
                int close = stringBuilder.indexOf("*", i + 1);
                while (stringBuilder.length() - close >= 2 && close != -1 && (stringBuilder.charAt(close - 1) == '\\' || stringBuilder.charAt(close + 1) == '*')) {
                    close = stringBuilder.indexOf("*", close + 1 + ((stringBuilder.charAt(close + 1) == '*') ? 1 : 0));
                }
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 1, close));
                    list.add(new Emphasis(toObject(temp)));
                    temp = new StringBuilder();
                    i = close;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.charAt(i) == '_') {
                int close = stringBuilder.indexOf("_", i + 1);
                while (stringBuilder.length() - close >= 2 && close != -1 && (stringBuilder.charAt(close - 1) == '\\' || stringBuilder.charAt(close + 1) == '_')) {
                    close = stringBuilder.indexOf("_", close + 1 + ((stringBuilder.charAt(close + 1) == '_') ? 1 : 0));
                }
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 1, close));
                    list.add(new Emphasis(toObject(temp)));
                    temp = new StringBuilder();
                    i = close;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.charAt(i) == '\\') {
                i++;
                temp.append(stringBuilder.charAt(i));
            } else if (stringBuilder.length() - i >= 2 && stringBuilder.charAt(i) == '`') {
                int close = stringBuilder.indexOf("`", i + 1);
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    temp = new StringBuilder(stringBuilder.substring(i + 1, close));
                    list.add(new Code(toObject(temp)));
                    temp = new StringBuilder();
                    i = close;
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else if (stringBuilder.charAt(i) == '[') {
                int close = stringBuilder.indexOf("]", i + 1);
                if (close != -1) {
                    if (temp.length() > 0) {
                        list.add(new Text(temp));
                    }
                    StringBuilder text = new StringBuilder(stringBuilder.substring(i + 1, close));
                    temp = new StringBuilder(stringBuilder.substring(close + 2, stringBuilder.indexOf(")", close + 2)));
                    list.add(new Link(toObject(text), new Text(temp)));
                    temp = new StringBuilder();
                    i = stringBuilder.indexOf(")", close + 2);
                } else {
                    temp.append(stringBuilder.charAt(i));
                }
            } else {
                temp.append(stringBuilder.charAt(i));
            }
        }
        if (temp.length() > 0) {
            list.add(new Text(temp));
            temp = new StringBuilder();
        }
        return list;
    }



    private static StringBuilder appendStrong(StringBuilder stringBuilder, List<Markup> list, StringBuilder temp, int i, int close) {
        if (close != -1) {
            list.add(new Text(temp));
            temp = new StringBuilder(stringBuilder.substring(i + 1, close));
            list.add(new Strong(toObject(temp)));
            temp = new StringBuilder();
        } else {
            temp.append(stringBuilder.charAt(i));
        }
        return temp;
    }

    private static StringBuilder appendStrikeout(StringBuilder stringBuilder, List<Markup> list, StringBuilder temp, int i, int close) {
        if (close != -1) {
            list.add(new Text(temp));
            temp = new StringBuilder(stringBuilder.substring(i + 1, close));
            list.add(new Strikeout(toObject(temp)));
            temp = new StringBuilder();
        } else {
            temp.append(stringBuilder.charAt(i));
        }
        return temp;
    }

    private static StringBuilder appendEmphasis(StringBuilder stringBuilder, List<Markup> list, StringBuilder temp, int i, int close) {
        if (close != -1) {
            list.add(new Text(temp));
            temp = new StringBuilder(stringBuilder.substring(i + 1, close));
            list.add(new Emphasis(toObject(temp)));
            temp = new StringBuilder();
        } else {
            temp.append(stringBuilder.charAt(i));
        }
        return temp;
    }


}
