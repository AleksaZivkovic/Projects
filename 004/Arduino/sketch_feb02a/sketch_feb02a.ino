
String sInputString = "";
bool bStringComplete = false;

int R = 6;
int G = 5;
int B = 3;

void setup() {
  digitalWrite(LED_BUILTIN, LOW);
  
  Serial.begin(9600);
  sInputString.reserve(200);
}

void loop() {
  if (bStringComplete) {
    int nR = int(sInputString[0]) - int('0');
    nR *= 10;
    nR += int(sInputString[1]) - int('0');
    nR *= 10;
    nR += int(sInputString[2]) - int('0');
    
    int nG = int(sInputString[3]) - int('0');
    nG *= 10;
    nG += int(sInputString[4]) - int('0');
    nG *= 10;
    nG += int(sInputString[5]) - int('0');
    
    int nB = int(sInputString[6]) - int('0');
    nB *= 10;
    nB += int(sInputString[7]) - int('0');
    nB *= 10;
    nB += int(sInputString[8]) - int('0');

    analogWrite(R, 255 - nR);
    analogWrite(G, 255 - nG);
    analogWrite(B, 255 - nB);
    
    sInputString = "";
    bStringComplete = false;
  }
}

void serialEvent() {
  while (Serial.available()) {
    char chInChar = (char)Serial.read();
    
    sInputString += chInChar;
    
    if (chInChar == '+') {
      bStringComplete = true;
    }
  }
}
