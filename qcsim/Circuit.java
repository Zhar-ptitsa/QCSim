import java.util.ArrayList;
import java.util.Arrays;

public class Circuit{

    private int size;
    private Complex[][] total;
    private ArrayList<String> gateList;

    public Circuit(int qubitCount){
        size = qubitCount;
        gateList = new ArrayList<String>();
        total = new Complex[(int)Math.pow(2,size)][(int)Math.pow(2,size)];
        for (int i = 0; i<Math.pow(2,size);i++){
            for (int j = 0; j<Math.pow(2,size);j++){
                total[i][j]=new Complex(0,0);
            }
        }
        for (int i = 0; i<Math.pow(2,size);i++){
            total[i][i]=new Complex(1,0);
        }
    }


    public void MatrixMult(Gate gate){
        Complex[][] gateMatrix = gate.matrix();
        Complex[][] temp = new Complex[(int)Math.pow(2,size)][(int)Math.pow(2,size)];
        for (int i = 0; i<Math.pow(2,size);i++){
            for (int j = 0; j<Math.pow(2,size);j++){
                temp[i][j]=new Complex(0,0);
            }
        }
        for (int i=0; i<Math.pow(2,size);i++){
            for (int j=0; j< Math.pow(2,size); j++){
                for (int k=0; k<Math.pow(2,size); k++){
                    temp[i][j]=Complex.add(temp[i][j],Complex.mult(gateMatrix[i][k],total[k][j]));
                }
            }
        }
        total = temp;
    }



    public void h(int position){
        MatrixMult(new H(position,size-position-1));
        gateList.add("   H   "+position);
        gateList.add("br");
    }

    public void rx(int position, double theta){
        MatrixMult(new RX(position,size-position-1,theta));
        if (theta>=0){
            gateList.add("RX"+String.format("%.3g",theta/Math.PI)+position);
        } else{
            gateList.add("RX"+String.format("%.3g",(2-(theta/Math.PI)))+position);
        }

        gateList.add("br");
    }

    public void ry(int position, double theta){
        MatrixMult(new RY(position,size-position-1,theta));
        if (theta>=0){
            gateList.add("RY"+String.format("%.3g",theta/Math.PI)+position);
        } else{
            gateList.add("RY"+String.format("%.3g",(2-(theta/Math.PI)))+position);
        }
        gateList.add("br");
    }

    public void rz(int position, double theta){
        MatrixMult(new RZ(position,size-position-1,theta));
        if (theta>=0){
            gateList.add("RZ"+String.format("%.3g",theta/Math.PI)+position);
        } else{
            gateList.add("RZ"+String.format("%.3g",(2-(theta/Math.PI)))+position);
        }
        gateList.add("br");
    }

    public void rp(int position, double theta){
        MatrixMult(new P(position,size-position-1,theta));
        if (theta>=0){
            gateList.add("RP"+String.format("%.3g",theta/Math.PI)+position);
        } else{
            gateList.add("RP"+String.format("%.3g",(2-(theta/Math.PI)))+position);
        }
        gateList.add("br");
    }

    public void x(int position){
        MatrixMult(new X(position,size-position-1));
        gateList.add("   X   "+position);
        gateList.add("br");
    }

    public void y(int position){
        MatrixMult(new Y(position,size-position-1));
        gateList.add("   Y   "+position);
        gateList.add("br");
    }

