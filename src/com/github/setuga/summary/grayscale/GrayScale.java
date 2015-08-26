package com.github.setuga.summary.grayscale;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GrayScale
{

    public enum TYPE
    {
        MIDDLE,
        NTSC,
        ITU,
        SIMPLE,
        MEDIAN
    }

    public static BufferedImage toGray(BufferedImage bufferedImage, TYPE type)
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int coordinates[][]  = new int[width][height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                int rgb = bufferedImage.getRGB(x, y);
                Color color = new Color(rgb);
                int gray = 0;
                switch (type)
                {
                    case MIDDLE:
                        int [] rgbArray = {color.getRed(), color.getGreen(), color.getBlue()};
                        int max = rgbArray[0], min = rgbArray[0];
                        for (int c : rgbArray)
                        {
                            if (max < c)
                            {
                                max = c;
                            }
                            if (min > c)
                            {
                                min = c;
                            }
                        }
                        gray = (max + min) / 2;
                        break;
                    case NTSC:
                        gray = (int) (color.getRed() * 0.298912 + color.getGreen() * 0.586611 + color.getBlue() * 0.114478);
                        break;
                    case ITU:
                        double dX = 2.2;
                        double doubleRed = Math.pow(color.getRed(), dX) * 0.222015;
                        double doubleGreen = Math.pow(color.getGreen(), dX) * 0.706655;
                        double doubleBlue = Math.pow(color.getBlue(), dX) * 0.071330;
                        double doubleGray = Math.pow((doubleRed + doubleGreen + doubleBlue), (1 / dX));
                        gray = (int) doubleGray;
                        break;
                    case SIMPLE:
                        gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                        break;
                    case MEDIAN:
                        gray = median(color.getRed(), color.getGreen(), color.getBlue());
                        break;
                }
                coordinates[x][y] = gray;
            }
        }
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        DataBuffer dataBuffer = out.getRaster().getDataBuffer();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                dataBuffer.setElem(y * width + x, coordinates[x][y]);
            }
        }
        return out;
    }

    public static BufferedImage readImage(String filePath) throws IOException
    {
        try (FileInputStream inputStream = new FileInputStream(filePath))
        {
            return ImageIO.read(inputStream);
        }
    }

    public static void outPutImage(String filePath, BufferedImage bufferedImage) throws IOException
    {
        ImageIO.write(bufferedImage, filePath.substring(filePath.lastIndexOf(".") + 1), new File(filePath));
    }

    private static int median(int r, int g, int b)
    {
        if (r >= g)
        {
            if (g >= b)
            {
                return g;
            }
            else if (r <= b)
            {
                return r;
            }
            else
            {
                return b;
            }
        }
        else if (r > b)
        {
            return r;
        }
        else if (g > b)
        {
            return b;
        }
        else
        {
            return g;
        }
    }

}
