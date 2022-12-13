package cn.xuanma.test.mq.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:wangshu'an
 * @date:2022/11/15 17:54
 * @Description:
 */
@Component
@RabbitListener(queues = "testDirectQueue")// 监听队列的名称
public class DirectReceiver {

    @RabbitHandler
    public void process(Map map){
        System.out.println("DirectReceiver 订阅者获取的消息是 " + map.toString());
    }

}