    public void z(int position){
        MatrixMult(new Z(position,size-position-1));
        gateList.add("   Z   "+position);
        gateList.add("br");
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

        boolean addTarget = true;
        for (int i: controls){
            if (addTarget && i>target){
                if (theta>=0){
                    gateList.add(gate+String.format("%.3g",theta/Math.PI)+target);
                } else{
                    gateList.add(gate+String.format("%.3g",(2-(theta/Math.PI)))+target);
                }
                addTarget = false;
            }
            gateList.add("   O   "+i);
        }
        if (addTarget){
            if (theta>=0){
                gateList.add(gate+String.format("%.3g",theta/Math.PI)+target);
            } else{
                gateList.add(gate+String.format("%.3g",(2-(theta/Math.PI)))+target);
            }
        }
        gateList.add("br");

        if (gate.equals("RX")){
            MatrixMult(new ControlledGate(index, new RX(0,0, theta)));
            return;
        }
        if (gate.equals("RY")){
            MatrixMult(new ControlledGate(index, new RY(0,0, theta)));
            return;
        }
        if (gate.equals("RZ")){
            MatrixMult(new ControlledGate(index, new RZ(0,0, theta)));
            return;
        }
        if (gate.equals("RP")){
            MatrixMult(new ControlledGate(index, new P(0,0, theta)));
            return;
        }
    }

    public void cg(int[] controls, int target, String gate){

        char[] index = indexHelper(controls, target);
        boolean addTarget = true;
        for (int i: controls){
            if (addTarget && i>target){
                gateList.add("   "+gate+"   "+target);
                addTarget = false;
            }
            gateList.add("   O   "+i);
        }
        if (addTarget){
            gateList.add("   "+gate+"   "+target);
        }
        gateList.add("br");


        if (gate.equals("X")){
            MatrixMult(new ControlledGate(index, new X(0,0)));
            return;
        }
        if (gate.equals("Y")){
            MatrixMult(new ControlledGate(index, new Y(0,0)));
            return;
        }
        if (gate.equals("Z")){
            MatrixMult(new ControlledGate(index, new Z(0,0)));
            return;
        }
    }

    public double[] probabilityVector(){
        double[] probabilities = new double[(int)Math.pow(2,size)];
        Complex[] vector = stateVector();
        for (int i = 0; i< vector.length; i++){
            probabilities[i] = Math.pow(vector[i].magnitude(),2);
        }
        return probabilities;
    }

    public String printProbabilityMatrix(){
        return Arrays.toString(probabilityVector());
    }

    public int measure(){
        double[] probabilities = probabilityVector();
        int[] result = new int[probabilities.length];
        double chance = Math.random();
        for (int i = 0; i<probabilities.length; i++){
            if (chance <probabilities[i]){
                result[i]=1;
                break;
            }
            chance-=probabilities[i];
        }
        for (int i = 0; i<result.length;i++){
            if (result[i]==1){
                return i;
            }
        }

        return -1;
    }

    public ArrayList<int[]> measure(int count){
        ArrayList<int[]> results = new ArrayList<int[]>();
        for (int current = 0; current<count; current++){
            int currentResult = measure();
            boolean add = true;
            for (int s = 0; s< results.size(); s++){
                if (results.get(s)[0]==currentResult){
                    results.get(s)[1]++;
                    add = false;
                    break;
                }
            }
            if (add){
                results.add(new int[]{currentResult, 1});
            }
        }
        return results;
    }


    public Complex[] stateVector(){
        Complex[] stateVector = new Complex[(int)Math.pow(2,size)];
        for (int i = 0; i<Math.pow(2,size); i++){
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

    public int size(){
    	return size;
    }

    public void draw(){
        System.out.print(" ");
        System.out.print("  |0>   ".repeat(size));
        System.out.print("\n ");
        System.out.print("   |    ".repeat(size));
        System.out.print("\n ");
        System.out.print("   |    ".repeat(size));
        System.out.print("\n ");
        int position = 0;
        for (int i = 0; i<gateList.size(); i++){
            if (gateList.get(i).equals("br")){
                for (int j = position; j<size;j++){
                    System.out.print("   |    ");
                }
                System.out.print("\n ");
                position = 0;
                System.out.print("   |    ".repeat(size));
                System.out.print("\n ");
                System.out.print("   |    ".repeat(size));
                System.out.print("\n ");
            }else{
                int currentPos = Integer.parseInt(gateList.get(i).substring(7));
                for (int j = position; j<currentPos;j++){
                    System.out.print("   |    ");
                }
                System.out.print(gateList.get(i).substring(0,7)+" ");
                position = currentPos+1;
            }
        }
        System.out.println("\n");
    }
}
