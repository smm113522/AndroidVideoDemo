#include "com_seedling_helloc_MainActivity.h"

extern "C" JNIEXPORT jstring JNICALL Java_com_seedling_helloc_MainActivity_hello
        (JNIEnv *env, jobject) {
    return env->NewStringUTF("Hello from C++ hello world");
}
