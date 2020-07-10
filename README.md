# my_blog
## 页面展示
### [项目地址](http://www.cchobo.com)

**首页展示**
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020063022245688.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630222512502.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630222512406.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
**个人信息**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118134645983.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
**标签**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118134900209.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
**创作页面**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118135027475.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)

**博文查看页面**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118135159574.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118135210906.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)

## 项目需求
***
**功能需求**
1. 编写博文（仅对网站管理员开放）
2. 个人介绍
3. 访客注册登录
4. 根据标签分类博文
5. 用户评论博文
6. 用户留言板

**安装部署**
 1. 此项目为maven项目，可用maven打成jar包并运行
 2. MySQL版本为8.0、Java版本为1.8

## 项目设计
****
1. 项目构建：Maven
2. web框架：Springboot
3. 数据库ORM：Mybatis
4. 数据库连接池： Druid
5. 分页插件：PageHelper
6. 数据库：MySql
7. 缓存：Redis
8. 文章展示：CkEditor.md
9. 反向代理：nginx

**环境**
| 工具 |名称  |
|--|--|
| 编辑器 | Intellij Idea |
|项目构建|maven|
|语言|JAVA、Html、js、css|
|项目框架|springboot、mybatis|
|安全框架|spring sercurity|
|登录验证方式|JWT|
|缓存|redis|
|数据库|MySQL8.0|
|运行环境|阿里云Centos7| 

## 业务设计
**发布文章**
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly96aHktbXlibG9nLm9zcy1jbi1zaGVuemhlbi5hbGl5dW5jcy5jb20vcHVibGljLyVFNSU4RCU5QSVFNSVBRSVBMiVFNiU5NyVBNSVFNSVCRiU5Ny9TcHJpbmdCb290JUU0JUI5JThCJUU0JUJCJThFJUU5JTlCJUI2JUU2JTkwJUFEJUU1JUJCJUJBJUU1JThEJTlBJUU1JUFFJUEyJUU3JUJEJTkxJUU3JUFCJTk5LzIwMTgwODAyMTQxMjIxLnBuZw?x-oss-process=image/format,png)
**登录**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200118142058686.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUzNTkxMw==,size_16,color_FFFFFF,t_70)
**修改个人资料**
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly96aHktbXlibG9nLm9zcy1jbi1zaGVuemhlbi5hbGl5dW5jcy5jb20vcHVibGljLyVFNSU4RCU5QSVFNSVBRSVBMiVFNiU5NyVBNSVFNSVCRiU5Ny9TcHJpbmdCb290JUU0JUI5JThCJUU0JUJCJThFJUU5JTlCJUI2JUU2JTkwJUFEJUU1JUJCJUJBJUU1JThEJTlBJUU1JUFFJUEyJUU3JUJEJTkxJUU3JUFCJTk5LzIwMTgwODAyMTQzMjMwLnBuZw?x-oss-process=image/format,png)

## 开发流程
****
**数据库CRUD**
- controller层中编写前端接口，接收前端参数
- service层中编写所需业务接口，供controller层调用
- 实现service层中的接口，并注入mapper层中的sql接口
- 采用Mybatis的xml方式编写Sql语句。需要自己手写所有sql语句

**页面展示**
- 前后端完全分离，前端模板来自[模板之家](http://www.cssmoban.com/)，并由前端小伙伴帮助我搭建
- 页面跳转由前端控制

**其它功能**
- 访客量统计：记录来访者的ip，将ip存入缓存，计入本次访问请求，若缓存未过期，则访客量不会更改。
- 留言：数据库设计和业务逻辑比较难，类似树结构

**网站建设**
- 服务器选用的是阿里云centos7
- 域名是阿里云上购买的.com的域名
- 网站备案，按照阿里云的流程走大概14天左右时间，需要上传个人身份信息以及邮寄个人资料过去。

##  总结
**未来需增加的功能**
- 增加文章分享至QQ、微信、微博中功能
- 用户可在线写文章功能
- 用户收藏文章功能
- 用户消息通知功能

**项目需要优化部分**
- 部分界面手机端适配不良好
- 部分功能会暴露服务器ip


**以上就是搭建博客的一些心得体会，有兴趣的小伙伴可以star一手，或者qq联系**

**QQ** ：1056024860


