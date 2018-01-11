
##【FFmpeg】FFmpeg常用基本命令

1.分离视频音频流

	ffmpeg -i input_file -vcodec copy -an output_file_video　　//分离视频流
	ffmpeg -i input_file -acodec copy -vn output_file_audio　　//分离音频流
2.视频解复用

	ffmpeg –i test.mp4 –vcodec copy –an –f m4v test.264
	ffmpeg –i test.avi –vcodec copy –an –f m4v test.264
3.视频转码

	ffmpeg –i test.mp4 –vcodec h264 –s 352*278 –an –f m4v test.264              //转码为码流原始文件
	ffmpeg –i test.mp4 –vcodec h264 –bf 0 –g 25 –s 352*278 –an –f m4v test.264  //转码为码流原始文件
	ffmpeg –i test.avi -vcodec mpeg4 –vtag xvid –qsame test_xvid.avi            //转码为封装文件
	//-bf B帧数目控制，-g 关键帧间隔控制，-s 分辨率控制
4.视频封装

	ffmpeg –i video_file –i audio_file –vcodec copy –acodec copy output_file
5.视频剪切

	ffmpeg –i test.avi –r 1 –f image2 image-%3d.jpeg        //提取图片
	ffmpeg -ss 0:1:30 -t 0:0:20 -i input.avi -vcodec copy -acodec copy output.avi    //剪切视频
//-r 提取图像的频率，-ss 开始时间，-t 持续时间
6.视频录制

	ffmpeg –i rtsp://192.168.3.205:5555/test –vcodec copy out.avi
7.YUV序列播放

	ffplay -f rawvideo -video_size 1920x1080 input.yuv
8.YUV序列转AVI

	ffmpeg –s w*h –pix_fmt yuv420p –i input.yuv –vcodec mpeg4 output.avi
常用参数说明：

主要参数：
-i 设定输入流
-f 设定输出格式
-ss 开始时间
视频参数：
-b 设定视频流量，默认为200Kbit/s
-r 设定帧速率，默认为25
-s 设定画面的宽与高
-aspect 设定画面的比例
-vn 不处理视频
-vcodec 设定视频编解码器，未设定时则使用与输入流相同的编解码器
音频参数：
-ar 设定采样率
-ac 设定声音的Channel数
-acodec 设定声音编解码器，未设定时则使用与输入流相同的编解码器
-an 不处理音频

##[总结]FFMPEG视音频编解码零基础学习方法

[http://blog.csdn.net/leixiaohua1020/article/details/15811977](http://blog.csdn.net/leixiaohua1020/article/details/15811977)
