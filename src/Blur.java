import java.awt.image.BufferedImage;

public class Blur extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                int alpha = (
                          pixelToARGB(image, i - 1, j - 1).alpha
                        + pixelToARGB(image, i - 1, j).alpha
                        + pixelToARGB(image, i - 1, j + 1).alpha
                        + pixelToARGB(image, i, j - 1).alpha
                        + pixelToARGB(image, i, j).alpha
                        + pixelToARGB(image, i, j + 1).alpha
                        + pixelToARGB(image, i + 1, j - 1).alpha
                        + pixelToARGB(image, i + 1, j).alpha
                        + pixelToARGB(image, i + 1, j + 1).alpha
                ) / 9;

                int red = (
                          pixelToARGB(image, i - 1, j - 1).red
                        + pixelToARGB(image, i - 1, j).red
                        + pixelToARGB(image, i - 1, j + 1).red
                        + pixelToARGB(image, i, j - 1).red
                        + pixelToARGB(image, i, j).red
                        + pixelToARGB(image, i, j + 1).red
                        + pixelToARGB(image, i + 1, j - 1).red
                        + pixelToARGB(image, i + 1, j).red
                        + pixelToARGB(image, i + 1, j + 1).red
                ) / 9;

                int green = (
                        pixelToARGB(image, i - 1, j - 1).green
                                + pixelToARGB(image, i - 1, j).green
                                + pixelToARGB(image, i - 1, j + 1).green
                                + pixelToARGB(image, i, j - 1).green
                                + pixelToARGB(image, i, j).green
                                + pixelToARGB(image, i, j + 1).green
                                + pixelToARGB(image, i + 1, j - 1).green
                                + pixelToARGB(image, i + 1, j).green
                                + pixelToARGB(image, i + 1, j + 1).green
                ) / 9;

                int blue = (
                          pixelToARGB(image, i - 1, j - 1).blue
                        + pixelToARGB(image, i - 1, j).blue
                        + pixelToARGB(image, i - 1, j + 1).blue
                        + pixelToARGB(image, i, j - 1).blue
                        + pixelToARGB(image, i, j).blue
                        + pixelToARGB(image, i, j + 1).blue
                        + pixelToARGB(image, i + 1, j - 1).blue
                        + pixelToARGB(image, i + 1, j).blue
                        + pixelToARGB(image, i + 1, j + 1).blue
                ) / 9;

                ARGB newPixelARGB = new ARGB(alpha, red, green, blue);
                int newPixelInt = newPixelARGB.toInt();
                newImage.setRGB(i, j, newPixelInt);
            }
        }
        return newImage;
    }

    protected ARGB pixelToARGB(BufferedImage image, int x, int y) {
        int pixelInt = image.getRGB(x, y);
        return new ARGB(pixelInt);
    }
}
