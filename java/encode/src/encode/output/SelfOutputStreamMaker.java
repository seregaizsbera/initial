package encode.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
class SelfOutputStreamMaker implements OutputStreamMaker {
    private final Iterator inputFiles;
    private OutputStream currentStream;
    private File currentFile;
    private File currentTempFile;

    private void closeCurrentFile() throws IOException {
        if (currentStream != null) {
            currentStream.close();
        }
        if (currentFile == null || currentTempFile == null) {
            return;
        }
        OutputStream finalOut = new FileOutputStream(currentFile);
        InputStream finalIn = new FileInputStream(currentTempFile);
        byte buf[] = new byte[1024 * 1024];
        int bytesRead = finalIn.read(buf);
        while (bytesRead > 0) {
            finalOut.write(buf, 0, bytesRead);
            bytesRead = finalIn.read(buf);
        }
        finalIn.close();
        finalOut.close();
        currentTempFile.delete();
        currentStream = null;
        currentFile = null;
        currentTempFile = null;
    }

	public boolean hasNext() {
		return inputFiles.hasNext();
	}
	
    SelfOutputStreamMaker() {
        Param param = Param.getInstance();
        inputFiles = param.getInputFiles().iterator();
        currentStream = null;
        currentFile = null;
        currentTempFile = null;
    }

    public OutputStream getNext() throws IOException {
        closeCurrentFile();
        if (!hasNext()) {
            return null;
        }
        currentFile = new File((String) inputFiles.next());
        currentTempFile = File.createTempFile("encode", currentFile.getName());
        currentTempFile.deleteOnExit();
        currentStream = new FileOutputStream(currentTempFile);
        return currentStream;
    }

    public void translated() throws IOException {
        closeCurrentFile();
    }
}
