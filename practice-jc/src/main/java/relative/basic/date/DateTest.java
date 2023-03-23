package relative.basic.date;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间类测试
 *
 * @Author chenSy
 * @Date 2023/03/03 17:36
 * @Description
 */
public class DateTest {
    public static void main(String[] args) {
//        useDate();
//        useCalendar();
//        useDateUtil();

        useTimestamp();
    }

    public static void useTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //坑啊，若用HH，就能显示24小时制，用hh就是12小时制

        System.out.println("格式化时间V1：" + sdf.format(timestamp)); //当前时间为 2023-03-22 17:03:41，却输出了2023-03-22 05:03:41，减掉了12小时

        System.out.println("格式化时间V2：" + sdf.format(new Date(timestamp.getTime())));

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("格式化时间V3：" + f.format(date));

    }

    public static void useDate() {
        long current = System.currentTimeMillis();
        long start = 50 * 24 * 60 * 60 * 1000;

        long diff = current - start;
        Date date = new Date(diff);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("useDate:" + sdf.format(date)); //todo @csy 为啥输出的时间不是想要的
    }

    // 使用Calendar计算
    public static void useCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -10); // 正数表示加、负数表示减

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("useCalendar:" + sdf.format(cal.getTime()));
    }

    // 使用apache lang3工具计算
    public static void useDateUtil() {
        Date date = DateUtils.addDays(new Date(), -10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("useDateUtil:" + sdf.format(date));
    }
}
