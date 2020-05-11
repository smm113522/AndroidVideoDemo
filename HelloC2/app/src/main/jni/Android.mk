# 当前路径
LOCAL_PATH := $(call my-dir)

# 清除LOCAL_XXX变量
include $(CLEAR_VARS)

## 原生库名称
#LOCAL_MODULE := hello
#
## 原生代码文件
#LOCAL_SRC_FILES =: hello.cpp

LOCAL_MODULE        := libmp3lame

LOCAL_SRC_FILES     := \
libmp3lame/bitstream.c \
libmp3lame/encoder.c \
libmp3lame/fft.c \
libmp3lame/gain_analysis.c \
libmp3lame/id3tag.c \
libmp3lame/lame.c \
libmp3lame/mpglib_interface.c \
libmp3lame/newmdct.c \
libmp3lame/presets.c \
libmp3lame/psymodel.c \
libmp3lame/quantize.c \
libmp3lame/quantize_pvt.c \
libmp3lame/reservoir.c \
libmp3lame/set_get.c \
libmp3lame/tables.c \
libmp3lame/takehiro.c \
libmp3lame/util.c \
libmp3lame/vbrquantize.c \
libmp3lame/VbrTag.c \
libmp3lame/version.c \
wrapper.c

LOCAL_CFLAGS = -DSTDC_HEADERS



LOCAL_LDLIBS := -llog

# 编译动态库
include $(BUILD_SHARED_LIBRARY)