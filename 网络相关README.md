
### 涵盖Android方方面面的技术, 目前保持更新. 时刻与Android开发流行前沿同步.

------------------------------------------------------


## 网络编程
### 网络加载框架

> 解决各种协议(GET, POST, PUT, HEAD, DETELE...)的网络数据的获取及请求, 支持异步,同步请求; 文件多线程下载断点续传, 上传; 请求自动重试, gzip压缩, Cookies自动解析并持久化. 数据的缓存. 目标是让网络请求更方便, 简介, 高效, 稳定.

名称 | 概要 | 详情
--- | --- | --- | ---
[*Retrofit2.0](http://square.github.io/retrofit/) | 以接口/注解的形式定义请求和响应 |  Square 开源的项目. 是一套RESTful架构的Android(Java)客户端实现，基于注解，提供JSON to POJO(Plain Ordinary Java Object,简单Java对象)，POJO to JSON，网络请求(POST，GET,PUT，DELETE等)封装。 Jake Wharton大神力荐. 本身的网络核心可以替换. 如Apache HTTP client, URL connection, OKHttp等, 数据解析核心也可以替换如Gson, Jackson, fastjson, xStream等. 力求用最少的代码, 实现最强大的功能. [官方主页](http://square.github.io/retrofit/)
[*okhttp](https://github.com/square/okhttp) | 一个为安卓和java应用诞生的Http+SPDY的网络处理库 | square开源项目. a. 支持HTTP, HTTPS, HTTP/2.0, and SPDY协议 b. 自动缓存数据, 节省流量, c.内部自动GZIP压缩内容.
[android-async-http](https://github.com/loopj/android-async-http) | 一个异步的AndroidHttp库 | 比较经典的网络请求库, 基于Apache的HttpClient库实现, 但是由于AndroidM(6.0)去除了对HttpClient相关API, 意味着google不再推荐使用.
[Volley](https://github.com/mcxiaoke/android-volley) | 一个能让Android的网络请求更简单快捷的Http库 | [官方地址, 需翻墙](https://android.googlesource.com/platform/frameworks/volley) Volley集成了AsyncHttpClient和Universal-Image-Loader的优点，既可以像AsyncHttpClient一样非常简单地进行HTTP通信，也可以像Universal-Image-Loader一样轻松加载网络上的图片。但是对大数据量的网络操作如文件的下载支持较差

### 网络加载相关博文
##### [用 Retrofit 2 简化 HTTP 请求](https://realm.io/cn/news/droidcon-jake-wharton-simple-http-retrofit-2/)
##### [快速Android开发系列网络篇之Retrofit](http://www.cnblogs.com/angeldevil/p/3757335.html)
##### [Android OkHttp完全解析 是时候来了解OkHttp了](http://blog.csdn.net/lmj623565791/article/details/47911083)
##### [快速Android开发系列网络篇之Android-Async-Http](http://www.cnblogs.com/angeldevil/p/3729808.html)
##### [Android Volley完全解析(一)，初识Volley的基本用法](http://blog.csdn.net/guolin_blog/article/details/17482095)
##### [Android Volley完全解析(二)，使用Volley加载网络图片](http://blog.csdn.net/guolin_blog/article/details/17482165)
##### [Android 网络通信框架Volley简介(Google IO 2013)](http://blog.csdn.net/t12x3456/article/details/9221611)
