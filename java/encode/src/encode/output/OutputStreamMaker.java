package encode.output;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
public interface OutputStreamMaker {

    boolean hasNext();

    OutputStream getNext() throws IOException;

    void translated() throws IOException;

}
