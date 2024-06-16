package interview.offer_come.design_mode.visitor;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author chensy
 * @date 2024/3/16
 */
@Getter
@Setter
public class ProjectElement implements Element {

    private String projectName;

    private String projectContent;

    private String visitorName;

    private Date visitorTime;

    public ProjectElement(String projectName, String projectContent) {
        this.projectName = projectName;
        this.projectContent = projectContent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void signature(String visitorName, Date visitorTime) {
        this.visitorName = visitorName;
        this.visitorTime = visitorTime;
    }

}
