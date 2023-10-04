# RocketMQ

## Docker 安装RocketMQ

```yaml
version: '2'
services:
  namesrv:
    image: apache/rocketmq:5.1.3
    container_name: rmqnamesrv
    volumes:
      - ./data/namesrv/logs:/home/rocketmq/logs
    command: sh mqnamesrv
  broker:
    image: apache/rocketmq:5.1.3
    container_name: rmqbroker
    ports:    
      - 10911:10911
      - 8081:8081
    volumes:
      - ./data/broker/logs:/home/rocketmq/logs
      - ./data/broker/store:/home/rocketmq/store
      - ./data/broker/conf/broker.conf:/home/rocketmq/rocketmq-5.1.3/conf/broker.conf
    command: sh mqbroker -n namesrv:9876 -c ../conf/broker.conf --enable-proxy
    depends_on:
      - namesrv
  dashboard:
    image: apacherocketmq/rocketmq-dashboard
    container_name: rocketmq_dashboard
    ports:
      - 18080:8080
    environment:
      JAVA_OPTS: -Drocketmq.namesrv.addr=namesrv:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=falses
```

`broker.conf`文件

```sh
brokerClusterName = DefaultCluster
brokerName = broker-a
brokerId = 0
deleteWhen = 04
fileReservedTime = 48
brokerRole = ASYNC_MASTER
flushDiskType = ASYNC_FLUSH

namesrvAddr=192.168.0.101:9876
brokerIP1=192.168.0.101
defaultTopicQueueNums=4
autoCreateTopicEnable=true
autoCreateSubscriptionGroup=true
listenPort=10911
```

## RocketMQ SDK

1. 添加引用
```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client-java</artifactId>
    <version>5.0.5</version>
</dependency>
```

2. 生产者

```java
package com.example.mq.demo.mqdemo;

import java.io.IOException;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {

    @Test
    void testSend() throws ClientException, IOException {
        String endpoint = "localhost:8081";
        String topic = "TestTopic";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
        ClientConfiguration configuration = builder.build();
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                .setClientConfiguration(configuration)
                .build();
        // 普通消息发送。
        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                // 设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys("messageKey")
                // 设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("messageTag")
                // 消息体。
                .setBody(("Wilson" + System.currentTimeMillis()).getBytes())
                .build();
        try {
            // 发送消息，需要关注发送结果，并捕获失败等异常。
            SendReceipt sendReceipt = producer.send(message);
        } catch (ClientException e) {
        }
        producer.close();
    }
}
```
3. 消费者

```java
package com.example.mq.demo.mqdemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import org.junit.jupiter.api.Test;

public class PushConsumerTest {
    @Test
    public void testConsumer() throws ClientException, InterruptedException, IOException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoints = "localhost:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        // 订阅消息的过滤规则，表示订阅所有Tag的消息。
        String tag = "*";
        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
        // 为消费者指定所属的消费者分组，Group需要提前创建。
        String consumerGroup = "YourConsumerGroup";
        // 指定需要订阅哪个目标Topic，Topic需要提前创建。
        String topic = "TestTopic";
        // 初始化PushConsumer，需要绑定消费者分组ConsumerGroup、通信参数以及订阅关系。
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                // 设置消费者分组。
                .setConsumerGroup(consumerGroup)
                // 设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                // 设置消费监听器。
                .setMessageListener(messageView -> {
                    // 处理消息并返回消费结果。
                    String body = StandardCharsets.UTF_8.decode(messageView.getBody()).toString();
                    System.out.println("body: " +  body);
                    return ConsumeResult.SUCCESS;
                })
                .build();

        Thread.sleep(Long.MAX_VALUE);

        // 如果不需要再使用 PushConsumer，可关闭该实例。
        pushConsumer.close();
    }
}
```
## RocketMQ-Dashboard

### 驾驶舱
* 查看broker的消息量（总量/5分钟图）
* 查看单一主题的消息量（总量/趋势图）

* 查看集群的分布情况
    * cluster与broker关系
    * broker
* 查看broker具体信息/运行信息
* 查看broker配置信息

### 主题页面
* 展示所有的主题，可以通过搜索框进行过滤
* 筛选 普通/重试/死信 主题
* 添加/更新主题
    * clusterName 创建在哪几个cluster上
    * brokerName 创建在哪几个broker上
    * topicName 主题名
    * writeQueueNums  写队列数量
    * readQueueNums  读队列数量
    * perm //2是写 4是读 6是读写
