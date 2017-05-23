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

日志
----
##2017-05-23

|序号|功能|备注|
|--|--|--|
|1|完成基本权限操作||
|2|修改系统菜单位置||
|3|修改数据库中菜单查询||
|4|修改按钮显示||

##2017-04-23

|序号|功能|备注|
|--|--|--|
|1|添加用户分页查询列表|只有简单分页|
|2|添加角色curd功能||
|3|解决bootstrap中使用modal遮罩只罩住了iframe子页面问题||
|4|扩展modal功能，增加alert功能||
