package MarkdownToHtml;

import java.io.IOException;

public class Block {
    private StringBuilder stringBuilder = new StringBuilder();

    Block(Scanner scanner) throws IOException {
        String string;
        if (scanner.hasNextLine()) {
            string = scanner.nextLine();
            while (string.equals("")) {
                if (scanner.hasNextLine()) {
                    string = scanner.nextLine();
                } else {
                    this.stringBuilder = null;
                    return;
                }
            }
            while (!string.equals("")) {
                this.stringBuilder.append(string);
                this.stringBuilder.append('\n');
                if (scanner.hasNextLine()) {
                    string = scanner.nextLine();
                } else {
                    return;
                }
            }
        } else {
            this.stringBuilder = null;
            return;
        }
    }

    public int getHeaderLevel() {
        if (stringBuilder.charAt(0) == '#') {
            int i = 1;
            while (stringBuilder.charAt(i) == '#') {
                i++;
            }
            if (stringBuilder.charAt(i) == ' ') {
                return i;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public boolean isParagraph() {
        return getHeaderLevel() == -1;
    }

    public boolean isHeader() {
        return getHeaderLevel() != -1;
    }

    StringBuilder getStringBuilder() {
        return this.stringBuilder;
    }
}
