package encode.output;

import java.io.IOException;

import encode.Param;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
public class OutputStreamMakerFactory {
    private final int mode;

    public OutputStreamMakerFactory() {
        Param param = Param.getInstance();
        mode =  param.getOutputMode();
    }

    public OutputStreamMaker getOutputStreamMaker() throws IOException {
        switch (mode) {
            case Param.SELF_OUTPUT:
                return new SelfOutputStreamMaker();
            case Param.FILE_OUTPUT:
                return new FileOutputStreamMaker();
            case Param.CONSOLE_OUTPUT:
            default:
                return new ConsoleOutputStreamMaker();
        }
    }
}
