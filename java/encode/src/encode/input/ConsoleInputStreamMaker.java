package encode.input;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */
class ConsoleInputStreamMaker implements InputStreamMaker {
    private boolean translated;

    ConsoleInputStreamMaker() {
        translated = false;
    }

    public boolean hasNext() {
        return !translated;
    }

    public InputStream getNext() throws IOException {
        return translated ? null : System.in;
    }

    public void translated() throws IOException {
        translated = true;
    }
}
