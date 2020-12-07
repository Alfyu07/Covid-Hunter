package gfx;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

public class ImageUtils {
    public static Image loadImage(String filePath){
        try {
            return ImageIO.read(ImageUtils.class.getResource(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
