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
class ConsoleOutputStreamMaker implements OutputStreamMaker {
	
	public boolean hasNext() {
		return true;
	}
	
    public OutputStream getNext() throws IOException {
        return System.out;
    }

    public void translated() throws IOException {
    }
}
