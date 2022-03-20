package com.immoc.diners.lambda.file;
@FunctionalInterface
public interface FileConsumer {
    void fileHandler(String fileContent);
}
