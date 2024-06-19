package relative.basic.reflect;

/**
 * @Author chenSy
 * @Date 2023/05/12 11:45
 * @Description
 */
public class GreetServiceImpl extends GreetCommonService implements IGreetService {

    private String msg; //问候语

    public GreetServiceImpl() {
    }

    public GreetServiceImpl(String msg) {
        this.msg = msg;
    }

    @Override
    public String say(String msg) {
        return "你好：" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public int f1(Object p, int x) throws NullPointerException
    {
        if (p == null)
            throw new NullPointerException();
        return x;
    }
}