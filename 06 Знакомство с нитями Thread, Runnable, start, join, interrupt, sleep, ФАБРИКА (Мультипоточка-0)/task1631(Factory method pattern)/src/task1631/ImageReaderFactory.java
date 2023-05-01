package task1631;

import task1631.common.*;

/**
 * \* Create by Prekrasnov Sergei
 * \
 */

public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes imageType) throws IllegalArgumentException {

        if (imageType == ImageTypes.JPG)
            return new JpgReader();

        if (imageType == ImageTypes.BMP)
            return new BmpReader();

        if (imageType == ImageTypes.PNG)
            return new PngReader();

        throw new IllegalArgumentException("Unknown image type");
    }
}