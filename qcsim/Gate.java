public abstract class Gate{
    static Complex[][] I = new Complex[][]{{new Complex(1,0),new Complex(0,0)},{new Complex(0,0),new Complex(1,0)}};
    private Complex[][] matrix;
    public Gate(){}
    
    public abstract void extend();
    
    public void kron(Complex[][] apply){
        Complex[][] temp = new Complex[matrix.length*2][matrix[0].length*2];
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                temp[i][j]=Complex.mult(apply[0][0],matrix[i][j]);
                temp[i][j+matrix[0].length]=Complex.mult(apply[0][1],matrix[i][j]);
                temp[i+matrix.length][j]=Complex.mult(apply[1][0],matrix[i][j]);         
                temp[i+matrix.length][j+matrix[0].length]=Complex.mult(apply[1][1],matrix[i][j]);
            }
        }
        matrix=temp;
    }
    
    public static Complex[][] matrixSum(Complex[][] m1, Complex[][]m2){
        for(int i = 0; i<m1.length; i++){
            for(int j = 0; j<m1[0].length; j++){
                m1[i][j]=Complex.add(m1[i][j],m2[i][j]);
            }
        }
        return m1;
    }
    
    public Complex[][] matrix(){
        return matrix;
    }
    
    public void matrix(Complex[][] newMatrix){
        matrix = newMatrix;
    }
    
}