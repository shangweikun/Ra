cmake_minimum_required(VERSION 3.27)
project(demo2_C C)

set(CMAKE_C_STANDARD 11)

add_library(demo2_C STATIC library.c
        HelloWolrd.c)
