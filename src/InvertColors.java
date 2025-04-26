import java.awt.image.BufferedImage;

public class InvertColors extends Converter {
    @Override
    public BufferedImage process(BufferedImage image) {
        int row = 0;
        helperInvertAllRows(row, image);
        return image;
    }

    private void helperInvertAllRows(int row, BufferedImage image) {
        if (row == image.getHeight()) {
            return;
        }
        int col = 0;
        helperInvertRow(col, row, image);
        row++;
        helperInvertAllRows(row, image);
    }

    private void helperInvertRow(int col, int row, BufferedImage image) {
        if (col == image.getWidth()) {
            return;
        }
        invertPixel(col, row, image);
        col++;
        helperInvertRow(col, row, image);
    }

    private void invertPixel(int col, int row, BufferedImage image) {
        int pixelInt = image.getRGB(col, row);
        ARGB pixelARGB = new ARGB(pixelInt);
        int alpha = pixelARGB.alpha;
        int red = 255 - pixelARGB.red;
        int green = 255 - pixelARGB.green;
        int blue = 255 - pixelARGB.blue;
        ARGB newPixelARGB = new ARGB(alpha, red, green, blue);
        int newPixelARGBInt = newPixelARGB.toInt();
        image.setRGB(col, row, newPixelARGBInt);
    }
}
