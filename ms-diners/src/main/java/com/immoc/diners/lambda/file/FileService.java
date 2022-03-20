package com.immoc.diners.lambda.file;

import java.io.*;

public class FileService {
    public void fileHandle(String url,FileConsumer fileConsumer) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
        //定义行变量
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line+"\n");

        }
        fileConsumer.fileHandler(stringBuilder.toString());
    }
}
