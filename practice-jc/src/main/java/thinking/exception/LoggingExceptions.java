package thinking.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @author orange
 * @date 2024/6/3
 */
public class LoggingExceptions {

    /**
     * 知识点（12.4.1）：异常与记录日志
     */

    static class LoggingException extends Exception {
        private static Logger logger = Logger.getLogger("LoggingException");
        public LoggingException() {
            StringWriter trace = new StringWriter();
            printStackTrace(new PrintWriter(trace));
            logger.severe(trace.toString());
        }
    }

    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
    }
}
