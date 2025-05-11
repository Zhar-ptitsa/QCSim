import java.util.ArrayList;
import java.util.Arrays;

public class Circuit{

    private int size;
    private Complex[][] total;
    public ArrayList<ArrayList<String>> gateList;

    public Circuit(int qubitCount){
        size = qubitCount;
        gateList = new ArrayList<ArrayList<String>>();
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
        if (position<size){
        MatrixMult(new H(position,size-position-1));
        gateList.add(new ArrayList<String>());
        gateList.get(gateList.size()-1).add(""+ position);
        gateList.get(gateList.size()-1).add("H");
        }
    }

    public void rx(int position, double theta){
                if (position<size){

                gateList.add(new ArrayList<String>());

        MatrixMult(new RX(position,size-position-1,theta*Math.PI));
        gateList.get(gateList.size()-1).add(""+ position);
        if (theta>=0){
            gateList.get(gateList.size()-1).add("RX"+String.format("%.3g",theta));
        } else{
            gateList.get(gateList.size()-1).add("RX"+String.format("%.3g",(2-(theta))));
        }
    }

    }

    public void ry(int position, double theta){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new RY(position,size-position-1,theta*Math.PI));
        gateList.get(gateList.size()-1).add(""+ position);
        if (theta>=0){
            gateList.get(gateList.size()-1).add("RY"+String.format("%.3g",theta));
        } else{
            gateList.get(gateList.size()-1).add("RY"+String.format("%.3g",(2-(theta))));
        }
    }
    }

    public void rz(int position, double theta){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new RZ(position,size-position-1,theta*Math.PI));
        gateList.get(gateList.size()-1).add(""+ position);
        if (theta>=0){
            gateList.get(gateList.size()-1).add("RZ"+String.format("%.3g",theta));
        } else{
            gateList.get(gateList.size()-1).add("RZ"+String.format("%.3g",(2-(theta))));
        }
    }
    }

    public void rp(int position, double theta){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new P(position,size-position-1,theta*Math.PI));
        gateList.get(gateList.size()-1).add(""+ position);
        if (theta>=0){
            gateList.get(gateList.size()-1).add("RP"+String.format("%.3g",theta));
        } else{
            gateList.get(gateList.size()-1).add("RP"+String.format("%.3g",(2-(theta))));
        }
    }
    }

    public void x(int position){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new X(position,size-position-1));
        gateList.get(gateList.size()-1).add(""+ position);
        gateList.get(gateList.size()-1).add("X");
                }
    }

    public void y(int position){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new Y(position,size-position-1));
        gateList.get(gateList.size()-1).add(""+ position);
        gateList.get(gateList.size()-1).add("Y");
                }
    }

    public void z(int position){
                if (position<size){

                gateList.add(new ArrayList<String>());
        MatrixMult(new Z(position,size-position-1));
        gateList.get(gateList.size()-1).add(""+ position);
        gateList.get(gateList.size()-1).add("Z");
                }
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
                if (target<size){

        gateList.add(new ArrayList<String>());
        char[] index = indexHelper(controls, target);
            
            gateList.get(gateList.size()-1).add(""+ target);
            if (theta>=0){
                gateList.get(gateList.size()-1).add(gate+String.format("%.3g",theta));
            } else{
                gateList.get(gateList.size()-1).add(gate+String.format("%.3g",(2-(theta))));
            }

        for (int i: controls){
            if (i<size){
            gateList.get(gateList.size()-1).add(""+ i);
            gateList.get(gateList.size()-1).add("O");
            }
        }

        if (gate.equals("RX")){
            MatrixMult(new ControlledGate(index, new RX(0,0, theta*Math.PI)));
            return;
        }
        if (gate.equals("RY")){
            MatrixMult(new ControlledGate(index, new RY(0,0, theta*Math.PI)));
            return;
        }
        if (gate.equals("RZ")){
            MatrixMult(new ControlledGate(index, new RZ(0,0, theta*Math.PI)));
            return;
        }
        if (gate.equals("RP")){
            MatrixMult(new ControlledGate(index, new P(0,0, theta*Math.PI)));
            return;
        }
    }
    }

    public void cg(int[] controls, int target, String gate){
                if (target<size){
        gateList.add(new ArrayList<String>());
        char[] index = indexHelper(controls, target);

            gateList.get(gateList.size()-1).add(""+ target);
            gateList.get(gateList.size()-1).add(gate);

        for (int i: controls){   
            if (i<size){ 
            gateList.get(gateList.size()-1).add(""+ i);
            gateList.get(gateList.size()-1).add("O");
            }
        }

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
                for (int i = 0; i<results.size();i++){
                    if (currentResult<results.get(i)[0]){
                        results.add(i,new int[]{currentResult, 1});
                        add = false;
                        break;
                    }
                }
                if (add){
                results.add(new int[]{currentResult, 1});
                }
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

    
}
