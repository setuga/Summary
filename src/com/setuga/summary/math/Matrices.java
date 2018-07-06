package com.setuga.summary.math;

public class Matrices
{

    //足し算
    public static Matrix add(Matrix a, Matrix b)
    {
        int rows = a.getRows();
        int columns = a.getColumns();

        if (rows != b.getRows() || columns != b.getColumns()) return null;

        double[][] r = new double[rows][columns];
        double[][] s = a.getMatrix();
        double[][] t = b.getMatrix();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r[i][j] = s[i][j] + t[i][j];
            }
        }
        return new Matrix(rows, columns, r);
    }

    //引き算
    public static Matrix sub(Matrix a, Matrix b)
    {
        int rows = a.getRows();
        int columns = a.getColumns();

        if (rows != b.getRows() || columns != b.getColumns()) return null;

        double[][] r = new double[rows][columns];
        double[][] s = a.getMatrix();
        double[][] t = b.getMatrix();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r[i][j] = s[i][j] - t[i][j];
            }
        }
        return new Matrix(rows, columns, r);
    }

    //掛け算
    public static Matrix multiple(double k, Matrix matrix)
    {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        double[][] r = new double[rows][columns];
        double[][] s = matrix.getMatrix();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r[i][j] = k * s[i][j];
            }
        }
        return new Matrix(rows, columns, r);
    }

    //内積
    public static Matrix dot(Matrix a, Matrix b)
    {
        int rows = a.getRows();
        int columns = a.getColumns();
        int bColumns = b.getColumns();

        if (columns != b.getRows()) return null;

        double[][] r = new double[rows][bColumns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < bColumns; j++)
            {
                double c = 0;
                for (int k = 0; k < columns; k++)
                {
                    c += a.getMatrix()[i][k] * b.getMatrix()[k][j];
                }
                r[i][j] = c;
            }
        }
        return new Matrix(rows, bColumns, r);
    }

    //単位行列
    public static Matrix identityMatrix(int n)
    {
        double[][] r = new double[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == j) r[i][i] = 1;
            }
        }
        return new Matrix(n, n, r);
    }

    //転置行列
    public static Matrix transposeMatrix(Matrix matrix)
    {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        double[][] r = new double[columns][rows];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                r[j][i] = matrix.getMatrix()[i][j];
            }
        }
        return new Matrix(columns, rows, r);
    }

    //整形
    public static String format(Matrix matrix)
    {
        StringBuilder stringBuilder = new StringBuilder();
        double[][] r = matrix.getMatrix();
        for (int i = 0; i < matrix.getRows(); i++)
        {
            for (int j = 0; j < matrix.getColumns(); j++)
            {
                stringBuilder.append(r[i][j]);
                if (j != matrix.getColumns() - 1) stringBuilder.append(' ');
            }
            if (i != matrix.getRows() - 1) stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    //整形（|あり）
    public static String formatWith1(Matrix matrix)
    {
        StringBuilder stringBuilder = new StringBuilder();
        double[][] r = matrix.getMatrix();
        for (int i = 0; i < matrix.getRows(); i++)
        {
            stringBuilder.append('|');
            stringBuilder.append(' ');
            for (int j = 0; j < matrix.getColumns(); j++)
            {
                stringBuilder.append(r[i][j]);
                if (j != matrix.getColumns() - 1) stringBuilder.append(' ');
            }
            stringBuilder.append(' ');
            stringBuilder.append('|');
            if (i != matrix.getRows() - 1) stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}