* 状态 查询消息投递状态（投递到哪些broker/哪些queue/多少量等）
* 路由 查看消息的路由（现在你发这个主题的消息会发往哪些broker，对应broker的queue信息）
* CONSUMER管理（这个topic都被哪些group消费了，消费情况何如）
* topic配置（查看变更当前的配置）
* 发送消息（向这个主题发送一个测试消息）
* 重置消费位点(分为在线和不在线两种情况，不过都需要检查重置是否成功)
* 删除主题 （会删除掉所有broker以及namesrv上的主题配置和路由信息）

### 消费者页面
* 展示所有的消费组，可以通过搜索框进行过滤
* 刷新页面/每隔五秒定时刷新页面
* 按照订阅组/数量/TPS/延迟 进行排序
* 添加/更新消费组
    * clusterName 创建在哪几个集群上
    * brokerName 创建在哪几个broker上
    * groupName  消费组名字
    * consumeEnable //是否可以消费 FALSE的话将无法进行消费
    * consumeBroadcastEnable //是否可以广播消费
    * retryQueueNums //重试队列的大小
    * brokerId //正常情况从哪消费
    * whichBrokerWhenConsumeSlowly//出问题了从哪消费
* 终端 在线的消费客户端查看，包括版本订阅信息和消费模式
* 消费详情 对应消费组的消费明细查看，这个消费组订阅的所有Topic的消费情况，每个queue对应的消费client查看（包括Retry消息）
* 配置 查看变更消费组的配置
* 删除 在指定的broker上删除消费组

### 发布管理页面
* 通过Topic和Group查询在线的消息生产者客户端
    * 信息包含客户端主机 版本
    
###* 查看集群的分布情况
    * cluster与broker关系
    * broker
* 查看broker具体信息/运行信息
* 查看broker配置信息

### 主题页面
* 展示所有的主题，可以通过搜索框进行过滤
* 筛选 普通/重试/死信 主题
* 添加/更新主题
    * clusterName 创建在哪几个cluster上
    * brokerName 创建在哪几个broker上
    * topicName 主题名
    * writeQueueNums  写队列数量
    * readQueueNums  读队列数量
    * perm //2是写 4是读 6是读写
* 状态 查询消息投递状态（投递到哪些broker/哪些queue/多少量等）
* 路由 查看消息的路由（现在你发这个主题的消息会发往哪些broker，对应broker的queue信息）
* CONSUMER管理（这个topic都被哪些group消费了，消费情况何如）
* topic配置（查看变更当前的配置）
* 发送消息（向这个主题发送一个测试消息）
* 重置消费位点(分为在线和不在线两种情况，不过都需要检查重置是否成功)
* 删除主题 （会删除掉所有broker以及namesrv上的主题配置和路由信息）

### 消费者页面
* 展示所有的消费组，可以通过搜索框进行过滤
* 刷新页面/每隔五秒定时刷新页面
* 按照订阅组/数量/TPS/延迟 进行排序
* 添加/更新消费组
    * clusterName 创建在哪几个集群上
    * brokerName 创建在哪几个broker上
    * groupName  消费组名字
    * consumeEnable //是否可以消费 FALSE的话将无法进行消费
    * consumeBroadcastEnable //是否可以广播消费
    * retryQueueNums //重试队列的大小
    * brokerId //正常情况从哪消费
    * whichBrokerWhenConsumeSlowly//出问题了从哪消费
* 终端 在线的消费客户端查看，包括版本订阅信息和消费模式
* 消费详情 对应消费组的消费明细查看，这个消费组订阅的所有Topic的消费情况，每个queue对应的消费client查看（包括Retry消息）
* 配置 查看变更消费组的配置
* 删除 在指定的broker上删除消费组

### 发布管理页面
* 通过Topic和Group查询在线的消息生产者客户端
    * 信息包含客户端主机 版本
    
### 消息查询页面
* 根据Topic和时间区间查询
    *由于数据量大 最多只会展示2000条，多的会被忽略 
* 根据Topic和Key进行查询
    * 最多只会展示64条
* 根据消息主题和消息Id进行消息的查询
* 消息详情可以展示这条消息的详细信息，查看消息对应到具体消费组的消费情况（如果异常，可以查看具体的异常信息）。可以向指定的消费组重发消息。


> [RocketMQ-Dashboard](https://github.com/apache/rocketmq-dashboard/blob/master/docs/1_0_0/UserGuide_CN.md)