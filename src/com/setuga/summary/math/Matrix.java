package com.setuga.summary.math;

public class Matrix
{

    private int rows;
    private int columns;

    private double[][] matrix;

    public Matrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    public Matrix(int rows, int columns, double[][] matrix)
    {
        this.rows = rows;
        this.columns = columns;

        this.matrix = matrix;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public double[][] getMatrix()
    {
        return matrix;
    }

}
