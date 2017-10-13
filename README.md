ACK SYSTEM
==========

简介
----

软件版本 : 1.0.0

* 目录结构，架构基本信息请参考master分支

* 本工程主要功能公司oa办公

部署
----

* 下載该项目

`git clone https://github.com/lzjlvlzj/ack_sys.git`

* 编译部署

`unzip ack_sys-easyoa.zip`

`cd ack_sys-easyoa`

`mvn package`

* sql初始化

登陆mysql

`source ack_sys.sql`

* 准备web容器

这里用tomcat8

从`ack-admin-web-ui`这个目录target下找到ack-admin-web-ui.war 将这个部署为ROOT项目

启动tomcat





