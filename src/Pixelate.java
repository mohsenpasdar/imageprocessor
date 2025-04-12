import java.awt.image.BufferedImage;

public class Pixelate extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width; i = i + 3) {
            for (int j = 0; j < height; j = j + 3) {
                int blockAvgColor = avgBlock(i, j, image);
                colorBlock(i, j, newImage, blockAvgColor);
            }
        }

        return newImage;
    }

    private int avgBlock(int x, int y, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        for (int i = x; i <= x + 2; i++) {
            for (int j = y; j <= y + 2 ; j++) {
                int col = Math.min(i, width - 1);
                int row = Math.min(j, height - 1);
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

    private void colorBlock(int x, int y, BufferedImage image, int pixelInt) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = x; i <= x + 2; i++) {
            for (int j = y; j <= y + 2 ; j++) {
                int col = Math.min(i, width - 1);
                int row = Math.min(j, height - 1);
                image.setRGB(col, row, pixelInt);
            }
        }
    }
}
