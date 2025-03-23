import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public abstract class Converter {

    public void convert(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        BufferedImage originalImage = ImageIO.read(inputFile);

        RenderedImage processedImage = specificConvert(originalImage);

        File outputFile = new File(outputFileName);
        ImageIO.write(processedImage, "PNG", outputFile);
    }

    public abstract BufferedImage specificConvert(BufferedImage image);
}
