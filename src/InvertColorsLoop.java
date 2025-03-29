import java.awt.image.BufferedImage;

public class InvertColorsLoop extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixelInt = image.getRGB(i, j);
                ARGB pixelARGB = new ARGB(pixelInt);
                int alpha = pixelARGB.alpha;
                int red = 255 - pixelARGB.red;
                int green = 255 - pixelARGB.green;
                int blue = 255 - pixelARGB.blue;
                ARGB newPixelARGB = new ARGB(alpha, red, green, blue);
                int newPixelARGBInt = newPixelARGB.toInt();
                image.setRGB(i, j, newPixelARGBInt);
            }
        }
        return image;
    }
}
