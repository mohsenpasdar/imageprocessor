import java.awt.image.BufferedImage;

/**
 * FlipVertical converter that flips the image vertically using loop (top to bottom).
 */
public class FlipVertical extends Converter {

    /**
     * Flip the input image vertically.
     *
     * @param image the original BufferedImage
     * @return the flipped BufferedImage
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Loop over each column
        for (int i = 0; i < width; i++) {
            // Swap pixels only in the top half of the column
            for (int j = 0; j < height / 2; j++) {
                swapColumnPixels(i, j, image);
            }
        }
        return image;
    }

    /**
     * Swap two pixels vertically across the center of the column.
     *
     * @param column current column index
     * @param row current row index
     * @param image the BufferedImage to flip
     */
    private void swapColumnPixels(int column, int row, BufferedImage image) {
        int height = image.getHeight();
        int pixelIJ = image.getRGB(column, row);
        int swappedPixel = image.getRGB(column, height - row - 1);

        // Swap the pixel at (column, row) with the pixel at (column, height - row - 1)
        image.setRGB(column, row, swappedPixel);
        image.setRGB(column, height - row - 1, pixelIJ);
    }
}
