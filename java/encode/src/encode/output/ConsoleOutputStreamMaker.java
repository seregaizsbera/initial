package encode.output;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
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
