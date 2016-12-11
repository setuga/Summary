import com.setuga.summary.image.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main
{

    public static void main(String[]args)
    {
        try
        {
            BufferedImage bufferedImage = Image.read("./LENA.png");
            Image.outPut("./MIDDLE.png", Image.toGray(bufferedImage, Image.GRAY_TYPE.MIDDLE));
            Image.outPut("./NTSC.png", Image.toGray(bufferedImage, Image.GRAY_TYPE.NTSC));
            Image.outPut("./ITU.png", Image.toGray(bufferedImage, Image.GRAY_TYPE.ITU));
            Image.outPut("./SIMPLE.png", Image.toGray(bufferedImage, Image.GRAY_TYPE.SIMPLE));
            Image.outPut("./MEDIAN.png", Image.toGray(bufferedImage, Image.GRAY_TYPE.MEDIAN));
            Image.outPut("./REVERSE.png", Image.reverseColor(bufferedImage));
            BufferedImage[] bufferedImages = Image.read("LENA.png", "./MIDDLE.png", "./NTSC.png", "./ITU.png",
                    "./SIMPLE.png", "./MEDIAN.png", "./REVERSE.png");
            Image.outPut("./Linked.png", Image.link(bufferedImages));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}