#include<stdio.h>
#include<stdlib.h>
#include<jni.h>

/*
 * Class:     jni_Hello
 * Method:    fromJniString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jni_Hello_fromJniString(JNIEnv * env,
		jobject obj) {
	char* str = "hello from jniString";
	jstring jstr = *env->NewStringUTF(env, str);
	return jstr;
}

/*
 * Class:     jni_Hello
 * Method:    fromJniInt
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jint JNICALL Java_jni_Hello_fromJniInt(JNIEnv * env, jobject obj) {

}

