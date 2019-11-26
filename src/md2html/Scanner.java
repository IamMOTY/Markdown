package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Scanner {
    private Reader in;
    private final int BUFFER_SIZE = 128;
    private char[] buffer = new char[BUFFER_SIZE];
    private int bufferPointer = 0;
    private int bytesRead = 0;
    private int savedPoint;

    public Scanner(InputStream inputStream) throws IOException {
        in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }

    public Scanner(String source) throws IOException {
        in = new StringReader(source);
    }

    public Scanner(File source) throws IOException {
        in = new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8);
    }

    private  boolean streamEnd() {
        return bufferPointer == bytesRead;
    }

    private void  read() throws IOException {
        do {
            bytesRead = in.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        } while (bytesRead == 0);

    }

    private int nextChar() throws IOException {
        if (streamEnd()) {
            read();
        }

        if (bytesRead == -1) {
            return -1;
        }

        return buffer[bufferPointer++];

    }

    private void setSavedPoint() {
        savedPoint = bufferPointer;
    }

    private void restoreSavedPoint() {
        bufferPointer = savedPoint;
    }

    public boolean hasNext() throws IOException {
        if (streamEnd()) {
            read();
        }

        char currentChar;

        int codeOfChar;

        do {
            codeOfChar = nextChar();
            if (codeOfChar == -1) {
                return false;
            }
            currentChar = (char) codeOfChar;
        } while (Character.isWhitespace(currentChar));

        boolean result = !Character.isWhitespace(currentChar);
        bufferPointer--;
        return result;
    }

    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();

        int codeOfChar = nextChar();

        while (codeOfChar != -1) {
            if (codeOfChar == '\n') {
                return line.toString();
            }

            line.append((char) codeOfChar);
            codeOfChar = nextChar();
        }

        return line.toString();
    }

    public String next() throws IOException{
        StringBuilder element = new StringBuilder();

        hasNext();

        int codeOfChar = nextChar();

        while (!Character.isWhitespace(codeOfChar) && codeOfChar != -1)
        {
            char currentChar = (char) codeOfChar;
            element.append(currentChar);
            codeOfChar = nextChar();
        }

        return element.toString();
    }

    public int nextInt() throws IOException{
        return Integer.parseInt(next());
    }

    public boolean hasNextLine() throws IOException {
        int result = nextChar();
        if (result == -1) {
            return false;
        } else {
            bufferPointer--;
            return true;
        }

    }


    public void close() throws IOException {
        in.close();
    }

    public void dump() {
        System.out.println("bufferPointer = " + bufferPointer + "  bytesRead = " + bytesRead);
    }



}
