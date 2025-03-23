public class Z extends GenericGate{

    public Z(int position, int tail){
        super.extend(position,tail,new Complex[][]{{new Complex(1,0),new Complex(0,0)},{new Complex(0,0),new Complex(-1,0)}});
    }

}