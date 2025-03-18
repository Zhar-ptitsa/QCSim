abstract class Gate{
    private double[][] matrix;
    public Gate(){}
    
    public abstract void extend();
    
    public void kron(double[][] apply){
        double[][] temp = new double[matrix.length*2][matrix[0].length*2];
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                temp[i][j]=apply[0][0]*matrix[i][j];
                temp[i][j+matrix[0].length]=apply[0][1]*matrix[i][j];
                temp[i+matrix.length][j]=apply[1][0]*matrix[i][j];         
                temp[i+matrix.length][j+matrix[0].length]=apply[1][1]*matrix[i][j];
            }
        }
        matrix=temp;
    }
    
    public static double[][] matrixSum(double[][] m1, double[][]m2){
        for(int i = 0; i<m1.length; i++){
            for(int j = 0; j<m1[0].length; j++){
                m1[i][j]+=m2[i][j];
            }
        }
        return m1;
    }
    
    public double[][] matrix(){
        return matrix;
    }
    
    public void matrix(double[][] newMatrix){
        matrix = newMatrix;
    }
    
}