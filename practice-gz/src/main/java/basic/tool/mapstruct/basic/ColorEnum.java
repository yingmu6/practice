package basic.tool.mapstruct.basic;

import lombok.Getter;

/**
 * @Author chenSy
 * @Date 2023/03/27 19:37
 * @Description
 */
@Getter
public enum ColorEnum {

    WHITE("white", "白色"),

    RED("read", "红色"),

    BLUE("blue", "蓝色");

    String code;

    String desc;

    ColorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ColorEnum getByCode(String code) {
        for (ColorEnum colorEnum : ColorEnum.values()) {
            if (code.equals(colorEnum.getCode())) {
                return colorEnum;
            }
        }
        return null;
    }
}
