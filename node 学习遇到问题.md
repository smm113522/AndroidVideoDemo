#node 服务器

## node 是什么
	https://www.ibm.com/developerworks/cn/opensource/os-nodejs/
## node 基础语法教程
http://www.hubwiz.com/course/5359f6f6ec7452081a7873d8/

	http://blog.xcoder.in/node-learning/2013/08/16/node-3-base/

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
	5.https://segmentfault.com/a/1190000000348772
	6.https://cnodejs.org/getstart/

## 开发工具
	使用Nodeclipse开发Express项目
	WebStorm 2016.3.2 

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
	2. https://smm113522.github.io/2017/01/26/nodejs+express%E4%BB%8E%E6%94%BE%E5%BC%83%E5%88%B0%E5%85%A5%E9%97%A8%E6%80%9D%E8%80%83/

## WebStorm 中开发项目
------------------------------------------------------------------
	node 对html 的支持
	cmd进入到项目目录，安装ejs：npm install --save ejs
	让node js express 支html模板
	导入ejs
	var ejs = require('ejs');
	指定html引擎
	app.engine('.html', ejs.__express);
	视图引擎
	app.set('view engine', 'html');

	html页面代码与ejs相同
	 <body>
	  <%=title%>
	 </body>
--------------------------------------------------------------
	添加404 页面
	app.js 中添加
	// 404
	app.get('*', function(req, res){
		res.render('404.html', {
			title: 'No Found'
		})
	});
----------------------------------------------------------------
	1.安装模块
	npm install <Module Name> -参数

	如果带参数-g表示全局安装，否则只是安装到某个目录下。

	以下实例，我们使用 npm 命令安装常用的 Node.js web框架模块 express
	2.卸载模块
	我们可以使用以下命令来卸载 Node.js 模块。
	npm uninstall <Module Name>

	如先使用安装指令安装bootstrap:

	npm install bootstrap

	再使用卸载指令删除模块：

	npm uninstall bootstrap

	可以到 /node_modules/ 目录下查看包是否还存在
	3.模块列表
	使用模块列表命令可以方便的看到当前项目中依赖的包：
	npm ls
	5.更新模块
	我们可以使用以下命令更新模块：
	npm update 模块名称
	npm up -g 模块名称
	6.搜索模块
	npm search 模块名称
	7.更换NPM 镜像
	因为npm的服务器在国外，在网络状态不好的情况下引入一个模块会因为网络延迟而失败，可以更换成国内速度更快的镜像服务器，这里以使用淘宝 NPM 镜像（http://npm.taobao.org/）为例：

	npm install -g cnpm --registry=https://registry.npm.taobao.org
---------------------------------------------------------------------------------------------
	// app.engine('.html', ejs.__express);
	// app.set('view engine', 'html');
	
	转换成其他的后缀名的方法
----------------------------------------------------------------------------------------------------

## node 项目
https://github.com/nswbmw/N-blog
https://github.com/jyy12/AcCms
	 

##node 必学的技术 
	1.Jade 是一个高性能的模板引擎，它深受 Html 影响，它是用 JavaScript 实现的，并且可以供 Node 使用。
	https://segmentfault.com/a/1190000000357534
	2. html2jade
	http://html2jade.vida.io/
	http://www.html2jade.org/
	3.vue 
## node 开发后台
	基于Nodejs+express4+Mongodb+Angularjs建立web项目


## node第三方
	0，utility   md5 加密
	
	1，superagent	(http://visionmedia.github.io/superagent/ ) 是个 http 方面的库，可以发起 get 或 post 请求。

	2，cheerio		(https://github.com/cheeriojs/cheerio ) 大家可以理解成一个 Node.js 版的 jquery，用来从网页中以 css selector 取数据，使用方式跟 jquery 一样一样的。	

	3, express 		官网是 http://expressjs.com/ ，是 Node.js 应用最广泛的 web 框架，现在是 4.x 版本，它非常薄。跟 Rails 比起来，完全两个极端。 

	4，eventproxy   高并发 三个库：superagent cheerio eventproxy(https://github.com/JacksonTian/eventproxy )	

	5，async  
	
	6，
## node 推荐书籍

	《九浅一深Node.js》
	书籍地址<https://github.com/pana/node-books>

## vue 基础
http://cn.vuejs.org/v2/guide/
http://www.cnblogs.com/rik28/p/6024425.html
## vue 是什么
Vue.js 不是一个框架，它只是一个提供 MVVM 风格的双向数据绑定的库，专注于 UI 层面。Vue.js 提供的核心是 MVVM 中的 VM
## 操作后台也可以做

## Weex 项目基础
https://weex.apache.org/cn/guide/set-up-env.html
https://weex.apache.org/cn/references/components/a.html

## weex-toolkit
https://github.com/weexteam/weex-toolkit
## weex 打包 android
http://www.weex.help/topic/58dcbbf22668808722df284f
怎么都离不开node

weexpack 去创建和打包apk


#php web 开发

## 基础语法

##  应用

## 框架

## 好的学习网站
1.http://lib.csdn.net/base/17



##  react-native android 开发

## 环境搭建
	1.jdk 
	2.sdk
	win 搭建 http://www.jianshu.com/p/2fdc4655ddf8
	学习地址 http://reactnative.cn/docs/0.20/getting-started.html
	React Native 环境搭建 请查看官网资料：
	http://reactnative.cn/docs/0.41/getting-started.html
	https://github.com/reactnativecn
	// 其他启动方式
	1.首先启动React Native Server 
	react-native start 
	2.重新开一个终端(cmd), 切换到项目目录安装APP 
	cd android
	gradlew.bat installDebug

    执行gradlew.bat installDebug时，请先开启Genymotion模拟器
	3.还可以
	react-native run-android

输入react-native init AwesomeProject，等待一段时间（较慢）
cd AwesomeProject
npm install
react-native start

react-native run-android
即可了

## demo


## 案例 
	https://github.com/bigsui/shopping-react-native
	
	
## 前端

## 好的blog
http://yrq110.me/

## node 打包

## 其他系统下的 node 安装和打包
html5打包 webpack

