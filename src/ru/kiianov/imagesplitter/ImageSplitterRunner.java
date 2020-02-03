package ru.kiianov.imagesplitter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageSplitterRunner {

    public static void main(String[] args) {
        splitImages(args[0]);
    }

    private static void splitImages(String imageFolderPath) {
        File imageFolder = new File(imageFolderPath);

        new File(imageFolderPath + "/result").mkdir();


        File[] files = imageFolder.listFiles();


        ExecutorService executor = Executors.newFixedThreadPool(8); // you can adjust threads count for more performance

        for (File file :
                files) {
            executor.execute(new ImageSplitter(file));
        }
        executor.shutdown();
    }

}
