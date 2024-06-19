package relative.script;

/**
 * @author chensy
 * @date 2022/10/13
 */
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptTest {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000 ; i++) {
            String script = "function run(arg){if (arg>500){return 0}else{return arg}}"; //ִ�еĽű�����
            System.out.println(jsRun("run", script, i));
        }
        System.out.println("��ʱ��"+String.valueOf(System.currentTimeMillis() - l));

    }

    /**
     *
     * @param methodName js�ű�������
     * @param script  js �ű�����
     * @param args js��������
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static Object jsRun(String methodName, String script, Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javaScriptEngine = scriptEngineManager.getEngineByName("JavaScript"); //��ȡָ�����ƵĽű�����
        javaScriptEngine.eval(script);
        Invocable inv = (Invocable) javaScriptEngine;
        return  inv.invokeFunction(methodName, args);

    }
}
