import java.awt.image.BufferedImage;

/**
 * Blur converter that applies a simple blur effect
 * by averaging the color values of the surrounding pixels (3x3 block).
 */
public class Blur extends Converter {

    /**
     * Apply a simple blur to the input image.
     *
     * @param image the original BufferedImage
     * @return a new BufferedImage with blur effect
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to store the blurred result
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop over each pixel and set it to the average of its neighbors
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newImage.setRGB(i, j, averagePixelInt(image, i, j));
            }
        }
        return newImage;
    }

    /**
     * Calculate the average ARGB value of a pixel and its surrounding 8 neighbors.
     *
     * @param image the original BufferedImage
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     * @return the averaged pixel value as an int
     */
    protected int averagePixelInt(BufferedImage image, int x, int y) {
        int width = image.getWidth();
        int height = image.getHeight();

        int sumAlpha = 0;
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;

        // Loop over the 3x3 neighborhood around (x, y)
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                // Clamp coordinates to stay within the image bounds
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

        // Create a new pixel with the averaged ARGB values
        ARGB newPixelARGB = new ARGB(sumAlpha / 9, sumRed / 9, sumGreen / 9, sumBlue / 9);
        return newPixelARGB.toInt();
    }
}
