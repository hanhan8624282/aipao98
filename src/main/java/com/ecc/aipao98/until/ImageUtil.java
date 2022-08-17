package com.ecc.aipao98.until;

/**
 * @author sunyc
 * @create 2022-08-16 14:49
 */



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class ImageUtil {
    public ImageUtil() {
    }

    public static OutputStream generator(int width, int height, String checkCode) {
        BufferedImage image = new BufferedImage(width, height, 5);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setColor(getRandColor(0, 255));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(getRandColor(160, 200));

        int i;
        int y;
        for (i = 0; i < 8; ++i) {
            i = random.nextInt(width);
            y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            g.drawLine(i, y, x1, y1);
        }

        g.setColor(getRandColor(160, 200));

        for (i = 0; i < 100; ++i) {
            i = random.nextInt(width);
            y = random.nextInt(height);
            g.drawLine(i, y, i, y);
        }

        Font font = new Font("Arial Black", 2, 25);
        g.setFont(font);
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

        for (i = 0; i < checkCode.length(); ++i) {
            g.drawString(String.valueOf(checkCode.charAt(i)), 22 * i + 2, 20);
        }

        g.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        return os;
    }

    private static Color getRandColor(int lower, int upper) {
        Random random = new Random();
        if (upper > 255) {
            upper = 255;
        }

        if (upper < 1) {
            upper = 1;
        }

        if (lower < 1) {
            lower = 1;
        }

        if (lower > 255) {
            lower = 255;
        }

        int r = lower + random.nextInt(upper - lower);
        int g = lower + random.nextInt(upper - lower);
        int b = lower + random.nextInt(upper - lower);
        return new Color(r, g, b);
    }
}