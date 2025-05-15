public static class KeyProcessor{



 static void attach(String textInput, Circuit core){
   if (textInput.length()<3){
     return;
   }
   if (textInput.charAt(0)=='c'){
     if (textInput.charAt(1)=='r'){
                   int position = int(textInput.substring(4,5));
       double theta = float(textInput.substring(6,11));
       int[] controls = new int[(textInput.length()-11)/2];
       for (int i = 0; i<controls.length;i++){
         controls[i]=textInput.charAt(12+2*i)-48;
       }

       if (textInput.charAt(2)=='x'){
         core.cg(controls, position, "RX", theta);
       }
       if (textInput.charAt(2)=='y'){
         core.cg(controls, position, "RY", theta);
       }
       if (textInput.charAt(2)=='z'){
         core.cg(controls, position, "RZ", theta);

       }
       if (textInput.charAt(2)=='p'){
         core.cg(controls, position, "RP", theta);
       }

     }else{
                   int position = int(textInput.substring(3,4));

       int[] controls = new int[(textInput.length()-4)/2];
       for (int i = 0; i<controls.length;i++){
         controls[i]=textInput.charAt(5+2*i)-48;
       }
       if (textInput.charAt(1)=='x'){
         core.cg(controls, position, "X");
       }
       if (textInput.charAt(1)=='y'){
         core.cg(controls, position, "Y");
       }
       if (textInput.charAt(1)=='z'){
         core.cg(controls, position, "Z");
       }
       if (textInput.charAt(1)=='p'){
         core.cg(controls, position, "P");
       }

     }
   }else{
     if (textInput.charAt(0)=='r'){
                   int position = int(textInput.substring(3,4));
              double theta = float(textInput.substring(5,10));

                     if (textInput.charAt(1)=='x'){
                       core.rx(position, theta);
                     }
                     if (textInput.charAt(1)=='y'){
                       core.ry(position, theta);
                     }
                     if (textInput.charAt(1)=='z'){
                       core.rz(position, theta);
                     }
                     if (textInput.charAt(1)=='p'){
                       core.rp(position, theta);
                     }

     }else{
                   int position = int(textInput.substring(2,3));
       if (textInput.charAt(0)=='x'){
         core.x(position);
       }
       if (textInput.charAt(0)=='y'){
         core.y(position);
       }
       if (textInput.charAt(0)=='z'){
         core.z(position);
       }
       if (textInput.charAt(0)=='h'){
         core.h(position);
       }
       if (textInput.charAt(0)=='p'){
         core.p(position);
       }
     }
   }
 }




}
