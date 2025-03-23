public class X extends GenericGate{

    public X(int position, int tail){
        super.extend(position,tail,new Complex[][]{{new Complex(0,0),new Complex(1,0)},{new Complex(1,0),new Complex(0,0)}});
    }

}