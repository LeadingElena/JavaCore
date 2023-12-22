package JavaCore.Class_File;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static final String NAME_ZIP_FILE = "packedSaveFile";

    public static void main(String[] args) throws FileNotFoundException {
        File dirGame = new File("C://users/Lena/Game");

        File dirSrc = new File("C://users/Lena/Game/src");
        File dirRes = new File("C://users/Lena/Game/res");
        File dirSavegames = new File("C://users/Lena/Game/savegames");

        File dirTemp = new File("C://users/Lena/Game/temp");

        File dirSrcMain = new File("C://users/Lena/Game/src/main");
        File dirSrcTest = new File("C://users/Lena/Game/src/test");

        File dirResDrawable = new File("C://users/Lena/Game/res/drawable");
        File dirResVectors = new File("C://users/Lena/Game/res/drawable");
        File dirResIcons = new File("C://users/Lena/Game/res/icons");

        File fileMainMain = new File("C://users/Lena/Game/src/main", "Main.java");
        File fileMainUtils = new File("C://users/Lena/Game/src/main", "Utils.java");

        File fileTempTemp = new File("C://users/Lena/Game/temp", "Temp.txt");

        StringBuilder sb = new StringBuilder();

        try {
            if (dirGame.mkdir()) {
                sb.append("Директория " + dirGame.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirSrc.getName() + " не создана\n");
            }
            if (dirSrc.mkdir()) {
                sb.append("Директория " + dirSrc.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirSrc.getName() + " не создана\n");
            }
            if (dirRes.mkdir()) {
                sb.append("Директория " + dirRes.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirRes.getName() + " не создана\n");
            }
            if (dirSavegames.mkdir()) {
                sb.append("Директория " + dirSavegames.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirSavegames.getName() + " не создана\n");
            }
            if (dirTemp.mkdir()) {
                sb.append("Директория " + dirTemp.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirTemp.getName() + " не создана\n");
            }
            if (dirSrcMain.mkdir()) {
                sb.append("Директория " + dirSrcMain.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirSrcMain.getName() + " не создана\n");
            }
            if (dirSrcTest.mkdir()) {
                sb.append("Директория " + dirSrcTest.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirSrcTest.getName() + " не создана\n");
            }
            if (dirResDrawable.mkdir()) {
                sb.append("Директория " + dirResDrawable.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirResDrawable.getName() + " не создана\n");
            }
            if (dirResVectors.mkdir()) {
                sb.append("Директория " + dirResVectors.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirResVectors.getName() + " не создана\n");
            }
            if (dirResIcons.mkdir()) {
                sb.append("Директория " + dirResIcons.getName() + " создана\n");
            } else {
                sb.append("Директория " + dirResIcons.getName() + " не создана\n");
            }
            if (fileMainMain.createNewFile()) {
                sb.append("Файл " + fileMainMain.getName() + " создан\n");
            } else {
                sb.append("Файл " + fileMainMain.getName() + " не создан\n");
            }
            if (fileMainUtils.createNewFile()) {
                sb.append("Файл " + fileMainUtils.getName() + " создан\n");
            } else {
                sb.append("Файл " + fileMainUtils.getName() + " не создан\n");
            }
            if (fileTempTemp.createNewFile()) {
                sb.append("Файл " + fileTempTemp.getName() + " создан\n");
            } else {
                sb.append("Файл " + fileTempTemp.getName() + " не создан\n");
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
