public class Y extends GenericGate{

    public Y(int position, int tail){
        super.extend(position,tail,new Complex[][]{{new Complex(0,0),new Complex(0,1)},{new Complex(0,-1),new Complex(0,0)}});
    }

}