import com.setuga.summary.image.Image;
import com.setuga.summary.math.Matrices;
import com.setuga.summary.math.Matrix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Main
{

    public static void main(String[]args)
    {
        try
        {
            BufferedImage bufferedImage = Image.read("./LENA.tif");
            Image.outPut("./MIDDLE.tif", Image.toGray(bufferedImage, Image.GRAY_TYPE.MIDDLE));
            Image.outPut("./NTSC.tif", Image.toGray(bufferedImage, Image.GRAY_TYPE.NTSC));
            Image.outPut("./ITU.tif", Image.toGray(bufferedImage, Image.GRAY_TYPE.ITU));
            Image.outPut("./SIMPLE.tif", Image.toGray(bufferedImage, Image.GRAY_TYPE.SIMPLE));
            Image.outPut("./MEDIAN.tif", Image.toGray(bufferedImage, Image.GRAY_TYPE.MEDIAN));
            Image.outPut("./REVERSE.tif", Image.reverseColor(bufferedImage));
            BufferedImage[] bufferedImages = Image.read("LENA.tif", "./MIDDLE.tif", "./NTSC.tif", "./ITU.tif",
                    "./SIMPLE.tif", "./MEDIAN.tif", "./REVERSE.tif");
            Image.outPut("./Linked.tif", Image.link(bufferedImages));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Matrix matrix1 = new Matrix(2, 2);
        System.out.println(matrix1.getRows());
        System.out.println(matrix1.getColumns());
        System.out.println(Arrays.deepToString(matrix1.getMatrix()));
        System.out.println(Matrices.format(matrix1));

        double[][] r = new double[][] {
                {1, 10},
                {10, 1}
        };
        Matrix matrix2 = new Matrix(2, 2, r);
        System.out.println(matrix2.getRows());
        System.out.println(matrix2.getColumns());
        System.out.println(Arrays.deepToString(matrix2.getMatrix()));
        System.out.println(Matrices.format(matrix2));

        System.out.println(Matrices.format(Matrices.identityMatrix(5)));
        System.out.println(Matrices.formatWith1(Matrices.identityMatrix(5)));
        double[][] a = new double[][] {
                {-1, 2},
                {3, 1},
                {4, -2}
        };
        double[][] b = new double[][] {
                {4, 2, -2},
                {-3, 0, 1}
        };
        double[][] c = new double[][] {
                {2, 0},
                {0, 3}
        };
        Matrix matrixA = new Matrix(3, 2, a);
        Matrix matrixB = new Matrix(2, 3, b);
        Matrix matrixC = new Matrix(2, 2, c);

        System.out.println(Matrices.formatWith1(Objects.requireNonNull(Matrices.add(matrix2, matrixC))));
        System.out.println(Matrices.formatWith1(Objects.requireNonNull(Matrices.sub(matrix2, matrixC))));
        System.out.println(Matrices.formatWith1(Matrices.multiple(10, matrixB)));

        System.out.println(Matrices.formatWith1(Objects.requireNonNull(Matrices.dot(matrixA, matrixB))));
        System.out.println(Matrices.formatWith1(Objects.requireNonNull(Matrices.dot(matrixC, matrixC))));
        System.out.println(Matrices.formatWith1(Objects.requireNonNull(Matrices.dot(Matrices.identityMatrix(5), Matrices.identityMatrix(5)))));

        double[][] d = new double[][] {
                {1, -2, 4},
                {5, 2, -3},
                {1, -5, 3}
        };
        Matrix matrixD = new Matrix(3, 3, d);
        System.out.println(Matrices.formatWith1(Matrices.transposeMatrix(matrixD)));
        System.out.println(Matrices.formatWith1(Matrices.transposeMatrix(Matrices.transposeMatrix(matrixD))));

    }

}