public class RP extends GenericGate{

    public RP(int position, int tail, double theta){
        super.extend(position,tail,new Complex[][]{{new Complex(1,0),new Complex(0,0)},{new Complex(0,0),new Complex(Math.cos(theta),Math.sin(theta))}});
    }

}
