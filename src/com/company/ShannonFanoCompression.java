package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;


public class ShannonFanoCompression {
    public void compressionFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            stringBuilder.append(new String(buffer));
            buffer = new char[10];
        }
        reader.close();
        String content = stringBuilder.toString();
        ArrayList<Node> nodes = this.getNodes(content);
        shannon2(nodes);
        Map<Character, List<Boolean>> map = new HashMap<>();
        FileResult compressionResult = new FileResult();
        compressionResult.setFileName(fileName);
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node node = nodes.get(i);
            map.put(node.symbol, node.bitCode);
            compressionResult.result.put(node.bitCode, node.symbol);
        }
        List<Boolean> list = new ArrayList<>();

        size = content.length();
        for (int i = 0; i < size; i++) {
            char sympol = content.charAt(i);
            list.addAll(map.get(sympol));
        }


        FileOutputStream f = new FileOutputStream(new File(fileName + ".shfn"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        // Write objects to file
        if (list.size() % 8 != 0) {
            for (int i = 0; i < 8 - (list.size() % 8); i++) {
                list.add(false);
            }
        }
        Boolean[] inputSleep = new Boolean[list.size()];
        inputSleep = list.toArray(inputSleep);
        // System.out.println(Arrays.toString(inputSleep));
        byte[] toReturn = new byte[inputSleep.length / 8];
        toReturn = Node.toByteArray(inputSleep);
        boolean[] bb = Node.toBooleanArray(toReturn);

        compressionResult.setData(Node.toByteArray(inputSleep));
        o.writeObject(compressionResult);
        o.close();
        f.close();
    }

    public void compressionImage(String fileName) throws IOException {
        String content = Node.readImageAsBase64(fileName);
        System.out.println();
        System.out.println(content.length());
        ArrayList<Node> nodes = this.getNodes(content);
        shannon2(nodes);
        Map<Character, List<Boolean>> map = new HashMap<>();
        ImageResult compressionResult = new ImageResult();
        compressionResult.setFileName(fileName);
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node node = nodes.get(i);
            map.put(node.symbol, node.bitCode);
            compressionResult.result.put(node.bitCode, node.symbol);
        }
        List<Boolean> list = new ArrayList<>();

        size = content.length();
        for (int i = 0; i < size; i++) {
            char sympol = content.charAt(i);
            list.addAll(map.get(sympol));
        }

        System.out.println(content.length());
        System.out.println(list.size() / 8);
        // Write objects to file
        if (list.size() % 8 != 0) {
            for (int i = 0; i < 8 - (list.size() % 8); i++) {
                list.add(false);
            }
        }
        Boolean[] inputSleep = new Boolean[list.size()];
        inputSleep = list.toArray(inputSleep);
        byte[] toReturn = new byte[inputSleep.length / 8];
        toReturn = Node.toByteArray(inputSleep);
        boolean[] bb = Node.toBooleanArray(toReturn);

        compressionResult.setData(Node.toByteArray(inputSleep));
        System.out.println(compressionResult.data.length + " size after compress");
        FileOutputStream f = new FileOutputStream(new File(fileName + ".shfo"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(compressionResult);
        o.close();
        f.close();

    }

    public FileResult compressionFileFolder(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[10];
            while (reader.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
                buffer = new char[10];
            }
            reader.close();
            String content = stringBuilder.toString();
            ArrayList<Node> nodes = this.getNodes(content);
            shannon2(nodes);
            Map<Character, List<Boolean>> map = new HashMap<>();
            FileResult compressionResult = new FileResult();
            compressionResult.setFileName(fileName);
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                Node node = nodes.get(i);
                map.put(node.symbol, node.bitCode);
                compressionResult.result.put(node.bitCode, node.symbol);
            }
            List<Boolean> list = new ArrayList<>();

            size = content.length();
            for (int i = 0; i < size; i++) {
                char sympol = content.charAt(i);
                list.addAll(map.get(sympol));
            }


            // Write objects to file
            if (list.size() % 8 != 0) {
                for (int i = 0; i < 8 - (list.size() % 8); i++) {
                    list.add(false);
                }
            }
            Boolean[] inputSleep = new Boolean[list.size()];
            inputSleep = list.toArray(inputSleep);
            // System.out.println(Arrays.toString(inputSleep));
            byte[] toReturn = new byte[inputSleep.length / 8];
            toReturn = Node.toByteArray(inputSleep);
            boolean[] bb = Node.toBooleanArray(toReturn);

            compressionResult.setData(Node.toByteArray(inputSleep));
            return compressionResult;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ImageResult compressionImageFolder(String fileName) {
        String content = Node.readImageAsBase64(fileName);
        System.out.println();
        System.out.println(content.length());
        ArrayList<Node> nodes = this.getNodes(content);
        shannon2(nodes);
        Map<Character, List<Boolean>> map = new HashMap<>();
        ImageResult compressionResult = new ImageResult();
        compressionResult.setFileName(fileName);
        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node node = nodes.get(i);
            map.put(node.symbol, node.bitCode);
            compressionResult.result.put(node.bitCode, node.symbol);
        }
        List<Boolean> list = new ArrayList<>();

        size = content.length();
        for (int i = 0; i < size; i++) {
            char sympol = content.charAt(i);
            list.addAll(map.get(sympol));
        }

        System.out.println(content.length());
        System.out.println(list.size() / 8);
        // Write objects to file
        if (list.size() % 8 != 0) {
            for (int i = 0; i < 8 - (list.size() % 8); i++) {
                list.add(false);
            }
        }
        Boolean[] inputSleep = new Boolean[list.size()];
        inputSleep = list.toArray(inputSleep);
        byte[] toReturn = new byte[inputSleep.length / 8];
        toReturn = Node.toByteArray(inputSleep);
        boolean[] bb = Node.toBooleanArray(toReturn);

        compressionResult.setData(Node.toByteArray(inputSleep));
        System.out.println(compressionResult.data.length + " size after compress");
        return compressionResult;

    }

    public void decompressionFile(String fileName) {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            FileResult compressionResult = (FileResult) oi.readObject();
            Map<List<Boolean>, Character> map = compressionResult.getResult();
            byte[] toReturn = compressionResult.getData();
            boolean[] bb = Node.toBooleanArray(toReturn);
            //System.out.println(Arrays.toString(bb));

           /* for (byte b: toReturn) {
                for (int i = 0; i < 9; i++) {
                   data.add(isSet(b,i));
                }

            }*/
            List<Boolean> temp = new ArrayList<>();
            String dataString = "";
            for (boolean b : bb) {
                temp.add(b);
                Character c = map.get(temp);
                if (c != null) {
                    dataString += c;
                    temp.clear();
                }
            }
            FileWriter fw = new FileWriter(compressionResult.fileName);
            fw.write(dataString);
            fw.close();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void decompressionFile(FileResult compressionResult) {
        try {
            Map<List<Boolean>, Character> map = compressionResult.getResult();
            byte[] toReturn = compressionResult.getData();
            boolean[] bb = Node.toBooleanArray(toReturn);

            List<Boolean> temp = new ArrayList<>();
            String dataString = "";
            for (boolean b : bb) {
                temp.add(b);
                Character c = map.get(temp);
                if (c != null) {
                    dataString += c;
                    temp.clear();
                }
            }
            FileWriter fw = new FileWriter(compressionResult.fileName);
            fw.write(dataString);
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void decompressionImage(String fileName) {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            Result result = (Result) oi.readObject();
            System.out.println(result.getClass());
            ImageResult compressionResult = (ImageResult) result;
            System.out.println(compressionResult.data.length);
            Map<List<Boolean>, Character> map = compressionResult.getResult();
            byte[] toReturn = compressionResult.getData();
            boolean[] bb = Node.toBooleanArray(toReturn);

            String dataString = "";

            List<Boolean> temp = new ArrayList<>();
            int min = getMin(map);
            System.out.println("min " + min);
            int size = bb.length;
            for (int i = 0; i < size; i++) {
                temp.add(bb[i]);
                Character c = map.get(temp);
                if (c != null) {
                    dataString += c;
                    temp.clear();
                    if (i + min < size) {
                        for (int j = 1; j < min; j++) {
                            temp.add(bb[i + j]);
                        }
                        i += min - 1;
                    }

                }
            }
            System.out.println(dataString.length() + " char");

            Node.writeBase64ToFileAsArrayOfBytes(dataString, compressionResult.fileName);
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void decompressionImage(ImageResult compressionResult) {
        System.out.println(compressionResult.data.length);
        Map<List<Boolean>, Character> map = compressionResult.getResult();
        byte[] toReturn = compressionResult.getData();
        boolean[] bb = Node.toBooleanArray(toReturn);

        String dataString = "";

        List<Boolean> temp = new ArrayList<>();
        int min = getMin(map);
        System.out.println("min " + min);
        int size = bb.length;
        for (int i = 0; i < size; i++) {
            temp.add(bb[i]);
            Character c = map.get(temp);
            if (c != null) {
                dataString += c;
                temp.clear();
                if (i + min < size) {
                    for (int j = 1; j < min; j++) {
                        temp.add(bb[i + j]);
                    }
                    i += min - 1;
                }

            }
        }
        System.out.println(dataString.length() + " char");
        Node.writeBase64ToFileAsArrayOfBytes(dataString, compressionResult.fileName);
    }

    public FolderResult compressFolder(String folderName) {
        try {
            List<File> filesInFolder = Files.walk(Paths.get(folderName))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            FolderResult folderResult = new FolderResult();
            folderResult.setFolderName(folderName);
            for (int i = 0; i < filesInFolder.size(); i++) {
                String extension = FilenameUtils.getExtension(filesInFolder.get(i).getAbsolutePath());
                if (filesInFolder.get(i).isDirectory()) {
                    folderResult.getResults().add(compressFolder(filesInFolder.get(i).getAbsolutePath()));
                } else if (extension.equals("jpg") || extension.equals("JPG") || extension.equals("PNG") || extension.equals("png")) {
                    folderResult.getResults().add(compressionImageFolder((filesInFolder.get(i).getAbsolutePath())));
                } else {
                    folderResult.getResults().add(compressionFileFolder((filesInFolder.get(i).getAbsolutePath())));
                }
            }
            return folderResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void decompressFolder(FolderResult folderResult) {
        try {
            File directory = new File(folderResult.getFolderName());
            boolean success = false;
            if (directory.exists()) {
                System.out.println("Directory already exists ...");
            } else {
                System.out.println("Directory not exists, creating now");
                success = directory.mkdir();
                if (success) {
                    System.out.printf("Successfully created new directory");
                } else {
                    System.out.printf("Failed to create new directory: ");
                }
            }
            for (int i = 0; i < folderResult.getResults().size(); i++) {
                if (folderResult.getResults().get(i).getClass() == FolderResult.class) {
                    System.out.println("folder");
                    decompressFolder((FolderResult) folderResult.getResults().get(i));
                } else if (folderResult.getResults().get(i).getClass() == ImageResult.class) {
                    System.out.println("image");
                    decompressionImage((ImageResult) folderResult.getResults().get(i));
                } else {
                    System.out.println("file");
                    decompressionFile((FileResult) folderResult.getResults().get(i));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void compress(String fileName) {
        try {
            File file = new File(fileName);
            String extension = FilenameUtils.getExtension(fileName);
            if (file.isDirectory()) {
                FileOutputStream f = new FileOutputStream(new File(fileName + ".shfn"));
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(compressFolder(fileName));
                o.close();
                f.close();
            } else if (extension.equals("jpg") || extension.equals("JPG") || extension.equals("PNG") || extension.equals("png")) {
                this.compressionImage(fileName);
            } else {
                this.compressionFile(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void decompress(String fileName) {
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            Result compressionResult = (Result) oi.readObject();
            if (compressionResult.getClass() == FolderResult.class) {
                decompressFolder((FolderResult) compressionResult);
            } else if (compressionResult.getClass() == ImageResult.class) {
                decompressionImage((ImageResult) compressionResult);
            } else {
                decompressionFile((FileResult) compressionResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void shannon2(List<Node> nodes) {
        int size = nodes.size();
        if (size <= 1) {
            return;
        } else if (size == 2) {
            nodes.get(0).getBitCode().add(true);
            nodes.get(1).getBitCode().add(false);
            return;
        } else {
            int h = size - 1;
            Double pack1 = 0.0, pack2 = 0.0, diff1 = 0.0, diff2 = 0.0;
            int i, d, k = 0, j;
            for (i = 0; i <= h - 1; i++)
                pack1 = pack1 + nodes.get(i).getProbability();
            pack2 = pack2 + nodes.get(h).getProbability();
            diff1 = pack1 - pack2;
            if (diff1 < 0)
                diff1 = diff1 * -1;
            j = 2;
            while (j != h - 0 + 1) {
                k = h - j;
                pack1 = 0.0;
                pack2 = 0.0;
                for (i = 0; i <= k; i++)
                    pack1 = pack1 + nodes.get(i).getProbability();
                for (i = h; i > k; i--)
                    pack2 = pack2 + nodes.get(i).getProbability();
                diff2 = pack1 - pack2;
                if (diff2 < 0)
                    diff2 = diff2 * -1;
                if (diff2 >= diff1)
                    break;
                diff1 = diff2;
                j++;
            }
            k++;
            for (i = 0; i <= k; i++) {
                nodes.get(i).getBitCode().add(true);
            }
            for (i = k + 1; i <= h; i++)
                nodes.get(i).getBitCode().add(false);


            shannon2(nodes.subList(0, k + 1));
            shannon2(nodes.subList(k + 1, size));
            return;
        }

    }

    public ArrayList<Node> getNodes(String content) {

        Map<Character, Node> nodeMap = new HashMap<>();
        int size = content.length();
        for (int i = 0; i < size; i++) {
            char sympol = content.charAt(i);
            if (nodeMap.get(sympol) == null) {
                Node node = new Node();
                node.setSymbol(sympol);
                node.addRepetition();
                nodeMap.put(sympol, node);
            } else {
                nodeMap.get(sympol).addRepetition();
            }
        }
        Node.setAll(content.length() + 0.0);
        ArrayList<Node> nodes = new ArrayList<Node>(nodeMap.values());
        Collections.sort(nodes);
        return nodes;
    }

    public int getMin(Map<List<Boolean>, Character> map) {
        int min = 1000000000;
        for (Map.Entry<List<Boolean>, Character> entry : map.entrySet()) {
            if (entry.getKey().size() < min) {
                min = entry.getKey().size();
            }
        }
        return min;
    }

   /* public void listFilesForFolder2(String dirpath) {
        try {
            List<File> filesInFolder = Files.walk(Paths.get(dirpath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (int i = 0; i < filesInFolder.size(); i++) {
                System.out.println(FilenameUtils.getExtension(filesInFolder.get(i).getAbsolutePath()));
                if (FilenameUtils.getExtension(filesInFolder.get(i).getAbsolutePath()).equals("shfn")) {
                    ShannonFanoCompression shannonFanoCompression = new ShannonFanoCompression();
                    System.out.println(filesInFolder.get(i).getAbsolutePath());
                    this.decompressionImage(filesInFolder.get(i).getAbsolutePath());
                }

            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }*/
}


