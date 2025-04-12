import java.awt.image.BufferedImage;

public class Pixelate extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 1; i < 3 * (width / 3); i = i + 3) {
            for (int j = 1; j < 3 * (height / 3); j = j + 3) {
                int blockAvgColor = avgBlock(i, j, image);
                colorBlock(i, j, newImage, blockAvgColor);
            }
        }

        return newImage;
    }

    private int avgBlock(int x, int y, BufferedImage image) {
        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
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

    private void colorBlock(int x, int y, BufferedImage image, int pixelInt) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                image.setRGB(i, j, pixelInt);
            }
        }
    }
}
