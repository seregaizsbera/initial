package encode.translate;

import java.io.IOException;
import java.io.Writer;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */
class UnixToDosTranslator extends SimpleTranslator {
    UnixToDosTranslator(String inputCharset, String outputCharset) {
        super(inputCharset, outputCharset);
    }

    protected void write(Writer output, int previousCharacter, int character) throws IOException {
        if (character == '\n' && previousCharacter != '\r') {
            output.write('\r');
        }
        output.write(character);
    }
}
