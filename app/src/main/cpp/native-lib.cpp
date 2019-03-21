#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jfloat JNICALL Java_com_netofthing_entity_bean_kline_DataParse_getSum(
        JNIEnv *env,
        jobject,
        jint a,
        jint b,
        jfloatArray data,
        jint n
) {
    jfloat sum = 0;
    jfloat *num = env->GetFloatArrayElements( data, NULL);
    for (int i = a; i <= b; i++) {
        sum += num[i];
    }
    return sum/n;
}
JNIEXPORT jstring JNICALL Java_com_netofthing_base_Application_key
        (JNIEnv *env){
    jstring jstring1=env->NewStringUTF("123456");
    return jstring1;
}