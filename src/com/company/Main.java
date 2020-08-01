package com.company;

public class Main {

    public static void main(String[] args) {
        ShannonFanoCompression shannonFanoCompression=new ShannonFanoCompression();

        //compress folder
        shannonFanoCompression.compress("D:\\multiMedia2\\src\\ss");
        //that will compress the folder, now we can delete it and then de compress the previous result
        shannonFanoCompression.decompress("D:\\multiMedia2\\src\\ss.shfo");

          //compress image
          shannonFanoCompression.compress("D:\\multiMedia2\\src\\ss\\tt.jpg");
        //that will compress the image, now we can delete it and then de compress the previous result
        shannonFanoCompression.decompress("D:\\multiMedia2\\src\\ss\\tt.jpg.shfo");

          //compress file
        shannonFanoCompression.compress("D:\\multiMedia2\\src\\ss\\Samples.txt");
        //that will compress the file now, we can delete it and then de compress the previous result
        shannonFanoCompression.decompress("D:\\multiMedia2\\src\\ss\\Samples.txt..shfo");

        //compress image using JPEG
        Node.JPEGCompress("D:\\multiMedia2\\src\\ss\\tt.jpg");



    }
}
