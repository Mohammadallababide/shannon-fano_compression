package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageResult implements Serializable,Result {
    String fileName;
    Map<List<Boolean>,Character> result=new HashMap<>();
    byte[] data;

    public Map<List<Boolean>, Character> getResult() {
        return result;
    }

    public void setResult(Map<List<Boolean>, Character> result) {
        this.result = result;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
