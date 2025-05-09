public class GenericGate extends Gate{

    public GenericGate(){}

    public void extend(int position, int tail, Complex[][] core){
        if (position == 0){
            super.matrix(core);
        }else{
            super.matrix(I);
            for (int i = 1; i<position; i++){
                extend();
            }
            super.kron(core);
        }
        for (int i = 0; i< tail; i++){
            extend();
        }
    }

    public void extend(){
        super.kron(I);
    }

}