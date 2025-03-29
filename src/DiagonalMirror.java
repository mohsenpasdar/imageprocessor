import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

public class DiagonalMirror extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        Converter flipHorizontalConverter = new FlipHorizontal();
        BufferedImage flippedHorizontalImage = flipHorizontalConverter.specificConvert(image);
        Converter flipVerticalConverter = new FlipVertical();
        return flipVerticalConverter.specificConvert(flippedHorizontalImage);
    }
}
