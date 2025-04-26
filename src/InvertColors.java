import java.awt.image.BufferedImage;

/**
 * InvertColors converter that inverts the colors of the image.
 * This is done recursively, without loops, by subtracting each RGB component from 255.
 */
public class InvertColors extends Converter {

    /**
     * Invert the colors of the input image recursively.
     *
     * @param image the original BufferedImage
     * @return the BufferedImage with colors inverted
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int row = 0;
        helperInvertAllRows(row, image);
        return image;
    }

    /**
     * Recursively invert all rows of the image.
     *
     * @param row current row index
     * @param image the BufferedImage to invert
     */
    private void helperInvertAllRows(int row, BufferedImage image) {
        if (row == image.getHeight()) {
            return; // Base case: all rows processed
        }
        int col = 0;
        helperInvertRow(col, row, image); // Start inverting this row
        helperInvertAllRows(row + 1, image); // Move to next row
    }

    /**
     * Recursively invert colors of pixels in a single row.
     *
     * @param col current column index
     * @param row current row index
     * @param image the BufferedImage to invert
     */
    private void helperInvertRow(int col, int row, BufferedImage image) {
        if (col == image.getWidth()) {
            return; // Base case: end of the row
        }
        invertPixel(col, row, image); // Invert current pixel
        helperInvertRow(col + 1, row, image); // Move to next column
    }

    /**
     * Invert color of a single pixel by subtracting each RGB component from 255.
     *
     * @param col column index of the pixel
     * @param row row index of the pixel
     * @param image the BufferedImage to invert
     */
    private void invertPixel(int col, int row, BufferedImage image) {
        int pixelInt = image.getRGB(col, row);
        ARGB pixelARGB = new ARGB(pixelInt);
        int alpha = pixelARGB.alpha;
        int red = 255 - pixelARGB.red; // Invert red channel
        int green = 255 - pixelARGB.green; // Invert green channel
        int blue = 255 - pixelARGB.blue; // Invert blue channel
        ARGB newPixelARGB = new ARGB(alpha, red, green, blue);
        int newPixelARGBInt = newPixelARGB.toInt();
        image.setRGB(col, row, newPixelARGBInt);
    }
}
