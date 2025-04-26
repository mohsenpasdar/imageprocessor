import java.awt.image.BufferedImage;

/**
 * Rotate converter that rotates the image 90 degrees clockwise.
 */
public class Rotate extends Converter {

    /**
     * Rotate the input image 90 degrees clockwise.
     *
     * @param image the original BufferedImage
     * @return a new BufferedImage rotated 90 degrees clockwise
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image with swapped width and height
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        // Loop over each pixel and move it to the rotated position
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixelInt = image.getRGB(i, j);

                // Move pixel (i, j) to (height - j - 1, i) in the new image
                newImage.setRGB(height - j - 1, i, pixelInt);
            }
        }
        return newImage;
    }
}
