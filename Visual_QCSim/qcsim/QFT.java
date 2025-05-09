import java.util.ArrayList;
import java.util.Arrays;

public class QFT {
    public void qft(Circuit circ){
    	for (int i = 0; i<circ.size()/2;i++){
    		swap(i,circ.size()-1-i,circ);
    	}
      for (int i = 0; i<circ.size();i++){
      	circ.h(i);
      	for (int j = i+1; j<circ.size();j++){
      		circ.cg(new int[]{j},i,"RP",Math.PI/Math.pow(2,(j-i)));
      	}
      }
    }

    public void swap(int a, int b, Circuit circ){
    	circ.cg(new int[]{a},b,"X");
    	circ.cg(new int[]{b},a,"X");
    	circ.cg(new int[]{a},b,"X");
    }
}
