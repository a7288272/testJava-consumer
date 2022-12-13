package cn.xuanma.test.mq.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:wangshu'an
 * @date:2022/11/15 17:31
 * @Description:
 */
@Component
@RabbitListener(queues = "fanoutB")
public class FanoutReceiverB {
    @RabbitHandler
    public void process(Map map){
        System.out.println("FanoutReceiverB 订阅者获取的消息是 " + map.toString());
    }
}

