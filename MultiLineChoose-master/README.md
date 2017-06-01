### Android流式布局实现多行Checkbox功能

**欢迎star、fork **  [https://github.com/crazyandcoder/MultiLineChoose](https://github.com/crazyandcoder/MultiLineChoose)

在我们开发过程中，我们有的时候会碰到这么一种需求，就是需要单选，但是呢？得多行显示要选的内容item，常规做法使用RadioGroup加上RadioButton来实现，但是前提是我们知道所要选择的item个数，如果碰到这么一种情况，所给出的item数量不确定，也就是从后台服务器中获取，有多少显示多少？这就有点郁闷了，没关系，今天我们来提供一种新的实现方式，那就是流式布局来实现类似功能。

### 特点
依赖包体积小、集成方便，提供多种自定义属性，基本能覆盖所有需求。

###apk演示下载

![](http://img.blog.csdn.net/20161028170127631)

[http://fir.im/cv1b](http://fir.im/cv1b)

###效果演示
![](http://img.blog.csdn.net/20161028163119962)

### 使用方法
**gradle引用**

```
compile 'liji.library.dev:multilinechooselib:1.4.0'
```


### 使用方法 

>  代码清单 activity_main.xml

```
            <com.ihidea.multilinechooselib.MultiLineChooseLayout
                android:id="@+id/flowLayout"
                style="@style/FlowLayout"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="5dp">
            </com.ihidea.multilinechooselib.MultiLineChooseLayout>
```

>代码清单  style.xml 自定义属性设置

**自定义属性**
```
<style name="FlowLayout">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_marginTop">5dp</item>
        
        //选项item文字默认值
        <item name="item_textColor">#666666</item>

		//选项item背景色
        <item name="item_backgroundColor">#ffffff</item>

		//选中后选项item的文字颜色
        <item name="item_selectedTextColor">#ffffff</item>

		//选中后选项item的背景颜色
        <item name="item_selectedBackgroundColor">#7289ff</item>

		//选项item的圆角大小
        <item name="item_radius">0dp</item>
	
		//选项item的边缘颜色
        <item name="item_strokeColor">#939393</item>

		//选项item选中后边缘颜色
        <item name="item_selectedStrokeColor">#939393</item>

		//选项item边缘的大小
        <item name="item_strokeWidth">1px</item>

		//水平方向，选项item的间距
        <item name="item_horizontalSpacing">10dp</item>
        
        //选项item内部padding
        <item name="item_horizontalPadding">10dp</item>

		////竖直方向，选项item的间距
        <item name="item_verticalPadding">5dp</item>
        
        //选项item的大小，如果设置wrap_content则包裹它的内容，可设置固定值如80，单位dp
        <item name="item_width">wrap_content</item>
        <item name="item_height">wrap_content</item>


        //限制item文字显示长度
        <item name="item_maxEms">5</item>
        
        //是否可以多选
        <item name="item_multiChooseable">true</item>
    </style>

```

**使用方法**

```
private List<String> mColorData = new ArrayList<>();
private MultiLineChooseLayout singleChoose;
singleChoose = (MultiLineChooseLayout) findViewById(R.id.singleChoose);

		
		mColorData.add("红色");
        mColorData.add("橙色");
        mColorData.add("黄色");
        mColorData.add("绿色");
        mColorData.add("蓝色");
        mColorData.add("灰色");
        mColorData.add("紫色");
        
//设置数据源
singleChoose.setList(mColorData);

//单选
singleChoose.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int position, String text) {
                singleChooseTv.setText("结果：position: " + position + "   " + text);
            }
        });

//取消选中项
singleChoose.cancelAllSelectedItems();
 
        
``` 
**如果要设置多选，请在style中设置item_multiChooseable=true**
**如果要设置流式布局，则将item的宽和高都设置为wrap_content**

### 常用方法介绍

**1、设置数据源**
```
public void setList(List<String> tagList);
```


**2、设置默认的选中项**

```
public int setIndexItemSelected(int position)
```

**3、获取选中的item内容**
```
protected String getSelectedItemText()
```

**4、多选情况下，返回所有选中的item内容**

```
public String[] getAllItemSelectedTextWithStringArray()
```


**5、多选情况下，返回所有选中项的下标**

```
public ArrayList<Integer> getAllItemSelectedIndex()
```

 

**6、取消所有选中项**

```
public void cancelAllSelectedItems() 
```

**7、设置item点击事件**

```
public void setOnItemClickListener(onItemClickListener l);

//点击事件，返回点击的position和选中项
 public interface onItemClickListener {
        void onItemClick(int position, String text);
 }
```
 
**more 。。。**


----------


**关于作者**

 1. QQ： 275137657
 2. github： https://github.com/crazyandcoder
 3. 个人博客：http://crazyandcoder.github.io/
