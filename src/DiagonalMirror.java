import java.awt.image.BufferedImage;

/**
 * DiagonalMirror converter that mirrors the image across its diagonal.
 * This is achieved by flipping the image horizontally and then vertically.
 */
public class DiagonalMirror extends Converter {

    /**
     * Mirror the input image diagonally by first flipping it horizontally,
     * then flipping it vertically.
     *
     * @param image the original BufferedImage
     * @return the diagonally mirrored BufferedImage
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        // First, flip the image horizontally
        Converter horizontalConverter = new FlipHorizontal();
        BufferedImage flippedHorizontalImage = horizontalConverter.process(image);

        // Then, flip the result vertically
        Converter verticalConverter = new FlipVertical();
        return verticalConverter.process(flippedHorizontalImage);
    }
}
