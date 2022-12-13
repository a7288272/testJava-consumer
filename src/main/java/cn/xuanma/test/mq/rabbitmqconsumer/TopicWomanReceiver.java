package cn.xuanma.test.mq.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:wangshu'an
 * @date:2022/11/16 9:15
 * @Description:
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicWomanReceiver {
    @RabbitHandler
    public void process(Map map){
        System.out.println("TopicWomanReceiver订阅者接受到的消息  "+map.toString() );

    }
}
