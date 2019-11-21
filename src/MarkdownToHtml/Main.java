package MarkdownToHtml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new File(args[1]));

        Block block = new Block(in);

        while (block.getStringBuilder() != null) {
            out.println(block.getStringBuilder());
            block = new Block(in);
        }
        out.close();
    }
}
