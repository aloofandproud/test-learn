package com.immoc.diners.lambda.file;

import java.io.IOException;

public class Test {
    public static void fileHandle() throws IOException {
        FileService fileService = new FileService();
        fileService.fileHandle("/Users/zhuxiaohui/IdeaProjects/food-social-context-parent/ms-diners/src/main/java/com/immoc/diners/lambda/file/FileConsumer.java",fileContent -> {
            System.out.println(fileContent);
        });
    }

    public static void main(String[] args) throws IOException {
        fileHandle();
    }
}
