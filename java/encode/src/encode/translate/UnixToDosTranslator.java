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
