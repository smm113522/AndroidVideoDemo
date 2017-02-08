#node 服务器

## node 是什么
	
## node 基础语法

## node 开发工具
	1.JetBrains WebStorm 2016.3.2(64)
	2.HBuilder

## node 基本操作

## node 应用
	1.爬虫
## node web 应用
	1.express 
	   案例https://gold.xitu.io/entry/5884ee911b69e60058e283a0
	   https://github.com/liumingmusic/react-native_toiletApp
	   启动起来了web端，app 端没有启动，接口可以走
	2.

## node 好网站 和 好的入门网站
1.https://cnodejs.org/	
2.https://github.com/alsotang/node-lessons
3.http://nodejs.cn/
4.http://www.cnblogs.com/Leo_wl/p/4361289.html

## one project

之下所有操作都是在命令实现的
1、express开发
1.1 npm环境设置

    1.安装node软件：https://nodejs.org/en
    2.安装淘宝滤镜：npm install -g cnpm --registry = https://registry.npm.taobao.org
    3.设置全局的npm从国内下载资源， npmrc添加配置。mac下面地址为 /Users/liumingming/.npmrc，修改strict-ssl=true 和 registry=https://registry.npm.taobao.org

1.2 express环境搭建

    1.安装express-generator：npm install -g express-generator，用户快速创建express项目
    2.生成项目模块：进入到项目目录 /User/liumm/A_study/app/toiletApp 下面，执行命令 express -e service，其中-e为ejs模块简写
    3.在服务端项目安装依赖：进入服务端项目 /User/liumm/A_study/app/toiletApp/service 执行命令 cnpm install，安装依赖类库
    4.启动项目：使用在当前目录中使用 npm start启动项目，其中start命令在package.json已经配置
    5.预览：启动已经开发本地的 localhost:3000，访问地址即可看见启动的页面
    6.修改预览：项目中app.js 文件为服务启动入口路径。修改项目下面 views/index.ejs文件，重启服务进行查看
    7.express修改热加载：安装supervisor，npm install supervisor -g，修改项目自动更新

根据环境之后基本步骤：http://blog.sina.com.cn/s/blog_a29eae2b0102vuey.html

1.工程的创建， 目录为three
	express three -s
	
2.cd three 打开文件夹

3.express -e three 编译程序

**这个必须进行
4.npm install npm 添加
从而可以添加mysql 等其他工具了

**这个必须进行
5.npm init 然后初始化， 

6.进行数据添加 然后添加json 里的数据了 yes 必须写上

7.npm start 就运行了。
就可以运行浏览器了，上面就有欢迎界面了。

8.http://localhost:3000/ 就有了welcome 了。

里面有配置和里面文件的作用。，
看blog 
1.https://smm113522.github.io/2017/01/25/nodejs+express%E4%BB%8E%E5%85%A5%E9%97%A8%E5%88%B0%E6%94%BE%E5%BC%83/
2.https://smm113522.github.io/2017/01/26/nodejs+express%E4%BB%8E%E6%94%BE%E5%BC%83%E5%88%B0%E5%85%A5%E9%97%A8%E6%80%9D%E8%80%83/

## WebStorm 中开发项目


 

##node 必学的技术 
1.Jade 是一个高性能的模板引擎，它深受 Html 影响，它是用 JavaScript 实现的，并且可以供 Node 使用。
https://segmentfault.com/a/1190000000357534
2. html2jade
http://html2jade.vida.io/
http://www.html2jade.org/

## node 开发后台

## node第三方
1，superagent	(http://visionmedia.github.io/superagent/ ) 是个 http 方面的库，可以发起 get 或 post 请求。

2，cheerio		(https://github.com/cheeriojs/cheerio ) 大家可以理解成一个 Node.js 版的 jquery，用来从网页中以 css selector 取数据，使用方式跟 jquery 一样一样的。	

3, express 		官网是 http://expressjs.com/ ，是 Node.js 应用最广泛的 web 框架，现在是 4.x 版本，它非常薄。跟 Rails 比起来，完全两个极端。 

4，eventproxy   三个库：superagent cheerio eventproxy(https://github.com/JacksonTian/eventproxy )	


##node 推荐书籍
《九浅一深Node.js》


	
#java web 开发
## 基础语法

## 基本操作

## 应用

## web 框架
	1.spring mvc
	2.spring boot
	
#php web 开发

## 基础语法

##  应用

## 框架

## 好的学习网站
1.http://lib.csdn.net/base/17
