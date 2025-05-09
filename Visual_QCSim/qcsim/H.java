public class H extends GenericGate{

    public H(int position, int tail){
        super.extend(position,tail,new Complex[][]{{new Complex(Math.sqrt(0.5),0),new Complex(Math.sqrt(0.5),0)},{new Complex(Math.sqrt(0.5),0),new Complex(-1 * Math.sqrt(0.5),0)}});
    }

}