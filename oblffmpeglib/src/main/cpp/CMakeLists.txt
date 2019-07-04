cmake_minimum_required(VERSION 3.4.1)

set(lib_src_DIR ${CMAKE_SOURCE_DIR}/../../../libs/${ANDROID_ABI})


add_library( # Sets the name of the library.
        ffmpegcmd
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        include/ffmpeg/cmdutils.c
        include/ffmpeg/ffmpeg.c
        include/ffmpeg/ffmpeg_filter.c
        include/ffmpeg/ffmpeg_opt.c
        include/ffmpeg/ffmpeg_cmd.c
        include/ffmpeg/ffmpeg_thread.c)
add_library(
        avcodec
        SHARED
        IMPORTED)

set_target_properties(
        avcodec
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libavcodec.so)

add_library(
        avfilter
        SHARED
        IMPORTED)

set_target_properties(
        avfilter
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libavfilter.so)

add_library(
        avformat
        SHARED
        IMPORTED)

set_target_properties(
        avformat
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libavformat.so)

add_library(
        avutil
        SHARED
        IMPORTED)
set_target_properties(
        avutil
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libavutil.so)

add_library(
        postproc
        SHARED
        IMPORTED)

set_target_properties(
        postproc
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libpostproc.so)

add_library(
        swresample
        SHARED
        IMPORTED)

set_target_properties(
        swresample
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libswresample.so)

add_library(
        swscale
        SHARED
        IMPORTED)

set_target_properties(
        swscale
        PROPERTIES IMPORTED_LOCATION
        ${lib_src_DIR}/libswscale.so)

find_library(
        log-lib
        log)
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp)
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/include)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

target_link_libraries(
        ffmpegcmd
        avcodec
        avfilter
        avformat
        avutil
        postproc
        swresample
        swscale
        ${log-lib})