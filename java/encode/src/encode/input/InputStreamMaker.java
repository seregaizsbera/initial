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
public interface InputStreamMaker {

    boolean hasNext();

    InputStream getNext() throws IOException;

    void translated() throws IOException;

}
