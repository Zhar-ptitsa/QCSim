import java.util.ArrayList;
import java.util.Arrays;

public class QFTDriver {
    public static void main(String[] args){
        Circuit circ = new Circuit(4);
        circ.h(0);
        circ.cg(new int[]{0},1,"X");
        circ.cg(new int[]{0},2,"X");
        circ.cg(new int[]{0},2,"X");
        circ.draw();
        QFT qftAlg = new QFT();
        qftAlg.qft(circ);
        circ.draw();
        ArrayList<int[]> results = circ.measure(1024);
        for (int i = 0; i< results.size(); i++){
            System.out.println(Arrays.toString(results.get(i)));
        }
    }
}
