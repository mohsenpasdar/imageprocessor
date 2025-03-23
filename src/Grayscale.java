import java.awt.image.BufferedImage;

public class Grayscale extends Converter {
    @Override
    public BufferedImage specificConvert(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        int pixel = original.getRGB(151, 102);
        ARGB pixelARGB = new ARGB(pixel);
        int alpha = pixelARGB.alpha;
        int red = pixelARGB.red;
        int green = pixelARGB.green;
        int blue = pixelARGB.blue;
        System.out.println("Alpha: " + pixelARGB.alpha + ", Red: " + pixelARGB.red + ", Green: " + pixelARGB.green + ", Blue: " + pixelARGB.blue);
        int gray = (red + green + blue) / 3;
        pixelARGB = new ARGB(alpha, gray, gray, gray);
        pixel = pixelARGB.toInt();
        original.setRGB(151, 102, pixel);
        int newPixel = original.getRGB(151, 102);
        ARGB newColor = new ARGB(newPixel);
        System.out.println("Alpha: " + newColor.alpha + ", Red: " + newColor.red + ", Green: " + newColor.green + ", Blue: " + newColor.blue);
        return original;
    }
}
