package encode.input;

import encode.Param;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
public class InputStreamMakerFactory {
    private final int mode;

    public InputStreamMakerFactory() {
        Param param = Param.getInstance();
        mode =  param.getInputMode();
    }

    public InputStreamMaker getInputStreamMaker() {
        switch (mode) {
            case Param.FILE_INPUT:
                return new FileInputStreamMaker();
            case Param.CONSOLE_INPUT:
            default:
                return new ConsoleInputStreamMaker();
        }
    }
}
