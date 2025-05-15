public class P extends GenericGate{

    public P(int position, int tail){
        super.extend(position,tail,new Complex[][]{{new Complex(1,0),new Complex(0,0)},{new Complex(0,0),new Complex(-1,0)}});
    }

}
