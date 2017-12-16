import org.apache.commons.io.FileUtils;
import tokens.Token;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("One argument (path to a source file) expected");
            return;
        }

        try {

            File file = new File(args[0]);
            String string = FileUtils.readFileToString(file, Charset.defaultCharset());

            TokenIterator tokens = new TokenIterator(string);

            while (tokens.hasNext()) {
                Token token = tokens.next();
                System.out.println(token);
            }
        } catch (ParsingErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}
