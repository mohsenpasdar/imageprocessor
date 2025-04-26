import java.awt.image.BufferedImage;

/**
 * Grayscale converter that transforms an image into shades of gray
 * by averaging the red, green, and blue color components of each pixel.
 */
public class Grayscale extends Converter {

    /**
     * Convert the input image to grayscale.
     *
     * @param image the original BufferedImage
     * @return a new BufferedImage with grayscale effect
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image with the same size and type
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop over each pixel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Get the current pixel's ARGB values
                int pixelInt = image.getRGB(i, j);
                ARGB pixelARGB = new ARGB(pixelInt);

                // Calculate the average for grayscale
                int alpha = pixelARGB.alpha;
                int red = pixelARGB.red;
                int green = pixelARGB.green;
                int blue = pixelARGB.blue;
                int gray = (red + green + blue) / 3;

                // Create a new pixel with grayscale values
                ARGB newPixelARGB = new ARGB(alpha, gray, gray, gray);
                int newPixelInt = newPixelARGB.toInt();

                // Set the new pixel in the new image
                newImage.setRGB(i, j, newPixelInt);
            }
        }
        return newImage;
    }
}
