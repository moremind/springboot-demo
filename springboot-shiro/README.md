##springboot

### shiro三大核心
#### 1.subject
subject获取到的是当前用户的安全操作

#### 2.securityManager
securityManager它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。
#### 3.realm
Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
它封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。