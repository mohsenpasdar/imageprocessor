import java.awt.image.BufferedImage;

public class FlipHorizontal extends Converter {

    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int j = 0;
        helperAllRows(j, image);
        return image;
    }

    private void helperAllRows(int j, BufferedImage image) {
        if (j == image.getHeight()) {
            return;
        }
        int i = 0;
        helperRow(i, j, image);
        j++;
        helperAllRows(j, image);
    }

    private void helperRow(int i, int j, BufferedImage image) {
        if (i == image.getWidth() / 2) {
            return;
        }
        swap(i, j, image);
        i++;
        helperRow(i, j, image);
    }

    private void swap(int i, int j, BufferedImage image) {
        int width = image.getWidth();
        int pixelIJ = image.getRGB(i, j);
        int swappedPixel = image.getRGB(width - i - 1, j);
        image.setRGB(i, j, swappedPixel);
        image.setRGB(width - i - 1, j, pixelIJ);
    }
}
