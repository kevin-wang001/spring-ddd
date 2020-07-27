DDD框架核心实现
```
com
└── sf
    └── ddd
        └── core
            ├── DomainEventHandler.java \\
            ├── Event.java
            ├── EventDispatcher.java  \\领域事件注册及分发中心，这是事件驱动编程的核心实现类
            ├── IHandler.java
            └── facade  \\领域间门面服务接口类
                ├── FacadeService.java
                ├── FacadeServiceRegistry.java
                ├── IFacadeService.java
```
