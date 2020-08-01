package com.company;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Node implements Comparable {
    static Double All;
    char symbol;
    Double repetition = 0.0;
    public ArrayList<Boolean> bitCode = new ArrayList<>();

    public ArrayList<Boolean> getBitCode() {
        return bitCode;
    }

    public void setBitCode(ArrayList<Boolean> bitCode) {
        this.bitCode = bitCode;
    }

    public static Double getAll() {
        return All;
    }

    public static void setAll(Double all) {
        All = all;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Double getRepetition() {
        return repetition;
    }

    public void setRepetition(Double repetition) {
        this.repetition = repetition;
    }

    public Double getProbability() {
        return repetition / All;
    }

    @Override
    public int compareTo(Object o) {

        Double compareProbability = ((Node) o).getProbability();
        /* For Ascending order*/
        Double a = (compareProbability - this.getProbability()) * 1000000000;
        return a.intValue();
    }


    public void addRepetition() {
        repetition++;
    }
    static byte[] toByteArray(Boolean[] bools) {
        BitSet bits = new BitSet(bools.length);
        for (int i = 0; i < bools.length; i++) {
            if (bools[i]) {
                bits.set(i);
            }
        }

        byte[] bytes = bits.toByteArray();
        if (bytes.length * 8 >= bools.length) {
            return bytes;
        } else {
            return Arrays.copyOf(bytes, bools.length / 8 + (bools.length % 8 == 0 ? 0 : 1));
        }
    }

    static boolean[] toBooleanArray(byte[] bytes) {
        BitSet bits = BitSet.valueOf(bytes);
        boolean[] bools = new boolean[bytes.length * 8];
        for (int i = bits.nextSetBit(0); i != -1; i = bits.nextSetBit(i+1)) {
            bools[i] = true;
        }
        return bools;
    }

    public static String readImageAsBase64(String imagePath) {
        // System.out.println(imagePath.equals("D:\\multiMedia\\src\\t3.jpg"));
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println(" Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }
    public static void writeBase64ToFileAsArrayOfBytes(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
    public static void JPEGCompress(String imageName){
        try {
            File imageFile = new File(imageName);
            File compressedImageFile = new File(imageFile.getParent()+"\\"+getFileNameWithoutExtension(imageFile)+"test1.jpg");

            InputStream is = new FileInputStream(imageFile);
            OutputStream os = new FileOutputStream(compressedImageFile);

            float quality = 0.5f;

            // create a BufferedImage as the result of decoding the supplied InputStream
            BufferedImage image = ImageIO.read(is);

            // get all image writers for JPG format
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

            if (!writers.hasNext())
                throw new IllegalStateException("No writers found");

            ImageWriter writer = (ImageWriter) writers.next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            // compress to a given quality
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);

            // appends a complete image stream containing a single image and
            //associated stream and image metadata and thumbnails to the output
            writer.write(null, new IIOImage(image, null, null), param);

            // close all streams
            is.close();
            os.close();
            ios.close();
            writer.dispose();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getFileNameWithoutExtension(File file) {
        String fileName = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }

        return fileName;

    }


}
