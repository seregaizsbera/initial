package encode.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import encode.Param;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */
class FileInputStreamMaker implements InputStreamMaker {
    private final Iterator inputFiles;
    private InputStream currentStream;

    FileInputStreamMaker() {
        Param param = Param.getInstance();
        inputFiles = param.getInputFiles().iterator();
        currentStream = null;
    }

    public InputStream getNext() throws IOException {
        closeCurrentStream();
        if (!hasNext()) {
            return null;
        }
        currentStream = new FileInputStream((String) inputFiles.next());
        return currentStream;
    }

    public void translated() throws IOException {
        closeCurrentStream();
    }

    public boolean hasNext() {
        return inputFiles.hasNext();
    }
    
    private void closeCurrentStream() throws IOException {
        if (currentStream != null) {
            currentStream.close();
            currentStream = null;
        }
    }
}
