package com.example.audiobook_generator.pdf;

public class Pdf {
    // A basic PDF class that is being used to store
    // basic information regarding a users input pdf

    private String fileName;
    private String contents;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Pdf(String fileName, String contents) {
        this.fileName = fileName;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "PDF{" +
                "fileName='" + fileName + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
