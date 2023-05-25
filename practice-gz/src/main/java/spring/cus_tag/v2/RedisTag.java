package spring.cus_tag.v2;

/**
 * @author chensy
 * @date 2023/5/25
 */
public class RedisTag {
    private String id;
    private String ip;
    private Integer port;
    private String desc;

    public RedisTag(String ip, Integer port, String desc) {
        this.ip = ip;
        this.port = port;
        this.desc = desc;
    }

    public RedisTag() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RedisTag(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}