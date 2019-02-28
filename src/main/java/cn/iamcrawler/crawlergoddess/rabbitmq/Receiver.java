package cn.iamcrawler.crawlergoddess.rabbitmq;

import cn.iamcrawler.crawlergoddess.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuliang on 2019/1/23.
 * 消息接受者
 */
@Component
@Slf4j
public class Receiver {


    /**
     * 监听配置的队列
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(String message) {
        log.info("【receiverDirectQueue监听到消息】" + message);
    }





}
