package relative.basic.json;

/**
 * @Author chenSy
 * @Date 2023/03/02 14:17
 * @Description
 */
public class Filed {

    private String fieldName;

    private String fieldValue;

    public Filed(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
