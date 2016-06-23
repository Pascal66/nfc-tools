LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := mfoc
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	C:\Users\Pascal\git\nfc-tools\androidMfoc\src\main\jni\Android.mk \
	C:\Users\Pascal\git\nfc-tools\androidMfoc\src\main\jni\fake-libnfc.c \
	C:\Users\Pascal\git\nfc-tools\androidMfoc\src\main\jni\fake-stdio.c \
	C:\Users\Pascal\git\nfc-tools\androidMfoc\src\main\jni\mfoc.c \

LOCAL_C_INCLUDES += C:\Users\Pascal\git\nfc-tools\androidMfoc\src\main\jni
LOCAL_C_INCLUDES += C:\Users\Pascal\git\nfc-tools\androidMfoc\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
