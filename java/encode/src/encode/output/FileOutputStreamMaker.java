package encode.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
class FileOutputStreamMaker implements OutputStreamMaker {
    private final Iterator inputFiles;
    private File outputFile;
    private OutputStream output;    

    FileOutputStreamMaker() {
        Param param = Param.getInstance();
        inputFiles = param.getInputFiles().iterator();
        outputFile = new File(param.getOutputFile());
    }

	public boolean hasNext() {
		return inputFiles.hasNext();
	}
	
    public OutputStream getNext()  throws IOException {
    	if (output == null) {
            output = new FileOutputStream(outputFile);
    	}
        return output;
    }

    public void translated() throws IOException {
        if (!hasNext() && output != null) {
            output.close();
            output = null;
        }
    }
}
