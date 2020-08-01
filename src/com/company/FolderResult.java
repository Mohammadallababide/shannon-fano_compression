package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class FolderResult implements Serializable,Result {
    String FolderName;
    ArrayList<Result> results=new ArrayList<>();

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
