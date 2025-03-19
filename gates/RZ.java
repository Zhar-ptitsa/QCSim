class RZ extends GenericGate{

    public RZ(int position, int tail, double theta){
        super.extend(position,tail,new Complex[][]{{new Complex(Math.cos(theta/2),-1*Math.sin(theta/2)),new Complex(0,0)},{new Complex(0,0),new Complex(Math.cos(theta/2),Math.sin(theta/2))}});
    }

}