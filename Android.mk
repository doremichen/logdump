#
# Android make file
#

LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src) $(call all-renderscript-files-under, src)
#LOCAL_SDK_VERSION := current

LOCAL_PACKAGE_NAME := LogCat

include $(BUILD_PACKAGE)
