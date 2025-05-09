public class RX extends GenericGate{

    public RX(int position, int tail, double theta){
        super.extend(position,tail,new Complex[][]{{new Complex(Math.cos(theta/2),0),new Complex(0,-1*Math.sin(theta/2))},{new Complex(0,-1*Math.sin(theta/2)),new Complex(Math.cos(theta/2),0)}});
    }

}