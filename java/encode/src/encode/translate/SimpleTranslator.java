package encode.translate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */
class SimpleTranslator implements Translator {
    private final String inputCharset;
    private final String outputCharset;

    SimpleTranslator(String inputCharset, String outputCharset) {
        this.inputCharset = inputCharset;
        this.outputCharset = outputCharset;
    }

    public void translate(InputStream in, OutputStream out) {
        if (in == null || out == null) {
            return;
        }
        try {
            Reader input = new BufferedReader(new InputStreamReader(in, inputCharset));
            Writer output = new BufferedWriter(new OutputStreamWriter(out, outputCharset));
            int previousCharacter = -1;
            int character = input.read();
            while (character > 0) {
                write(output, previousCharacter, character);
                output.flush();
                previousCharacter = character;
                character = input.read();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void write(Writer output, int previousCharacter, int character) throws IOException {
        output.write(character);
    }
}
