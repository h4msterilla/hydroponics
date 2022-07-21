#define POMP_UP 4
#define POMP_DOWN 5
#define WATER_CHECK 6
#define LEDS 7

void setup() {
  pinMode(POMP_UP, OUTPUT);
  pinMode(POMP_DOWN, OUTPUT);
  //tepmoraly button against waterChecker
  pinMode(WATER_CHECK, INPUT_PULLUP);
  pinMode(LEDS, OUTPUT);

  digitalWrite(POMP_UP, LOW);
  digitalWrite(POMP_DOWN, LOW);
  digitalWrite(LEDS, LOW);

  Serial.begin(9600);
  Serial.println("Start working. Water stop.");
}
/* 0 - stop
 * 1 - water up
 * 2 - water down
 * 3 - LEDs on
 * 4 - LEDs off
*/
int pompMode = '0';
int incomingByte = 0;
int waterState = 0;
int LEDs = 0;

void loop() {

  if (Serial.available()) {
    processCommand(Serial.read());
  }

  if (!(digitalRead(WATER_CHECK)) && waterState == 1) {
    processCommand('0');
    Serial.print("Maximum water level reached! ");
  }

  if (pompMode == '0') {
    digitalWrite(POMP_UP, LOW);
    digitalWrite(POMP_DOWN, LOW);
    if (waterState != 0) Serial.println("Water stop");
    waterState = 0;
  }
  if (pompMode == '1') {
    digitalWrite(POMP_UP, HIGH);
    digitalWrite(POMP_DOWN, LOW);
    if (waterState != 1) Serial.println("Water up");
    waterState = 1;
  }
  if (pompMode == '2') {
    digitalWrite(POMP_UP, LOW);
    digitalWrite(POMP_DOWN, HIGH);
    if (waterState != 2) Serial.println("Water down");
    waterState = 2;
  }
  if (pompMode == '3') {
    digitalWrite(LEDS, HIGH);
    if (LEDs != 1) Serial.println("LEDs on");
    LEDs = 1;
  }
  if (pompMode == '4') {
    digitalWrite(LEDS, LOW);
    if (LEDs != 0) Serial.println("LEDs off");
    LEDs = 0;
  }
}

void processCommand(int mode) {
  if (mode<'0' | mode> '4') {
    Serial.println("Wrong command!");
    return;
  }
  pompMode = mode;
}