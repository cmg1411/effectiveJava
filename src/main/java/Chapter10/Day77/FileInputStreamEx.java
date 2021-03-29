package Chapter10.Day77;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInputStreamEx {

    private static final File defaultFile = new File("defaultFilePath");

    public static void main(String[] args) {
        FileInputStreamEx fx = new FileInputStreamEx();

        FileInputStream fileInputStream = fx.openFile();
        close(fileInputStream);
    }

    public FileInputStream openFile() {
        String filePath = (new Scanner(System.in)).nextLine();
        File file = new File(filePath);

        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return openFile();
        }
    }

    public static void close(FileInputStream fileInputStream) {
        try {
            fileInputStream.close();
        } catch (IOException e) {
        }
    }
}
