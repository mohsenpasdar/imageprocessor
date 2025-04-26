import java.awt.image.BufferedImage;

/**
 * SepiaTone converter that applies a sepia-tone effect to an image.
 * This gives the image a warm, brownish tone similar to old photographs.
 */
public class SepiaTone extends Converter {

    /**
     * Apply a sepia-tone filter to the input image.
     *
     * @param image the original BufferedImage
     * @return the processed BufferedImage with sepia-tone effect
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Loop through each pixel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixelInt = image.getRGB(i, j);
                ARGB pixelARGB = new ARGB(pixelInt);
                int alpha = pixelARGB.alpha;
                int red = pixelARGB.red;
                int green = pixelARGB.green;
                int blue = pixelARGB.blue;

                // Apply the Sepia tone transformation using standard coefficients
                // Sepia tone coefficients sourced from OpenCV's sepia filter implementation:
                // Reference: https://www.kaggle.com/code/ahedjneed/15-image-filters-with-deployment-opencv-streamlit
                int newRed = Math.min(255, (int)(0.393 * red + 0.769 * green + 0.189 * blue));
                int newGreen = Math.min(255, (int)(0.349 * red + 0.686 * green + 0.168 * blue));
                int newBlue = Math.min(255, (int)(0.272 * red + 0.534 * green + 0.131 * blue));

                // Create the new sepia-toned pixel
                ARGB newPixelARGB = new ARGB(alpha, newRed, newGreen, newBlue);
                int newPixelInt = newPixelARGB.toInt();

                // Set the new pixel value
                image.setRGB(i, j, newPixelInt);
            }
        }
        return image;
    }
}
