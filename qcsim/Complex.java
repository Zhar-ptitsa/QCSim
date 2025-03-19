public class Complex {
    private double real;
    private double imaginary;
    public Complex(double r, double i){
        real = r;
        imaginary = i;
    }

    public String toString(){
        return real + " " + imaginary + "i";
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(real,2)+Math.pow(imaginary,2));
    }

    public double[] get(){
        return new double[]{real,imaginary};
    }

    public double get(int i){
        return get()[i];
    }

    public void set(double r, double i){
        real = r;
        imaginary = i;  
    }

    public static Complex add(Complex c1, Complex c2){
        return new Complex(c1.get(0)+c2.get(0),c1.get(1)+c2.get(1));
    }

    public static Complex mult(Complex c1, Complex c2){
        return new Complex(c1.get(0)*c2.get(0)+c1.get(1)*c2.get(1),c1.get(1)*c2.get(0)+c1.get(0)*c2.get(1));
    }
}
