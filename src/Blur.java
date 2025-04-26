import java.awt.image.BufferedImage;

public class Blur extends Converter {
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newImage.setRGB(i, j, averagePixelInt(image, i, j));
            }
        }
        return newImage;
    }

    protected int averagePixelInt(BufferedImage image, int x, int y) {
        int width = image.getWidth();
        int height = image.getHeight();

        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                int col = Math.min(Math.max(i, 0), width - 1);
                int row = Math.min(Math.max(j, 0), height - 1);
                int pixelInt = image.getRGB(col, row);
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
