ACK SYSTEM
==========

简介
----

软件版本 : 1.0.0

* 本工程主要是对新人来做的，一个集成性质的脚手架，省去了很多重复劳动。可以快速进入开发
* 工程采用maven构建
* jdk1.8 + 
* 前端页面基于`ACE Admin`框架
* 后端采用 `spring`,`mybatis`
* 权限管理部分,这个是仿照的`shiro`(其实就是自定义了一个权限注解)
* 用户列表采用的是`datatable`这个前端组件，功能很强大(没有做排序和搜索)
* 其他列表采用自己写的一个基于bootstrap分页工具[https://github.com/lzjlvlzj/pagination](https://github.com/lzjlvlzj/pagination)

工程目录简介
--------


|序号|工程名称|功能|备注|
|--|--|--|--|
|1|ack_sys|工程总文件夹|母工程|
|2|ack-pojo|java bean||
|3|ack-base-persist|基础数据持久层|提供数据基本curd接口|
|4|ack-persist|mysql数据库持久层|依赖ack-base-persist|
|5|ack-base-service|服务层基础封装|提供简单curd|
|6|ack-service|服务层封装|依赖ack-base-service|
|7|ack-base-web|控制层封装|主要对基本curd进行了封装|
|8|ack-admin-web|后台管理控制层主要逻辑|依赖ack-base-web|
|9|ack-admin-web-ui|页面和css,js,图片等静态资源|母工程|
|10|ack-web|pc端展示给用户用|暂时没有开发|
|11|ack-common|常用的东西|逻辑复杂|
|12|ack-util|工具类|逻辑简单|
|13|ack-exception|异常|自定义异常|
|14|iamge|工程readme图片|图片|



注意
--
权限部分采用了map存储这个修改权限后要跟新，要不然不起作用。这个部分以后是要放到redis或者memcache缓存中的。

例子
--
[这里](http://www.itblg.com:8080/)

`user : zsan`

`pass : 123`

安装使用
------
**下载**

`git clone https://github.com/lzjlvlzj/ack_sys.git`
**开发**

下载后，导入到 Eclipse或者MyEclipse(注意是Maven工程)

正确导入后是这个样子的:

![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/1.png)

导入到开发环境后，我们还需要为开发做下面的准备(这个需要把数据提前导入到数据库)

* 配置maven命令

点击菜单栏上的![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/2.png) 选择 `Debug Configrations`

按步骤创建命令
![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/3.png)

`install`:

![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/4.png)

`jetty:run`:

这个是jetty运行的命令(容器是在响应工程里的pom.xml配置的例子请看`ack-admin-web-ui`的`pom.xml`,当然也可以配置`tomcat`,需要的请自行配置)

![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/5.png)

注意：第一次启动项目时间会久点，请耐心等待。

`clean`:

![image](https://github.com/lzjlvlzj/ack_sys/blob/master/image/6.png)


**部署**

数据初始化:

* `ack_sys.sql`将这个导入到mysql数据库

* 修改`ack-persist`工程中 `db.properties`中数据库地址

编译(请确保本地有maven环境) : 

* 解压 `ack_sys-master.zip`
 
* `cd ack_sys-master`

* `mvn package`

工程`ack-amdin-web-ui`中的war文件就是我们要部署的文件。

部署：

将上面war文件部署到tomcat(7+)或者其他web容器中,启动容器完成后访问即可。

日志
----

## 2018-06-13

|序号|功能|备注|
|--|--|--|
|1|修改iframe显示不完整bug||

## 2017-05-25

|序号|功能|备注|
|--|--|--|
|1|datatable添加排序搜索功能||

## 2017-05-24

|序号|功能|备注|
|--|--|--|
|1|添加权限and or逻辑操作功能|只支持一个逻辑符号|


## 2017-05-23

|序号|功能|备注|
|--|--|--|
|1|完成基本权限操作||
|2|修改系统菜单位置||
|3|修改数据库中菜单查询||
|4|修改按钮显示||

## 2017-04-23

|序号|功能|备注|
|--|--|--|
|1|添加用户分页查询列表|只有简单分页|
|2|添加角色curd功能||
|3|解决bootstrap中使用modal遮罩只罩住了iframe子页面问题||
|4|扩展modal功能，增加alert功能||
