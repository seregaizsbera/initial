package encode.translate;

import java.io.IOException;
import java.io.Writer;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
class DosToUnixTranslator extends SimpleTranslator {
    DosToUnixTranslator(String inputCharset, String outputCharset) {
        super(inputCharset, outputCharset);
    }

    protected void write(Writer output, int previousCharacter, int character) throws IOException {
        if (previousCharacter == '\r' && character != '\n') {
            output.write(previousCharacter);
        }
        if (character == '\r') {
            return;
        }
        output.write(character);
    }
}
