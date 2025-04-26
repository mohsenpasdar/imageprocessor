import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public abstract class Converter {

    /**
     * Convert the input image using a specific image processing technique and save the output image.
     *
     * @param inputFileName  the name of the input image file
     * @param outputFileName the name of the output image file
     * @throws IOException if there is an error reading or writing image files
     */
    public void convert(String inputFileName, String outputFileName) throws IOException {
        // Read the input image from the file
        File inputFile = new File(inputFileName);
        BufferedImage originalImage = ImageIO.read(inputFile);

        // Apply the specific image processing
        RenderedImage processedImage = process(originalImage);

        // Write the processed image to the output file
        File outputFile = new File(outputFileName);
        ImageIO.write(processedImage, "PNG", outputFile);
    }

    /**
     * Perform the specific image processing operation.
     *
     * @param image the original BufferedImage
     * @return the processed BufferedImage
     */
    public abstract BufferedImage process(BufferedImage image);
}
