import java.util.ArrayList;
import java.util.Arrays;

class Circuit{

    private int size;
    private Complex[][] total;
    private ArrayList<String> gateList;

    public Circuit(int qubitCount){
        size = qubitCount;
        total = new Complex[(int)Math.pow(size,2)][(int)Math.pow(size,2)];
        for (int i = 0; i<Math.pow(size,2);i++){
            total[i][i]=new Complex(1,0);
        }
    }

    public void MatrixMult(Gate gate){
        Complex[][] gateMatrix = gate.matrix();
        Complex[][] temp = new Complex[size][size];
        for (int i=0; i<size;i++){
            for (int j=0; j< size; j++){
                for (int k=0; k<size; k++){
                    Complex.add(temp[i][j],Complex.mult(gateMatrix[i][k],total[k][j]));
                }
            }
        }
        total = temp;
    }



    public void h(int position){
        MatrixMult(new H(position,size-position-1));
        gateList.add("H");
    }

    public void rx(int position, double theta){
        MatrixMult(new RX(position,size-position-1,theta));
        gateList.add("RX"+String.format("%.4g%n",theta));
    }

    public void ry(int position, double theta){
        MatrixMult(new RY(position,size-position-1,theta));
        gateList.add("RY"+String.format("%.4g%n",theta));
    }

    public void rz(int position, double theta){
        MatrixMult(new RZ(position,size-position-1,theta));
        gateList.add("RZ"+String.format("%.4g%n",theta));
    }

    public void x(int position){
        MatrixMult(new RX(position,size-position-1,Math.PI/2));
        gateList.add("X");
    }

    public void y(int position){
        MatrixMult(new RY(position,size-position-1,Math.PI/2));
        gateList.add("Y");
    }

    public void z(int position){
        MatrixMult(new RZ(position,size-position-1,Math.PI/2));
        gateList.add("Z");
    }


    public char[] indexHelper(int[] controls, int target){
        char[] index = new char[size];
        for (int i = 0; i<size;i++){
            if (i==target){
                index[i]='T';
            }else{
                for (int pos : controls){
                    if (i==pos){
                        index[i]='C';
                        break;
                    }
                }
                if (index[i]!='C'){
                    index[i]='I';
                }
            }
        }
        return index;
    }


    public void cg(int[] controls, int target, String gate, double theta){

        char[] index = indexHelper(controls, target);
        gateList.add(index + gate + String.format("%.4g%n",theta));

        if (gate == "RX"){
            MatrixMult(new ControlledGate(index, new RX(0,0, theta)));
            return;
        }
        if (gate == "RY"){
            MatrixMult(new ControlledGate(index, new RY(0,0, theta)));
            return;
        }
        if (gate == "RZ"){
            MatrixMult(new ControlledGate(index, new RZ(0,0, theta)));
            return;
        }
    }

    public void cg(int[] controls, int target, String gate){

        char[] index = indexHelper(controls, target);
        gateList.add(index + gate);

        if (gate == "RX"){
            MatrixMult(new ControlledGate(index, new RX(0,0, Math.PI/2)));
            return;
        }
        if (gate == "RY"){
            MatrixMult(new ControlledGate(index, new RY(0,0, Math.PI/2)));
            return;
        }
        if (gate == "RZ"){
            MatrixMult(new ControlledGate(index, new RZ(0,0, Math.PI/2)));
            return;
        }
    }




    public Complex[] stateVector(){
        Complex[] stateVector = new Complex[size];
        for (int i = 0; i<size; i++){
            stateVector[i]=total[i][0];
        }
        return stateVector;
    }

    public String printStateVector(){
        return Arrays.toString(stateVector());
    }

    public Complex[][] getMatrix(){
        return total;
    }

    public String printMatrix(){
        String result = "";
        for (Complex[] row : total){
            result+=Arrays.toString(row)+"\n";
        }
        return result;
    }

    //draw and measure remain

}