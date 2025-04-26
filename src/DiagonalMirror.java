import java.awt.image.BufferedImage;

public class DiagonalMirror extends Converter {
    @Override
    public BufferedImage process(BufferedImage image) {
        Converter flipHorizontalConverter = new FlipHorizontal();
        BufferedImage flippedHorizontalImage = flipHorizontalConverter.process(image);
        Converter flipVerticalConverter = new FlipVertical();
        return flipVerticalConverter.process(flippedHorizontalImage);
    }
}
