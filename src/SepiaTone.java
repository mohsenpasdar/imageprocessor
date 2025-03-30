import java.awt.image.BufferedImage;

public class SepiaTone extends Converter {

    @Override
    public BufferedImage specificConvert(BufferedImage image) {
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

                int newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                newRed = Math.min(255, newRed);
                newGreen = Math.min(255, newGreen);
                newBlue = Math.min(255, newBlue);

                ARGB newPixelARGB = new ARGB(alpha, newRed, newGreen, newBlue);
                int newPixelInt = newPixelARGB.toInt();
                image.setRGB(i, j, newPixelInt);
            }
        }
        return image;
    }
}
