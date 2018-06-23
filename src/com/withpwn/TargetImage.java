package com.withpwn;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TargetImage {
    BufferedImage Image;
    int width, height;

    TargetImage(File file) throws IOException {
        Image = ImageIO.read(file);
        width = Image.getWidth();
        height = Image.getHeight();
    }
}
