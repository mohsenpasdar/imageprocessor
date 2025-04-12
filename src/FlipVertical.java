import java.awt.image.BufferedImage;

public class FlipVertical extends Converter {

    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height / 2; j++) {
                swapColumnPixels(i, j, image);
            }
        }
        return image;
    }

//    private void helperFlipAllColumns(int column, BufferedImage image) {
//        if (column == image.getWidth()) {
//            return;
//        }
//        int row = 0;
//        helperFlipColumn(column, row, image);
//        column++;
//        helperFlipAllColumns(column, image);
//    }
//
//    private void helperFlipColumn(int column, int row, BufferedImage image) {
//        if (row == image.getHeight() / 2) {
//            return;
//        }
//        swapColumnPixels(column, row, image);
//        row++;
//        helperFlipColumn(column, row, image);
//    }

    private void swapColumnPixels(int column, int row, BufferedImage image) {
        int height = image.getHeight();
        int pixelIJ = image.getRGB(column, row);
        int swappedPixel = image.getRGB(column, height - row - 1);
        image.setRGB(column, row, swappedPixel);
        image.setRGB(column, height - row - 1, pixelIJ);
    }
}
