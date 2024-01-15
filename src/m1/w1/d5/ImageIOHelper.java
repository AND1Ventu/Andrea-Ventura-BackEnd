import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIOHelper {

    public static byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
}
