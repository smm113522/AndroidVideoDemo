package com.kesun.png.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetImagePathUtil {

    /**
     * 得到网页中图片的地址
     */
    public static ArrayList<String> getImgList(String content) {
        ArrayList<String> srcList = new ArrayList<String>(); //用来存储获取到的图片地址
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//匹配字符串中的img标签
        Matcher matcher = p.matcher(content);
        boolean hasPic = matcher.find();
        if(hasPic == true)//判断是否含有图片
        {
            while(hasPic) //如果含有图片，那么持续进行查找，直到匹配不到
            {
                String group = matcher.group(2);//获取第二个分组的内容，也就是 (.*?)匹配到的
                Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//匹配图片的地址
                Matcher matcher2 = srcText.matcher(group);
                if( matcher2.find() )
                {
                    srcList.add( matcher2.group(3) );//把获取到的图片地址添加到列表中
                }
                hasPic = matcher.find();//判断是否还有img标签
            }

        }
        return srcList;
    }
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
// String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
// 得到<img />数据
            img = m_image.group();
// 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

}