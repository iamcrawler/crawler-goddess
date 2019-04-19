package cn.iamcrawler.crawlergoddess.rabbitmq;

import cn.iamcrawler.crawlergoddess.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuliang on 2019/1/23.
 * 消息发送者
 */
@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue() {
        log.info("【sendDirectQueue已发送消息】");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, "123");
    }


}
