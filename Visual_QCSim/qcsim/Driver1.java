public class Driver1 {
    public static void main(String[] args){
        Circuit testCirc = new Circuit(5);
        testCirc.draw();
        testCirc.h(3);
        testCirc.draw();
        System.out.println(testCirc.printStateVector());

        testCirc.cg(new int[]{1,2,4},3,"X");
        testCirc.draw();
        System.out.println(testCirc.printStateVector());
        testCirc.z(2);
        testCirc.draw();
        testCirc.rx(0,0.25*Math.PI);
        testCirc.draw();
        testCirc.cg(new int[]{1,3}, 2, "RX", 0.25*Math.PI);
        testCirc.draw();
        System.out.println(testCirc.measure());
    }
}
