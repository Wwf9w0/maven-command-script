package model;

public class FileInfo {

    private String fileName;
    private String root;


    public FileInfo(String fileName, String root){
        this.fileName = fileName;
        this.root = root;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getRoot(){
        return root;
    }

    public void setRoot(String root){
        this.root = root;
    }
}
