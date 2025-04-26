import java.awt.image.BufferedImage;

public class FlipHorizontal extends Converter {

    @Override
    public BufferedImage process(BufferedImage image) {
        int row = 0;
        helperFlipAllRows(row, image);
        return image;
    }

    private void helperFlipAllRows(int row, BufferedImage image) {
        if (row == image.getHeight()) {
            return;
        }
        int column = 0;
        helperFlipRow(column, row, image);
        row++;
        helperFlipAllRows(row, image);
    }

    private void helperFlipRow(int column, int row, BufferedImage image) {
        if (column == image.getWidth() / 2) {
            return;
        }
        swapRowPixels(column, row, image);
        column++;
        helperFlipRow(column, row, image);
    }

    private void swapRowPixels(int column, int row, BufferedImage image) {
        int width = image.getWidth();
        int pixelIJ = image.getRGB(column, row);
        int swappedPixel = image.getRGB(width - column - 1, row);
        image.setRGB(column, row, swappedPixel);
        image.setRGB(width - column - 1, row, pixelIJ);
    }
}
