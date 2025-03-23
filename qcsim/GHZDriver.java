public class GHZDriver {
    public static void main(String[] args){
        Circuit circ = new Circuit(3);
        circ.h(0);
        circ.cg(new int[]{0},1,"X");
        circ.cg(new int[]{0},2,"X");
        circ.draw();
        System.out.println(circ.measure());
    }
}
