import java.awt.image.BufferedImage;

/**
 * Pixelate converter that creates a pixelated (blocky) effect by dividing the image into blocks (3x3)
 * and setting each block's pixels to the average color of that block.
 */
public class Pixelate extends Converter {

    // Size of each pixelated block (can easily be changed)
    private static final int BLOCK_SIZE = 3;

    /**
     * Apply a pixelation effect to the input image.
     *
     * @param image the original BufferedImage
     * @return a new BufferedImage with the pixelated effect
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop through image in blocks of BLOCK_SIZE
        for (int i = 0; i < width; i = i + BLOCK_SIZE) {
            for (int j = 0; j < height; j = j + BLOCK_SIZE) {
                int blockAvgColor = avgBlock(i, j, image);
                colorBlock(i, j, newImage, blockAvgColor);
            }
        }

        return newImage;
    }

    /**
     * Calculate the average color of a BLOCK_SIZE x BLOCK_SIZE block.
     *
     * @param x starting x-coordinate of the block
     * @param y starting y-coordinate of the block
     * @param image the original BufferedImage
     * @return the averaged pixel color as an integer
     */
    private int avgBlock(int x, int y, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;

        // Calculate the sum of colors within the block
        for (int i = x; i < x + BLOCK_SIZE; i++) {
            for (int j = y; j < y + BLOCK_SIZE ; j++) {
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

        int pixelsCount = BLOCK_SIZE * BLOCK_SIZE;

        // Return the average color of the block
        ARGB newPixelARGB = new ARGB(sumAlpha / pixelsCount, sumRed / pixelsCount, sumGreen / pixelsCount, sumBlue / pixelsCount);
        return newPixelARGB.toInt();
    }

    /**
     * Fill a BLOCK_SIZE x BLOCK_SIZE block in the new image with the specified color.
     *
     * @param x starting x-coordinate of the block
     * @param y starting y-coordinate of the block
     * @param image the new BufferedImage to set pixels
     * @param pixelInt the color to set for the block
     */
    private void colorBlock(int x, int y, BufferedImage image, int pixelInt) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Set each pixel in the block to the average color
        for (int i = x; i < x + BLOCK_SIZE; i++) {
            for (int j = y; j < y + BLOCK_SIZE ; j++) {
                int col = Math.min(i, width - 1);
                int row = Math.min(j, height - 1);
                image.setRGB(col, row, pixelInt);
            }
        }
    }
}
