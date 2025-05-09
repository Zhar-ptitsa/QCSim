public class ControlledGate extends Gate{
    char[] index;
    GenericGate target;
    public ControlledGate(char[] input, GenericGate targetGate){
        index = input;
        target = targetGate;
        extend();
    }
    
    public void extend(){
        super.matrix(new Complex[][] {{new Complex(1,0)}});
        coreSegment(0);
        Complex[][] temp = super.matrix();
        for (int i = 0; i<temp.length;i++){
            boolean works = true;
            for (int j = 0; j<temp[0].length;j++){
                if (temp[i][j].magnitude()!=0){
                    works = false;
                }
            }
            if (works){
                temp[i][i]=new Complex(1,0);
            }
        }
        super.matrix(temp);
    }
    
    private void coreSegment(int reading){
        if (reading>=index.length){
            return;
        }
        if (index[reading] == 'I'){
            super.kron(I);
        }else if (index[reading]=='C'){
            super.kron(new Complex[][]{{new Complex(0,0),new Complex(0,0)},{new Complex(0,0),new Complex(1,0)}});
        } else if(index[reading]=='T'){
            super.kron(target.matrix());
        }
        coreSegment(reading+1); 
    }
    
    
    }
    