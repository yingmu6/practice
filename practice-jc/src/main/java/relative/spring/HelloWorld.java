package relative.spring;

/**
 * @author : chensy
 * Date : 2020-03-05 17:32
 */
public class HelloWorld {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your Message : " + message);
    }
}
