# 摘要

在前面一篇介绍了如何通过DDD的思想，来调整单体服务内的工程结构，为微服务的拆分做准备。同时介绍了我们在进行微服务拆分的时候踩过的一些坑。
这篇介绍下我们最终的方案，不一定对，欢迎留言讨论。

# 微服务划分

## 问题分析

上篇介绍过我们一开始的服务划分标准

>1. **一个领域一个服务的规则**去拆分，
>2. 同时为了保证领域的纯洁性，我们区分了**领域服务，和前台服务**。领域服务就是领域逻辑，不直接对前端暴露。前台服务组装各个领域服务，暴露给前端。
>3. 同时**为了保持扩展**，我们预留了一个微服务作为服务孵化器。对于领域不清晰的（比如大部分的新的业务），放在这个服务里面孵化，然后等领域足够大的时候再拆分出去。

实践后有些典型的问题也比较突出
>- 服务热点问题
 我们是一个新的业务，在业务迭代的过程中，大部分新需求都是领域不清晰，不知道能不能迭代下去的。所以按照之前的标准，都往growth服务里面去写代码，这样导致几乎团队里面的所有的人都在开发这一个项目，失去了拆分微服务的意义。
 
> - 服务依赖太严重
   无论写什么需求，都需要写多个应用，领域服务1个，前台如果有pc,需要在pc服务上开发，移动端要展示，要在mobile服务开发。服务之间的调用需要写rpc client接口，需要发版本，因为同时开发的人多，经常发生版本混乱，依赖问题。服务上线也很头疼，改一个小需求，需要部署多个服务。微服务一个很重要的点是去耦合，可独立部署。多了一层UI层作为微服务显然不是很合适。
   
>- 领域划分问题
 一个领域一个服务，粒度太小，有些东西不知道放在哪个服务里面，比如用户收藏博客，是放在用户服务里面，还是放在博客领域呢。

三个比较突出的问题，反应出的共性问题就是

- 服务边界不清晰

  微服务的边界不清晰，起因肯定是标准定义的不够准确

- 服务之间依赖多了
  
  微服务的一个重要特征就是自治性，如果依赖的服务多了，那么我们就享受不到微服务带来的好处，而只能感受到微服务的坏处。
  

## 解决手法

为了解决以上问题，我们反思了下我们的划分标准，组内进行了深入的讨论。一致觉得是因为我们为了推行DDD，在没有深入思考的情况下，过早的进行了大面积的微服务拆分。导致了诸多的问题。虽然这么做在当时的情况下，是最优的解决方案，但是带来的问题也很突出。**那什么时候才是进行微服务拆分的最好时机呢**?
因为理论学习、认知始终都没有尽头，只有实践才能出真知。我们没有纠结在过去的错误之中，而是重新读取了DDD的理论。这一次有了不一样的思考。

DDD中有战略设计，**划分领域，找出限界上下文，识别出核心域**。然后有战术设计，对领域进行建模，
聚合根、实体、值对象、领域服务、领域事件等。战略设计通常就是指导思想，战术设计是具体打法。我们一开始认定要
先有指导思想，然后再有具体打法。现在发现我们错了，指导思想不是一蹴而就的，也不是不成不变的。在一开始没有标准时，它必须要来源于实际打法中。
同时需要在实践过程中不断总结，修正、完善指导思想。

于是我们又重新梳理了一遍我们的整体业务

### 前台功能

把我们所有端的前台功能都梳理一遍，画成图


### 业务架构全景

根据前台功能，进一步整理，抽象出业务架构全景

### 划分出上下文

根据业务架构全景，在核心域中建立出限界上下文，拆分微服务

非常抱歉了，涉及敏感信息，这里不能贴图,如果觉得太抽象不好理解，请参考[DDD落地：业务分析师和架构师的完美结对](http://www.ddd-china.com/look-back-2017.html)

### 新的微服务划分标准

我们提出了一种新的微服务划分标准

- 确定以限界上下文为微服务划分的标准
  
  限界上下文的划分很难，但是必须要做。限界上下文不是凭空造出来的，而是从一个实体关联关系、与业务人员沟通出来的。

- 服务的演进是以限界上下文作为单元进行演进的
  
  之前我们拿一个微服务作为领域孵化器，其实就是放弃了对业务的整体认知，和对新需求的业务思考。
  我们的新业务不是一个新产品，全部推倒重来的。大多时候它还是解决某类业务上的问题。只是采取的手段不一样罢了。
  所以我们需要挖掘其本质，将它放到现有的上下文中。每个上下文一个微服务，对应一个开发owner,他负责这个领域内的事情。
  这样每个服务都有新的领域孵化。
  
  
 
# 举例

以电商举例，如果只是一个创业公司，不可能都跟阿里巴巴一样的架构，上百个服务。但是解决的问题电商领域可以抽象出来。
## 限界上下文

![](https://user-gold-cdn.xitu.io/2019/6/2/16b165f717af1b38?w=1028&h=877&f=png&s=61903)

分为人、货、场、交易几个上下文。然后不断的孵化，哪一部分是你业务的核心域，然后不断的服务拆分，比如你也是一家做垂直电商的公司，这些基本的东西肯定不应该是你的核心域，只能是支撑域，要不然你的业务肯定发展不起来。


## 微服务的划分
从限界上下文中抽出微服务，一个微服务中包含了多个领域。

另外我们遗弃了之前的UI服务，所有微服务可以直接和前台交互，这样可以有效的减少服务的依赖。
只有当需要多个领域进行组合时，我们才写在一个新的【组合ui】服务里面

![](https://user-gold-cdn.xitu.io/2019/6/2/16b165fa85307b1a?w=765&h=637&f=png&s=20522)

另外限界上下文不是一层不变的，比如商品营销，是一个领域，业务简单时和商品的关联性比较大，放在商品域。当你需要同时对店铺做营销，对用户做营销，显然他不应该在商品上下文了，那么可以剥离出来，作为一个独立的限界上下文：营销上下文。


**相关阅读**

[可落地的DDD(1)-目标讨论](https://juejin.im/post/5ce0cf78f265da1bab297e3d)

[可落地的DDD的(2)-为什么说MVC工程架构已经过时](https://juejin.im/post/5ce40ef95188252db664848a)

[可落地的DDD(3)-如何利用DDD进行微服务的划分](https://juejin.im/post/5ceff514f265da1b83337250)

**关注【方丈的寺院】，第一时间收到文章的更新，与方丈一起开始技术修行之路**
![在这里插入图片描述](https://user-gold-cdn.xitu.io/2019/5/30/16b095554461841d?w=344&h=344&f=jpeg&s=10747)
