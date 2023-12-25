package JavaCore.Class_File;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class Main {
    public static final String NAME_ZIP_FILE = "packedSaveFile";

    public static void main(String[] args) throws FileNotFoundException {
        List<File> listDir = new ArrayList<>();
        List<File> listFiles = new ArrayList<>();

        File dirGame = new File("C://users/Lena/Game");
        listDir.add(dirGame);

        File dirSrc = new File("C://users/Lena/Game/src");
        listDir.add(dirSrc);
        File dirRes = new File("C://users/Lena/Game/res");
        listDir.add(dirRes);
        File dirSavegames = new File("C://users/Lena/Game/savegames");
        listDir.add(dirSavegames);

        File dirTemp = new File("C://users/Lena/Game/temp");
        listDir.add(dirTemp);

        File dirSrcMain = new File("C://users/Lena/Game/src/main");
        listDir.add(dirSrcMain);
        File dirSrcTest = new File("C://users/Lena/Game/src/test");
        listDir.add(dirSrcTest);

        File dirResDrawable = new File("C://users/Lena/Game/res/drawable");
        listDir.add(dirResDrawable);
        File dirResVectors = new File("C://users/Lena/Game/res/drawable");
        listDir.add(dirResVectors);
        File dirResIcons = new File("C://users/Lena/Game/res/icons");
        listDir.add(dirResIcons);

        File fileMainMain = new File("C://users/Lena/Game/src/main", "Main.java");
        listFiles.add(fileMainMain);
        File fileMainUtils = new File("C://users/Lena/Game/src/main", "Utils.java");
        listFiles.add(fileMainUtils);

        File fileTempTemp = new File("C://users/Lena/Game/temp", "Temp.txt");
        listFiles.add(fileTempTemp);

        StringBuilder sb = new StringBuilder();

        for (File dir : listDir) {
            if (dir.mkdir()) {
                sb.append("Директория " + dir.getName() + " создана\n");
            } else {
                sb.append("Директория " + dir.getName() + " не создана\n");
            }
        }

        try {
            for (File file : listFiles) {
                if (file.createNewFile()) {
                    sb.append("Файл " + file.getName() + " создан\n");
                } else {
                    sb.append("Файл " + file.getName() + " не создан\n");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(fileTempTemp)) {
            fileWriter.write(sb.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

// Task 2
        String zipFile = "C://users/Lena/Game/savegames/zip.zip";

        GameProgress gameProgress1 = new GameProgress(1, 2, 3, 1.5);
        GameProgress gameProgress2 = new GameProgress(4, 5, 6, 3.5);
        GameProgress gameProgress3 = new GameProgress(7, 8, 9, 6.0);

        saveGame("C://users/Lena/Game/savegames/save1.dat", gameProgress1);
        saveGame("C://users/Lena/Game/savegames/save2.dat", gameProgress2);
        saveGame("C://users/Lena/Game/savegames/save3.dat", gameProgress3);

        List<String> saveFile = new ArrayList<>();
        saveFile.add("C://users/Lena/Game/savegames/save1.dat");
        saveFile.add("C://users/Lena/Game/savegames/save2.dat");
        saveFile.add("C://users/Lena/Game/savegames/save3.dat");

        zipFiles(zipFile, saveFile);

        Path dir = Paths.get("C://users/Lena/Game/savegames/");
        clearDirectory(dir);

    }

    public static void saveGame(String saveFile, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(saveFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(gameProgress);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipFile, List<String> saveFile) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            int cnt = 1;
            for (String sf : saveFile) {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf))) {
                    zos.putNextEntry(new ZipEntry(NAME_ZIP_FILE + cnt + ".dat"));
                    cnt++;
                    byte[] buffer = new byte[bis.available()];
                    bis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void clearDirectory(Path dir) {
        File folder = new File(String.valueOf(dir));
        File fList[] = folder.listFiles();

        for (File f : fList) {
            if (f.getName().endsWith(".dat")) {
                f.delete();
            }
        }
    }
}
