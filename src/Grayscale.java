import java.awt.image.BufferedImage;

public class Grayscale extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage original) {
        return original;
    }
}
