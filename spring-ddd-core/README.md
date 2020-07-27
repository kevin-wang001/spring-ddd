DDD框架核心实现
```
com
└── sf
    └── ddd
        └── core
            ├── DomainEventHandler.java \\ 领域事件处理类注解，所有领域事件处理类都需添加该注解，该注解绑定领域事件与其事件处理类
            ├── Event.java \\ 领域事件接口，所有的领域事件都需实现此接口
            ├── EventDispatcher.java  \\ 领域事件注册及分发中心，这是事件驱动编程的核心实现类，EventDispatcher在应用启动时会扫描容器中所有标注@DomainEventHandler注解的类并注册
            ├── IHandler.java \\ 领域事件处理类接口，所有领域事件处理类都需实现此接口
            └── facade  \\ 领域层（界限上下文）之间门面服务调用接口
                ├── FacadeService.java \\ 门面服务类注解，所有门面服务类都需添加该注解，该注解绑定门面服务接口与其实现类
                ├── FacadeServiceRegistry.java \\ 门面服务类注册中心，FacadeServiceRegistry在应用启动时会扫描容器中所有标注@FacadeService注解的类并注册
                ├── IFacadeService.java \\ 门面服务类接口，所有对领域外提供门面服务类都需实现此接口
```
