package encode.translate;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
public interface Translator {
    void translate(InputStream in, OutputStream out);
}
