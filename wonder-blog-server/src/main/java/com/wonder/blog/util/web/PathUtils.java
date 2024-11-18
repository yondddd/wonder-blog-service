package com.wonder.blog.util.web;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathUtils {

    public static String normalizePath(String... paths) {
        if (paths == null || paths.length == 0) {
            return "";
        }
        return Arrays.stream(paths)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("/"))
                .replaceAll("/+", "/");
    }

    public static void main(String[] args) {
        System.out.println(PathUtils.normalizePath("/file/", "/test/"));
    }

}