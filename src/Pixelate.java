import java.awt.image.BufferedImage;

public class Pixelate extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width / 3 + 1, height / 3 + 1, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width / 3; i++) {
            for (int j = 0; j < height / 3; j++) {
                int avgColor = avgBlock(i, j, image);
                newImage.setRGB(i, j, avgColor);
            }
        }
        return newImage;
    }

    private int avgBlock(int x, int y, BufferedImage image) {
        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        for (int i = 3 * x; i <= 3 * x + 2; i++) {
            for (int j = 3 * y; j <= 3 * y + 2 ; j++) {
                int pixelInt = image.getRGB(i, j);
                ARGB pixelARGB = new ARGB(pixelInt);
                sumAlpha += pixelARGB.alpha;
                sumRed += pixelARGB.red;
                sumGreen += pixelARGB.green;
                sumBlue += pixelARGB.blue;
            }
        }
        ARGB newPixelARGB = new ARGB(sumAlpha / 9, sumRed / 9, sumGreen / 9, sumBlue / 9);
        return newPixelARGB.toInt();
    }
}
