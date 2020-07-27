# spring-ddd
## 项目说明
spring-ddd是一个依赖spring的类加载机制，结合多年对DDD的理解和实践经验而总结的一套DDD编程框架。
领域驱动设计最早源于Eric的《领域驱动设计》一书，这本书介绍的思想在应对软件复杂性方面非常有价值，但是内容非常抽象难以理解，下面先介绍一下本人对《领域驱动设计》核心概念及思想的理解，以及如何在编程实践中运用这些概念及思想。

DDD的核心思想是如何让应用代码架构能体现业务逻辑架构 ，可以分为战术设计和战略设计两个层面

### 战术设计

**实体(Entity)：** 实体对象一般来说对应一个数据库存储对象，实体对象需要一个ID(主键)来标示唯一性，实体对象通过数据访问层(Repository)加载到应用内存中，计算结果也通过数据访问层(Repository)保存到数据存储层(MySQL)中。

**值对象(ValueObject)：** 这个概念稍微有点难理解，简单来说类似DTO(Data transform object)对象，用来在不同领域对象之间传递信息。值对象和实体间可以相互转换，不同的地方在于值对象没有与之直接对应的数据存储对象。

**聚合(Aggregate)：** 这是《领域驱动设计》这本书里提出的最晦涩难懂的概念，并且让很多人在实践DDD的过程中走了不少弯路，这里重点说明一下。聚合概念是这样的：聚合是一组相关的领域对象，其目的是要确保业务规则在领域对象的各个生命周期都得以执行。聚合的提出应该是Eric想在应用代码中建立一套业务逻辑模型，在之前的编程方式中，业务逻辑往往散落于代码的各个角落甚至于SQL语句(存储过程)中，开发人员无法看到业务模型的全貌，导致后期代码维护成本越来越高，风险越来越大。聚合模型希望能在应用代码中统一维护业务逻辑模型，保证对业务对象的操作满足规则一致性。但是在实际编程中，这个概念往往非常难于落地。举个例子：某个订单(订单表)包含1000个子订单(子订单表)，当某个子订单的金额变更需要同步更新订单的总金额。以往的编程模式中一般是写两条SQL更新逻辑，同时将这两条SQL更新代码放到一个事务中保证一致性。但是按照聚合模型，需要先将订单及相关子订单的数据全部加载到应用中，在内存中重建订单聚合模型，通过对订单聚合根的操作更新业务模型数据，再将更新后的订单聚合模型保存到数据存储层中。是不是有种想死的感觉？一个设计即使想法再好，如果增加了事情的复杂性，也不是一个好的架构设计。那聚合的意义是什么？**我认为聚合的意义在于如何在应用代码中明确体现业务模型及业务规则。** 但是就表现形式而言，不一定非要构造一个聚合类这样的内存模型，也可以是关系数据表这样的数据模型(现在Mybatis这样的ORM框架已经可以很好的建立内存与数据模型的映射关系)。一个优秀的DDD开发人员对聚合理解应该能做到手中无剑而心中有剑。在编程框架如何体现聚合的概念，这里就要提到DDD战术设计最重要的概念-领域事件。

**领域事件(DomainEvent)：** 领域事件最早不是Eric提出的，应该是后人在实践DDD过程中总结的概念。因为在应用中重建聚合根的方式在编码中很难落地，所以提出了领域事件(其实我也很早就想到了这个方式，只是没有总结成领域事件这个概念)并由此提出了事件驱动编程模型。简单来说，一个领域事件就是对一个聚合根的业务操作定义，同时由事件处理类来这个满足这个场景业务规则。在上面的订单的例子中，我们可以定义这样的领域事件UpdateOrderAmountEvent(更新订单金额事件)，以及事件处理类UpdateOrderAmountEventHandler。领域事件驱动编程体现了约定编程模式的思想：UpdateOrderAmountEvent定义体现了操作是对订单聚合根的操作，而不是对子订单操作，UpdateOrderAmountEventHandler事件处理类须满足业务规则（订单金额与子订单金额一致性）。领域事件编程模式提供了一种很好的将聚合模型落地的实现方式。

**将隐式概念转变为显性概念：** 这是《领域驱动设计》里又一个很难理解的章节，我按照我的经验解释一下。什么是隐式概念，前面说过以往的编程方式业务规则逻辑经常散落在不同的代码角落里以if-else的形式存在，如果后面的开发人员要理解这段业务逻辑就需要找到相对应的if-else代码理解逻辑，这往往是很难理解的（加上中国程序员写注释的习惯很差）。这就是隐式概念。那怎么优化呢？第一步我们想到的就是把这个业务逻辑提到一个方法里，通过方法名我们大致就能判断出这段代码的业务含义。但是这样还不够，因为随着业务的发展一个复杂的业务类往往会有几十甚至上百个业务方法，我需要一个个打开类去找我要修改的那个方法。而且方法和方法的业务逻辑很容易耦合在一起。所以更进一步，我们可以把一个业务逻辑放到一个业务类里。比如上面的UpdateOrderAmountEventHandler，这个类从名字就可以看出大致的业务含义，并且这个类只做这一个业务操作，满足单一职责原则。同时借助Java的类隔离机制，又很好的解耦了业务代码逻辑。所以**将隐式概念转变为显性概念就是将业务逻辑从if-else代码->业务方法->领域事件类这样不断抽象与提升的过程**。

**统一语言：** 统一语言涉及战术与战略层面，这里先从战术层面解释一下。当我们将隐式概念转变为显示概念后（业务逻辑都抽象到领域事件类及事件处理类里），**那领域事件类的命名就代表着我们对业务的理解，必须非常严肃和谨慎**。比如：UpdateOrderAmountEvent和ModifyOrderAmountEvent就代表两个不同的领域事件，UpdateOrderAmountEvent就必须对应UpdateOrderAmountEventHandler处理类。如果命名不规范就会造成后续开发人员对业务逻辑的错误理解。

**如何理解 领域层：**

### 战略设计
战略设计核心讲了一个问题，领域(界限上下文)如何隔离及领域之间如何关联



