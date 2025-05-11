boolean opened;
boolean measurement;
int qcount;
boolean opening;
int start;
int end;
Circuit core;
boolean textInputActive;
String textInput;
boolean measuring;
ArrayList<int[]> output;

void setup(){
  windowResizable(true);
  strokeCap(SQUARE);
 size(200,200); 
 background(0);
 textSize(14);
 textAlign(CENTER,CENTER);
 text("Input Number of Qubits (1 - 9)", 100, 20);
 opened = false;
 opening = false;
 qcount = 0;
 start = 0;
 end = 12;
 textInputActive = false;
 textInput = "";
 measurement = false;
 measuring = true;
}

void draw(){
 if (!opened && !measurement){
   if (keyPressed){
     if (key<='9' && key>='1'){
       textSize(90);
       text(key,100,100);
       qcount = key-48;
       opening = true;
       opened = true;
       core = new Circuit(qcount);
     }
   }
   }else if (opening){
            delay(1000);

     background(0);
           windowResize(100*qcount+100, 1000); 
           opening = false;
   }
   
   else if (measurement){
      if (measuring){
        background(0);
               windowResize(1080,720); //<>//
               measuring = false;
      }
      background(0);
      rectMode(CENTER);
      strokeWeight(0);
      textSize(10);
      int tracker = 0;
      for (int value = 0; value<pow(2,qcount); value++){
        if (tracker>=output.size()){
          tracker = 0; 
        }
        if (value == output.get(tracker)[0]){
          try{
          rect(50+(((width-100)/pow(2,(qcount)))*(value+0.5)),600-(output.get(tracker)[1]/(4*qcount)),((width-100)/pow(2,qcount))*0.8, output.get(tracker)[1]/(2*qcount));
          }catch(NullPointerException e){
           println("error"); 
          }
          tracker++;
        }
        text(value, 50+(((width-100)/pow(2,(qcount)))*(value+0.5)),640);
      }
   } //<>// //<>// //<>// //<>// //<>// //<>// //<>// //<>// //<>//
   
   else {
     background(0);
     strokeWeight(10);
     stroke(255);
    for (int i=0; i<qcount; i++){
      line(100+100*i,60,100+100*i,1000);
    }

    
    rectMode(CENTER);
    strokeWeight(1);
    fill(200,50,50);
    for (int index = start; index<end; index++){
      if (index>=core.gateList.size()){
        break;
      }
      ArrayList<String> currentLevel = core.gateList.get(index);
      
        if(currentLevel.size()>2){
          stroke(250,200,10);
          fill(250,200,10);
          for (int controlIndex = 1; controlIndex<((currentLevel.size()/2)); controlIndex++){
              circle(100+100*int(currentLevel.get(controlIndex*2)),60+50+75*(index-start),50);
              strokeWeight(10);
              line(100+100*int(currentLevel.get(controlIndex*2)), 60+50+75*(index-start), 100+100*int(currentLevel.get(0)), 60+50+75*(index-start));
              strokeWeight(1);
          }
         stroke(255);
        }
      
        fill(200,50,50);
        square(100+100*int(currentLevel.get(0)), 60+50+75*(index-start),50);
        fill(255);
        textSize(60);
        if (currentLevel.get(1).length()>1){
          textSize(20);
          text(currentLevel.get(1).substring(0,2),100+100*int(currentLevel.get(0)),60+ 50+75*(index-start)-10);
          text(currentLevel.get(1).substring(2,7),100+100*int(currentLevel.get(0)),60+ 50+75*(index-start)+10);
        }else{
        text(currentLevel.get(1),100+100*int(currentLevel.get(0)),60+ 50+75*(index-start));
        }
    }
    strokeWeight(10);
    stroke(255);
    fill(255);
    textSize(15);
    textAlign(LEFT, CENTER);
    text("Input: ", 20, 15);
    textAlign(CENTER, CENTER);
    textSize(30);
    line(0,60,width,60);
    text(textInput, width/2, 35);
   }

 }
 
 
 void keyPressed(){
   if (opened){
      if (key == CODED) {
          if (keyCode == DOWN) { 
            start+=1;
            end+=1;
          }
          else if (keyCode == UP) { 
            start-=1;
            end-=1;
            if (start<0){
              start = 0;
              end += 1;
            }
          }
        }
      
     if(key=='m'||key=='M'){
       measurement = true; 
       
       
        output = core.measure(1024*qcount);
        
        for (int i = 0; i< output.size(); i++){
          println("");
          for (int j = 0; j< output.get(i).length;j++){
            print(output.get(i)[j]+" ");
          }
        }     
        
        
      }
      
     if(key==ENTER){
       if(textInputActive){
         textInputActive = false;
         KeyProcessor.attach(textInput, core);
         textInput = "";
       }else{
         textInputActive=true;
       }
     }else if (textInputActive){
       if (key == BACKSPACE && textInput.length()>0){
         textInput = textInput.substring(0, textInput.length()-1);
       }else{
       textInput+=key;
       }
     }
   }
 }
