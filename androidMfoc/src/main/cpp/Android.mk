LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

TARGET_PLATFORM := android-24
TARGET_ARCH_ABI := armeabi-v7a
TARGET_ABI := android-24-armeabi-v7a # concatenation of above two variables

LOCAL_CPP_FLAGS = -DDEBUG
LOCAL_C_INCLUDES := cpp/include
LOCAL_MODULE    := mfoc
LOCAL_SRC_FILES := fake-libnfc.c fake-stdio.c mfoc.c \
	src/mfoc.c src/crapto1.c src/crypto1.c src/mifare.c src/nfc-utils.c

include $(BUILD_SHARED_LIBRARY)