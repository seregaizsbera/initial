package tickets.util;

import java.io.Writer;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Содержит методы помогающие осуществлять "exception chaining". Коструирование экземляров
 * этого класса происходит по шаблону проектирования &quot;singleto&quot;.
 */
public class ExHelper {

    public static final String ENS_EXCEPTION = "<exception><![CDATA[";
    public static final String ENE_EXCEPTION = "]]></exception>";
    public static final String ENS_MESSAGE = "<message><![CDATA[";
    public static final String ENE_MESSAGE = "]]></message>";
    public static final String ENS_TO_STRING = "<toString><![CDATA[";
    public static final String ENE_TO_STRING = "]]></toString><![CDATA[";
    public static final String ENS_STACK_TRACE = "<stackTrace><![CDATA[";
    public static final String ENE_STACK_TRACE = "]]></stackTrace>";

    private static ExHelper instance = null;

    private ExHelper() {}


    public String writeXMLExDescr(Throwable e) {
        StringWriter res = new StringWriter();
        try {
            writeXMLExDescr(res, e);
        } catch (IOException e1) {
            //ignore
        }
        return res.toString();
    }

    public void writeXMLExDescr(Writer out, Throwable e) throws IOException {
        out.write(ENS_EXCEPTION);
        out.write(ENS_MESSAGE);
        out.write(e.getMessage());
        out.write(ENE_MESSAGE);
        out.write(ENS_TO_STRING);
        out.write(e.toString());
        out.write(ENE_TO_STRING);
        out.write(ENS_STACK_TRACE);
        e.printStackTrace(new PrintWriter(out));
        out.write(ENE_STACK_TRACE);
        out.write(ENE_EXCEPTION);
    }

    public String toString(Throwable parent) {
        StringBuffer buf = new StringBuffer();
        if (parent != null) {
            buf.append(" Parent exception: { ").append(parent.toString()).append(" }");
        }
        return buf.toString();
    }

    public void printStackTrace(
                Throwable parent) {
        if (parent != null) {
            Util.debug("Caused by:\n");
            Util.debug(parent);
        }
    }

    public void printStackTrace(
                java.io.PrintStream ps,
                Throwable parent) {
        if (parent != null) {
            ps.println("Caused by:\n");
            parent.printStackTrace(ps);
        }
    }

    public void printStackTrace(
                java.io.PrintWriter pw,
                Throwable parent) {
        if (parent != null) {
            pw.println("Caused by:\n");
            parent.printStackTrace(pw);
        }
    }

    public static final ExHelper getInstance() {
        if (instance == null) {
            instance = new ExHelper();
        }
        return instance;
    }
}

