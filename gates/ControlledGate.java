class ControlledGate extends Gate{
    char[] index;
    GenericGate target;
    public ControlledGate(char[] input, GenericGate targetGate){
        index = input;
        target = targetGate;
        extend();
    }
    
    public void extend(){
        super.matrix(new double[][] {{1}});
        coreSegment(0);
        double[][] temp = super.matrix();
        for (int i = 0; i<temp.length;i++){
            double magnitude = 0;
            for (int j = 0; j<temp[0].length;j++){
                if (temp[i][j]!=0){
                    magnitude += Math.pow(temp[i][j], 2);
                }
            }
            if (magnitude!=1){
                temp[i][i]=Math.sqrt(1-magnitude);
            }
        }
    }
    
    private void coreSegment(int reading){
        if (reading>=index.length){
            return;
        }
        if (index[reading] == 'I'){
            super.kron(new double[][] {{1,0},{0,1}});
        }else if (index[reading]=='C'){
            super.kron(new double[][] {{0,0},{0,1}});
        } else if(index[reading]=='T'){
            super.kron(target.matrix());
        }
        coreSegment(reading+1); 
    }
    
    
    }
    