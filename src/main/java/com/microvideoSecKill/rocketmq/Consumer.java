package com.microvideoSecKill.rocketmq;

import com.alibaba.fastjson.JSON;
import com.microvideoSecKill.service.SecKillGoodsService;
import com.microvideoSecKill.service.SecKillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class Consumer {

    @Value("${rocketmq.consumer.groupName}")
    private String groupName;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.topic}")
    private String consumerTopic;

    @Autowired
    SecKillGoodsService secKillGoodsService;

    @Autowired
    SecKillOrderService secKillOrderService;

    private static DefaultMQPushConsumer consumer;

    @PostConstruct
    public void consumeMessage() throws Exception {
        // 指定消费组名为my-consumer
        consumer = new DefaultMQPushConsumer(groupName);
        // 配置namesrv地址
        consumer.setNamesrvAddr(namesrvAddr);
        // 订阅topic下的全部消息（因为是*，*指定的是tag标签，代表全部消息，不进行任何过滤）
        consumer.subscribe(consumerTopic, "*");

        // 注册监听器，进行消息消息。
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : msgs) {
                    String str = new String(msg.getBody());
                    // 输出消息内容
                    Date date = new Date();
                    System.out.println(date.toString() + " Consumer 接收消息成功！Result is : " + str);

                    SecKillMessage secKillMessage = JSON.parseObject(str, SecKillMessage.class);
                    String userId = secKillMessage.getUserId();
                    String goodsId = secKillMessage.getGoodsId();
                    Integer buyCount = secKillMessage.getBuyCount();
                    Timestamp timestamp = secKillMessage.getTimestamp();

                    // MySQL 减库存
                    // 每次成功秒杀的商品（或红包）的数量为 buyCount
                    boolean successReduceStock = reduceStock(goodsId, buyCount);
                    if (successReduceStock) {
                        log.info("Update MySQL Database for SecKill Reduce Stock Successfully !");
                    } else {
                        log.error("Update MySQL Database for SecKill Reduce Stock is failed !");
                    }

                    // MySQL 创建订单
                    // 每次成功秒杀的商品（或红包）的数量为 buyCount
                    boolean successCreateOrder = createNewOrder(userId, goodsId, buyCount, timestamp);
                    if (successCreateOrder) {
                        log.info("Insert MySQL Database for Order detail Successfully !");
                    } else {
                        log.error("Insert MySQL Database for Order detail is failed !");
                    }
                }

                // 默认情况下，这条消息只会被一个consumer消费，这叫点对点消费模式。也就是集群模式。
                // ack确认
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();
        System.out.println("消费者 Consumer start");
    }


    // MySQL 减库存
    private boolean reduceStock(String goodsId, Integer buyCount) {
        boolean successReduceStock = false;
        try {
            successReduceStock = secKillGoodsService.reduceStock(goodsId, buyCount);
        } catch (Exception e) {
            log.error("Error in Update MySQL Database for Reduce Stock - ", e);
        }
        return successReduceStock;
    }


    // MySQL 创建订单
    private boolean createNewOrder(String userId, String goodsId, Integer buyCount, Timestamp timestamp) {
        boolean successCreateOrder = false;
        try {
            successCreateOrder = secKillOrderService.
                    createNewSecKillGoodsAndStockCount(userId, goodsId, buyCount, timestamp);
        } catch (Exception e) {
            log.error("Error in Insert MySQL Database for Order detail - ", e);
        }
        return successCreateOrder;
    }


    @PreDestroy
    public void shutDownConsumer() {
        if (consumer != null) {
            consumer.shutdown();
            System.out.println("生产者 Consumer shutdown");
        }
    }
}
