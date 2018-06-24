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

    int[] GetBG(int x, int y) {
        int[] colors = new int[3];
        int clr = Image.getRGB(x, y);

        colors[0] = (clr & 0x00ff0000) >> 16;
        colors[1] = (clr & 0x0000ff00) >> 8;
        colors[2] = clr & 0x000000ff;

        return colors;
    }

    String GetTd(int x, int y) {
        int[] colors = GetBG(x, y);
        int clr = Image.getRGB(x, y);

        colors[0] = (clr & 0x00ff0000) >> 16;
        colors[1] = (clr & 0x0000ff00) >> 8;
        colors[2] = clr & 0x000000ff;

        return String.format("            <td style=\"background:#%02x%02x%02x;\"></td>\n", colors[0], colors[1], colors[2]);
    }
}
