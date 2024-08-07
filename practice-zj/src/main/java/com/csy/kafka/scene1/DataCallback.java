package com.csy.kafka.scene1;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author orange
 * @date 2024/6/1
 */
public class DataCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(DataCallback.class);
    private final long startTime;
    private final String message;

    public DataCallback(long startTime, String message) {
        this.startTime = startTime;
        this.message = message;
    }

    /**
     * 生产者成功发送消息，收到kafka服务端发来的ACK确认消息后，会调用此回调函数
     * @param recordMetadata 生产者发送的消息的元数据，如果发送过程中出现异常，此参数为null
     * @param e 发送过程中出现的异常，如果发送成功，则此参数为null
     */
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (recordMetadata != null) {
            long endTime = System.currentTimeMillis() - startTime;
            logger.info("callback success, message(" + message + ") send to partition("
                    + recordMetadata.partition() + ")," + "offset(" + recordMetadata.offset() + ") in" + endTime);
        } else {
            e.printStackTrace();
        }
    }
}
