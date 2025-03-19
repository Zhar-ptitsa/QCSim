public class RY extends GenericGate{

    public RY(int position, int tail, double theta){
        super.extend(position,tail,new Complex[][]{{new Complex(Math.cos(theta/2),0),new Complex(Math.sin(theta/2),0)},{new Complex(-1*Math.sin(theta/2),0),new Complex(Math.cos(theta/2),0)}});
    }

}