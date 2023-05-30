package relative.basic.type.primitive;

import org.junit.Test;

/**
 * 基本类型的默认值
 * @author chensy
 * @date 2023/5/30
 */
public class DefaultValueTest {

    /**
     * 基本类型_概述：
     * a）基本类型默认值（作为成员变量时系统会设置默认值，具体变量时需要自行指定，否则会报出编译错误）
     * Data Type	Default Value (for fields)
     * byte	        0
     * short	    0
     * int	        0
     * long	        0L
     * float	    0.0f
     * double	    0.0d
     * char	        '\u0000'
     * String (or any object)  	null (String不算基本类型，属于对象了)
     * boolean	    false
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html 基本类型说明
     */

    // -------基本类型----------
    private byte bVal;
    private short sVal;
    private int iVal;
    private long lVal;
    private float fVal;
    private double dVal;
    private char cVal;
    private String strVal;
    private boolean bolVal;

    //--------封装类型---------
    private Byte bWrapVal;
    private Short sWrapVal;
    private Integer iWrapVal;
    private Long lWrapVal;
    private Float fWrapVal;
    private Double dWrapVal;
    private Character cWrapVal;
    private Boolean bolWrapVal;

    @Test
    public void test_primitive_default_val() {
        /**
         * 成员变量为基本类型时，若没有指定初始值，系统则会设置默认值
         */
        System.out.println("byte_成员变量_默认值：" + bVal);
        System.out.println("short_成员变量_默认值：" + sVal);
        System.out.println("int_成员变量_默认值：" + iVal);
        System.out.println("long_成员变量_默认值：" + lVal);
        System.out.println("float_成员变量_默认值：" + fVal);
        System.out.println("double_成员变量_默认值：" + dVal);
        System.out.println("char_成员变量_默认值：" + cVal + ",是否为：" + (cVal == '\u0000'));
        System.out.println("String_成员变量_默认值：" + strVal);
        System.out.println("boolean_成员变量_默认值：" + bolVal);
    }

    @Test
    public void test_wrapper_default_val() {
        /**
         * 封装类的默认值为null
         */
        System.out.println("byte_封装类_默认值：" + bWrapVal);
        System.out.println("short_封装类_默认值：" + sWrapVal);
        System.out.println("int_封装类_默认值：" + iWrapVal);
        System.out.println("long_封装类_默认值：" + lWrapVal);
        System.out.println("float_封装类_默认值：" + fWrapVal);
        System.out.println("double_封装类_默认值：" + dWrapVal);
        System.out.println("char_封装类_默认值：" + cWrapVal);
        System.out.println("boolean_封装类_默认值：" + bolWrapVal);
    }

    @Test
    public void test_local_default_val() {
        byte bLocalVal;
        short sLocalVal;
        int iLocalVal;
        long lLocalVal;
        float fLocalVal;
        double dLocalVal;
        char cLocalVal;
        String strLocalVal;
        boolean bolLocalVal;

        // 局部变量需要指定初始值，不然会报 "变量未初始化"的编译错误
//        System.out.println("byte_局部变量_默认值：" + bLocalVal);
//        System.out.println("short_局部变量_默认值：" + sLocalVal);
//        System.out.println("int_局部变量_默认值：" + iLocalVal);
//        System.out.println("long_局部变量_默认值：" + lLocalVal);
//        System.out.println("float_局部变量_默认值：" + fLocalVal);
//        System.out.println("double_局部变量_默认值：" + dLocalVal);
//        System.out.println("char_局部变量_默认值：" + cLocalVal + ",是否为：" + (cLocalVal == '\u0000'));
//        System.out.println("String_局部变量_默认值：" + strLocalVal);
//        System.out.println("boolean_局部变量_默认值：" + bolLocalVal);
    }
}
