# 背景
在DDD代码实践过程出现一些看起来很别扭的实现

-  为了查询，领域聚合根无限扩大

    如商品详情页聚合根

 
```
public class BrandAggr {
    /**
     * 唯一标识
     */
     private Long id;
     
    /**
     * 商品简介
     */
    private ItemInfoVal brandInfoVal;

    /**
     * 商品的渠道列表
     */
    private List<ItemChannelVal> channels;

    /**
     * 商品的价格区间列表
     */
    private List<ItemPricingVal> pricings;

    /**
     * 商品风格列表
     */
    private List<ItemStyleVal> styles;
     /**
     * 商品促销列表
     */
    private List<ItemPromotionVal> promotion;
     /**
     * 推荐列表
     */
    private List<ItemRecommendVal> promotion;
```

2.  组合领域对象是领域吗？如商品详情页，包含商品，促销，推荐，这这种场景下如何使用聚合根


# 一. 定义
CQRS(Command and Query Responsibility Segregation)是一种与传统的DDD实现不同的模式，将写与读区分开。CQRS适用于DDD的原因在于查询本身不应当影响领域建模

CQRS 主要包含两大概念，**一个是读写分离，一个是事件源。事件源不是必须项，**

读写分离

- 如果一个方法修改了对象的状态，就是一个命令，不应该返回数据

      阻抗：创建资源的时候，不是要返回资源id吗（这个不是重点可以忽略）



- 如果一个方法返回了数据，该方法就是一个查询，不应该直接或间接的修改对象的状态

     阻抗：现在有些方法中在查询的时候进行了懒删除



CQRS期望解决的问题

- 类似懒删除这种导致的数据不一致，难以排查的问题
- 使用同一个领域对象来进行数据读写可能会遇到资源竞争的情况。所以经常要处理锁的问题，在写入数据的时候，需要加锁，读取数据的时候需要判断是否允许脏读。这样使得系统的逻辑性和复杂性增加，并会影响系统的吞吐量。
- 对于复杂的业务场景，查询通常不只是通过领域对象构成，比如商品需要从opensearch中查询。
- 像数据层面做读写分离，缓存一样，读db和写db通常也是分离的。需要有一种结构和这种场景映射
# 二. 架构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190313213637175.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9mYW5nemhhbmcuYmxvZy5jc2RuLm5ldA==,size_16,color_FFFFFF,t_70)

command bus:接受写请求，分发给commandhandle

commandhandle：将领域事件保存到event store，同时publish消息到event bus

event bus: 分发给不同event handle

event handle： 将对象的变更更新到query数据库
　

# 三. 与目前的DDD区别

**领域对象**
我们不再是使用一套领域对象了，领域对象主要针对的是写。读直接是DTO
          比如上面提到的brand聚合就不会无限扩大了。




**面向事件编程**

    - 对象的所有变更通过事件来记录，

     - 对象的历史情况，还原都是通过事件db来处理

     - 系统间的交互通过事件来实现

# 四. 落地


事件溯源目前比较难落地，**读写分离可以尝试**。


 遵循聚合根的定义，必须与对象的组合区分开，对象组合考虑用DTO或者其他
 我们再来回顾下聚合根。解决开头两个问题

> Aggregate(聚合）是一组相关对象的集合，作为一个整体被外界访问，聚合根（Aggregate Root）是这个聚合的根节点。
聚合是一个非常重要的概念，核心领域往往都需要用聚合来表达。其次，聚合在技术上有非常高的价值，可以指导详细设计。
聚合由根实体，值对象和实体组成。

如何创建好的聚合？

- 边界内的内容具有一致性：在一个事务中只修改一个聚合实例。如果你发现边界内很难接受强一致，不管是出于性能或产品需求的考虑，应该考虑剥离出独立的聚合，采用最终一致的方式。
- 设计小聚合：大部分的聚合都可以只包含根实体，而无需包含其他实体。即使一定要包含，可以考虑将其创建为值对象。
- 聚合之间的关联通过ID，而不是对象引用
- 聚合内强一致性，聚合之间最终一致性       

>1. 为了查询，领域聚合根无限扩大
>2. 组合领域对象是领域吗？如商品详情页，包含商品，促销，推荐，这这种场景下如何使用聚合根

组合领域对象是领域，衍生出一些业务逻辑，但是不应该定义为聚合根，聚合根应该是小的，事务一致性的，面向领域本身的。
像商品详情页这种应该使用DTO来组合。

# 参考
https://cqrs.files.wordpress.com/2010/11/cqrs_documents.pdf

https://www.jdon.com/37891

https://www.cnblogs.com/zhili/p/CQRSDemo.html

http://www.cnblogs.com/daxnet/archive/2011/01/06/1929099.html

实现领域驱动设计第十章聚合