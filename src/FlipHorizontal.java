import java.awt.image.BufferedImage;

/**
 * FlipHorizontal converter that flips the image horizontally using recursion.
 */
public class FlipHorizontal extends Converter {

    /**
     * Flip the input image horizontally.
     *
     * @param image the original BufferedImage
     * @return the flipped BufferedImage
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int row = 0;
        helperFlipAllRows(row, image);
        return image;
    }

    /**
     * Recursively flip all rows in the image.
     *
     * @param row current row index
     * @param image the BufferedImage to flip
     */
    private void helperFlipAllRows(int row, BufferedImage image) {
        if (row == image.getHeight()) {
            return; // Base case: all rows processed
        }
        int column = 0;
        helperFlipRow(column, row, image);
        row++;
        helperFlipAllRows(row, image);
    }

    /**
     * Recursively flip one row by swapping pixels horizontally.
     *
     * @param column current column index
     * @param row current row index
     * @param image the BufferedImage to flip
     */
    private void helperFlipRow(int column, int row, BufferedImage image) {
        if (column == image.getWidth() / 2) {
            return; // Base case: finished half the row
        }
        swapRowPixels(column, row, image);
        column++;
        helperFlipRow(column, row, image);
    }

    /**
     * Swap two pixels horizontally across the center of the row.
     *
     * @param column current column index
     * @param row current row index
     * @param image the BufferedImage to flip
     */
    private void swapRowPixels(int column, int row, BufferedImage image) {
        int width = image.getWidth();
        int pixelIJ = image.getRGB(column, row);
        int swappedPixel = image.getRGB(width - column - 1, row);
        image.setRGB(column, row, swappedPixel);
        image.setRGB(width - column - 1, row, pixelIJ);
    }
}
