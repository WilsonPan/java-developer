# RocketMQ

## 领域模型

### 主题(Topic)

主题是 Apache RocketMQ 中消息传输和存储的顶层容器，用于标识同一类业务逻辑的消息。 主题的作用主要如下：

- **定义数据的分类隔离**: 在 Apache RocketMQ 的方案设计中，建议将不同业务类型的数据拆分到不同的主题中管理，通过主题实现存储的隔离性和订阅隔离性。

- **定义数据的身份和权限**: Apache RocketMQ 的消息本身是匿名无身份的，同一分类的消息使用相同的主题来做身份识别和权限管理。

**使用示例**

```sh
sh mqadmin updateTopic -n <nameserver_address> -t <topic_name> -c <cluster_name> -a +message.type=<message_type>
```

**使用建议**

1. 按照业务分类合理拆分主题

  - 消息类型是否一致
  - 消息业务是否关联
  - 消息量级是否一样

2. 单一主题只收发一种类型消息，避免混用

3. 主题管理尽量避免自动化机制

### 队列（MessageQueue）

队列是 Apache RocketMQ 中消息存储和传输的实际容器，也是 Apache RocketMQ 消息的最小存储单元。 Apache RocketMQ 的所有主题都是由多个队列组成，以此实现队列数量的水平拆分和队列内部的流式存储。

### 消息（Message）

消息是 Apache RocketMQ 中的最小数据传输单元。生产者将业务数据的负载和拓展属性包装成消息发送到 Apache RocketMQ 服务端，服务端按照相关语义将消息投递到消费端进行消费。

Apache RocketMQ 的消息模型具备如下特点：

- 消息不可变性
- 消息持久化

### 生产者（Producer）

生产者是 Apache RocketMQ 系统中用来构建并传输消息到服务端的运行实体。

**使用建议**

1. 不建议单一进程创建大量生产者
2. 不建议频繁创建和销毁生产者

### 消费者分组（ConsumerGroup）

消费者分组是 Apache RocketMQ 系统中承载多个消费行为一致的消费者的负载均衡分组。

**使用建议**

1. 按照业务合理拆分分组

2. 消费者分组管理尽量避免自动化机制

### 消费者（Consumer）

消费者是 Apache RocketMQ 中用来接收并处理消息的运行实体。 消费者通常被集成在业务系统中，从 Apache RocketMQ 服务端获取消息，并将消息转化成业务可理解的信息，供业务逻辑处理。

**使用建议**

1. 不建议频繁创建和销毁消费者

### 订阅关系（Subscription）

订阅关系是 Apache RocketMQ 系统中消费者获取消息、处理消息的规则和状态配置。

## 功能特性

### 普通消息

普通消息为 Apache RocketMQ 中最基础的消息，区别于有特性的顺序消息、定时/延时消息和事务消息。本文为您介绍普通消息的应用场景、功能原理、使用方法和使用建议。

**应用场景**

1. 微服务异步解耦
> 订单信息发送RocketMQ，下游物流系统|会员系统订阅订单消息并做下一步操作

2. 数据集成传输
> 订单信息发送RocketMQ，数据分析|日志系统订阅订单消息并做下一步操作

**普通消息生命周期**

- 初始化：消息被生产者构建并完成初始化，待发送到服务端的状态。

- 待消费：消息被发送到服务端，对消费者可见，等待消费者消费的状态。

- 消费中：消息被消费者获取，并按照消费者本地的业务逻辑进行处理的过程。

- 消费提交：消费者完成消费处理，并向服务端提交消费结果，服务端标记当前消息已经被处理。

- 消息删除：Apache RocketMQ按照消息保存机制滚动清理最早的消息数据，将消息从物理文件中删除。

### 定时/延时消息

定时/延时消息为 Apache RocketMQ 中的高级特性消息，本文为您介绍定时/延时消息的应用场景、功能原理、使用限制、使用方法和使用建议。


**应用场景**

- 分布式定时调度

> 例如每天5点执行文件清理，每隔2分钟触发一次消息

- 任务超时处理

> 订单下单后暂未支付，此时不可以直接关闭订单，而是需要等待一段时间后才能关闭订单

## 基本最佳实践

### 生产者

**Tag的使用**

一个应用尽可能用一个Topic，而消息子类型则可以用tags来标识。tags可以由应用自由设置，只有生产者在发送消息设置了tags，消费方在订阅消息时才可以利用tags通过broker做消息过滤。

**Keys的使用**

每个消息在业务层面一般建议映射到业务的唯一标识并设置到keys字段，方便将来定位消息丢失问题。服务器会为每个消息创建索引（哈希索引），应用可以通过topic、key来查询这条消息内容，以及消息被谁消费。由于是哈希索引，请务必保证key尽可能唯一，这样可以避免潜在的哈希冲突。常见的设置策略使用订单Id、用户Id、请求Id等比较离散的唯一标识来处理。

**日志的打印**

消息发送成功或者失败要打印消息日志，用于业务排查问题。Send消息方法只要不抛异常，就代表发送成功。

**消息发送失败处理方式**

以上策略也是在一定程度上保证了消息可以发送成功。如果业务要求消息发送不能丢，仍然需要对可能出现的异常做兜底，比如调用send同步方法发送失败时，则尝试将消息存储到db，然后由后台线程定时重试，确保消息一定到达Broker。

### 消费者

**消费过程幂等**

RocketMQ 无法避免消息重复（Exactly-Once），所以如果业务对消费重复非常敏感，务必要在业务层面进行去重处理。可以借助关系数据库进行去重。首先需要确定消息的唯一键，可以是msgId，也可以是消息内容中的唯一标识字段，例如订单Id等。在消费之前判断唯一键是否在关系数据库中存在。如果不存在则插入，并消费，否则跳过。（实际过程要考虑原子性问题，判断是否存在可以尝试插入，如果报主键冲突，则插入失败，直接跳过）

msgId一定是全局唯一标识符，但是实际使用中，可能会存在相同的消息有两个不同msgId的情况（消费者主动重发、因客户端重投机制导致的重复等），这种情况就需要使业务字段进行重复消费。

**提高消费并行度**

绝大部分消息消费行为都属于 IO 密集型，即可能是操作数据库，或者调用 RPC，这类消费行为的消费速度在于后端数据库或者外系统的吞吐量，通过增加消费并行度，可以提高总的消费吞吐量，但是并行度增加到一定程度，反而会下降。所以，应用必须要设置合理的并行度。 如下有几种修改消费并行度的方法：

**批量方式消费**

某些业务流程如果支持批量方式消费，则可以很大程度上提高消费吞吐量，例如订单扣款类应用，一次处理一个订单耗时 1 s，一次处理 10 个订单可能也只耗时 2 s，这样即可大幅度提高消费的吞吐量。

**重置位点跳过非重要消息**

发生消息堆积时，如果消费速度一直追不上发送速度，如果业务对数据要求不高的话，可以选择丢弃不重要的消息。建议使用重置位点功能直接调整消费位点到指定时刻或者指定位置。

**优化每条消息消费过程**

**消费打印日志**

如果消息量较少，建议在消费入口方法打印消息，消费耗时等，方便后续排查问题。







## 引用
> [rocketmq](https://rocketmq.apache.org/)