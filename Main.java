package com.company;


public class Main {
    public static void main(String[] args) {
        com.company.Matrix firstMatrix = new com.company.Matrix(3, 3);
        System.out.println("Матрица 1");
        firstMatrix.displayMatrix(); 

        com.company.Matrix secondMatrix = new com.company.Matrix(3,3);
        System.out.println("Матрица 2");
        secondMatrix.displayMatrix();

        System.out.println("Сложение матриц");
        firstMatrix.sumWithNewMatrix(secondMatrix.getMatrix()); 
        System.out.println("Умножение матрицы на число");
        firstMatrix.mullToEta(10); 
        firstMatrix.displayMatrix();

        System.out.println("Умножение матриц");
        double[][] result = com.company.Matrix.mullMatrix(firstMatrix.getMatrix(), secondMatrix.getMatrix()); // умножение матрицы на матрицу
        com.company.Matrix.displayMatrix(result);

        System.out.println("Прямоугольная матрица");
        com.company.RectangleMatrix rectMatrix = new com.company.RectangleMatrix(3,3);
        rectMatrix.displayMatrix();
        double determinant = rectMatrix.determinant(rectMatrix.getMatrix());
        System.out.printf("Определитель: %.3f", determinant);
    }
}

class Matrix {
    protected int n;
    protected int m;
    protected double [][] M;

    Matrix (int m, int n) {
        this.m = m;
        this.n = n;
        this.M = new double[m][n];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                M[i][j] = Math.random() * 100;
            }
        }
    }

    void displayMatrix() {
        for (int i = 0; i < this.m; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < this.n; j++) {
                System.out.printf(" %.3f", this.M[i][j]);
            }
            System.out.print(" |");
        }
        System.out.println();
    }

    static void displayMatrix(double[][] M) {
        for (int i = 0; i < M.length; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < M[0].length; j++) {
                System.out.printf(" %.3f", M[i][j]);
            }
            System.out.print(" |");
        }
        System.out.println();
    }

    void sumWithNewMatrix(double[][] newM) {
        if (newM[0].length != this.n || newM.length != this.m) {
            throw new IllegalArgumentException();
        }
        double[][] result = new double[this.m][this.n];
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                result[i][j] = this.M[i][j] + newM[i][j];
            }
        }
        displayMatrix(result);
    }

    void mullToEta(int eta) {
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                this.M[i][j] *= eta;
            }
        }
    }

    double[][] getMatrix() {
        return this.M;
    }

    static public double[][] mullMatrix(double[][] A, double[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException();
        }
        double[][] result = new double[B.length][A[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k < result.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

}

class RectangleMatrix extends com.company.Matrix {

    RectangleMatrix(int m, int n) {
        super(m, n);
    }

    double[][] minor(double[][] M, int i, int j) {
        double[][] minor = new double[M.length-1][M.length-1];
        for (int k = 0; k < M.length; k++) {
            for (int l = 0; k != i && l < M[k].length; l++) {
                if (l != j) {
                    minor[(k < i) ? k : k - 1][(l < j) ? l : l - 1] = M[k][l];
                }
            }
        }
        return minor;
    }

    double determinant(double[][] M) {
        if (M[0].length != M.length){
            throw new IllegalArgumentException();
        }
        if (M.length == 2) {
            return M[0][0] * M[1][1] - M[0][1] * M[1][0];
        }
        double determinant = 0;
        for (int c = 0; c < M.length; c++) {
            determinant += (Math.pow((-1), c) * M[0][c] * determinant(minor(M, 0, c)));
        }
        return determinant;
    }

}
