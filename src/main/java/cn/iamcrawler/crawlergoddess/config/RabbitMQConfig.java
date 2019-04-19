package cn.iamcrawler.crawlergoddess.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuliang on 2019/1/23.
 * <p>
 * rabbit-mq配置文件
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    public static final String QUEUE = "direct_queue";


    /**
     * Direct模式
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        // 第一个参数是队列名字， 第二个参数是指是否持久化
        return new Queue(QUEUE, true);
    }


    /**
     * 延时队列 TTL 名称
     */

    private static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";


    /**
     * dead letter exchange
     * 延时消息就是发送在这个交换机上
     */

    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";


    /**
     * routing key 名称
     * 具体消息发在该routing key
     */

    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";


    public static final String ORDER_QUEUE_NAME = "user.order.queue";
    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";
    public static final String ORDER_ROUTING_KEY = "order";


}
