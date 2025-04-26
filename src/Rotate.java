import java.awt.image.BufferedImage;

public class Rotate extends Converter {
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixelInt = image.getRGB(i, j);
                newImage.setRGB(height - j - 1, i, pixelInt);
            }
        }
        return newImage;
    }
}
