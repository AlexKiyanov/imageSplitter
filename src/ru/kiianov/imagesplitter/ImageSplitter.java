package ru.kiianov.imagesplitter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageSplitter extends Thread {
    private File inputImage;


    public ImageSplitter(File image) {
        this.inputImage = image;
    }

    @Override
    public void run() {
        try {
            if (inputImage.getName().equalsIgnoreCase("result")) {
                Thread.currentThread().destroy();
            }
            String[] fileParts = {"left", "right"};
            int filePartsIterator = 0;
            BufferedImage source = ImageIO.read(inputImage);
            for (int x = 0; x < source.getWidth(); x += source.getWidth() / 2) {
                File outputFile = new File(String.format("%s/result/%s_%s.%s",
                        inputImage.getParent(),
                        inputImage.getName().split("\\.")[0],
                        fileParts[filePartsIterator],
                        inputImage.getName().split("\\.")[1]));
                filePartsIterator++;

                ImageIO.write(source.getSubimage(x, 0,
                        source.getWidth() / 2,
                        source.getHeight()),
                        inputImage.getName().split("\\.")[1],
                        outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(inputImage.getName());
        }
    }
}
