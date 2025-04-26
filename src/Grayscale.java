import java.awt.image.BufferedImage;

public class Grayscale extends Converter {

    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixelInt = image.getRGB(i, j);
                ARGB pixelARGB = new ARGB(pixelInt);
                int alpha = pixelARGB.alpha;
                int red = pixelARGB.red;
                int green = pixelARGB.green;
                int blue = pixelARGB.blue;

                int gray = (red + green + blue) / 3;
                ARGB newPixelARGB = new ARGB(alpha, gray, gray, gray);
                int newPixelInt = newPixelARGB.toInt();

                image.setRGB(i, j, newPixelInt);
            }
        }
        return image;
    }
}
