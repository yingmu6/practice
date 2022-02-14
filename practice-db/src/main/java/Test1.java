import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensy
 * @date 2022/2/14
 */
@Getter
@Slf4j
public class Test1 {
    public static void main(String[] args) {
        log.debug("222"); //todo @csy 配置log4j文件，能输出日志

        ChannelInboundHandlerAdapter adapter = null;
    }
}
