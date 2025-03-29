import java.awt.image.BufferedImage;

public class FlipVertical extends Converter {

    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int i = 0;
        helperFlipAllColumns(i, image);
        return image;
    }

    private void helperFlipAllColumns(int column, BufferedImage image) {
        if (column == image.getWidth()) {
            return;
        }
        int row = 0;
        helperFlipColumn(column, row, image);
        column++;
        helperFlipAllColumns(column, image);
    }

    private void helperFlipColumn(int column, int row, BufferedImage image) {
        if (row == image.getHeight() / 2) {
            return;
        }
        swapColumnPixels(column, row, image);
        row++;
        helperFlipColumn(column, row, image);
    }

    private void swapColumnPixels(int column, int row, BufferedImage image) {
        int height = image.getHeight();
        int pixelIJ = image.getRGB(column, row);
        int swappedPixel = image.getRGB(column, height - row - 1);
        image.setRGB(column, row, swappedPixel);
        image.setRGB(column, height - row - 1, pixelIJ);
    }
}
