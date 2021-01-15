package tankgame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

    //This class loads the image files in the "resources" folder.
public class LoadImage {

    public static BufferedImage loadImage(String file) {
        try {
            return ImageIO.read(LoadImage.class.getResource("/resources" + file));
        }
        catch (IOException e) {
            System.out.println("Failed to load the requested image file.");
        }
        return null;
    }

}