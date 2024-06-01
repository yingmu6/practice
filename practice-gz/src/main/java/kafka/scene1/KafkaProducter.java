package kafka.scene1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author orange
 * @date 2024/6/1
 */
public class KafkaProducter { //@Gz-Doing

    /**
     * 知识点：kafka发送消息
     *
     * 知识点概括：
     *
     * 相关点学习：
     * 1）需先启动Zookeeper、再启动Kafka，最后可启动Kafka图形化界面EFAK进行分析
     *
     * 参考链接：
     * 1）https://blog.csdn.net/m0_38001814/article/details/82628101 kafka发送消息与接收消息
     */

    private final KafkaProducer<String, String> producer;
    public final static String TOPIC = "itsm-test";

    private KafkaProducter(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.required.acks","-1");
        producer = new KafkaProducer<>(properties);
    }

    void produce() {
        //发送100条消息
        int messageNo = 100;
        int count = 200;
        while (messageNo < count) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            long startTime = System.currentTimeMillis();
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, data);
            producer.send(record, new DataCallback(startTime, data));
            System.out.println(data);
            messageNo++;
        }
    }
    public static void main( String[] args ) {
        new KafkaProducter().produce();

        /**
         * 输出结果：
         * hello kafka message 100
         * hello kafka message 101
         * hello kafka message 102
         * hello kafka message 103
         * hello kafka message 104
         * hello kafka message 105
         * hello kafka message 106
         * hello kafka message 107
         * hello kafka message 108
         * ......
         *
         * 结果分析：
         */
    }
}
