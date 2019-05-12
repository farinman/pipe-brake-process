#define ARDUINOJSON_ENABLE_PROGMEM 0
// This #include statement was automatically added by the Particle IDE.
#include <ArduinoJson.h>

#include <Stepper.h>


// 09.05.2019
// This #include statement was automatically added by the Particle IDE.
#include <AzureIotHubClientV2.h>



#define CONNECTON_STRING "HostName=iotc-8e3b10ae-0286-4588-98b5-46579bd769e7.azure-devices.net;DeviceId=7bae8e93-e786-4454-9166-7d97a614be08;SharedAccessKey=lrjpXE6n1QHet3oxA/5CS7lv7O0xKHMs2MnsLYkK+Iw="

int count = 0;
int msgId = 0;
char telemetryBuffer[256];

// define the pin for the button
const int buttonPin = D5;

// define the pin for the led. (D7 is the PIN that is coupled with the on-board LED)
const int ledPin =  D7;
const int buzzer = D0;

//const int stepsPerRevolution = 4076;  // number of steps of the stepper motor 28BYJ-48
// define the stepper motor pins
//Stepper stepper(stepsPerRevolution, D4, D3, D2, D1);
const int waterStreamOpen = 0;
const int waterStreamClosed = 360;
Servo myservo;// create servo object using the built-in Particle Servo Library
const int servoPin = D2;  //declare variable for servo
//define stepper position
//int degrees = 0;
//define one degree in steps
const float oneDegree= 5.661111111111111;

// the state of the push button.
int defaultButtonState = 0;
int buttonState = defaultButtonState;

// defining and using a LED status
LEDStatus status2(RGB_COLOR_GREEN, LED_PATTERN_BLINK);
LEDStatus status1(RGB_COLOR_RED, LED_PATTERN_BLINK);

// Syntax for the "blink"-Function that is published in the cloud
int blinkGreen(String command);

//Syntax for opening and closing water stream
int closeWater(String command);
int openWater(String command);


// define callback signiture
void callbackCloud2Device(char *topic, byte *payload, unsigned int length);
int callbackDirectMethod(char *method, byte *payload, unsigned int length);

IotHub hub(CONNECTON_STRING, callbackCloud2Device, callbackDirectMethod);

void setup()
{
  WiFi.selectAntenna(ANT_INTERNAL);
  pinMode(LED_BUILTIN, OUTPUT);
  RGB.control(true);
  Time.zone(0);
  myservo.attach(servoPin);  //Initialize the servo attached to pin D0
  myservo.write(0);        //set servo to furthest position
  delay(500);                //delay to give the servo time to move to its position
  //myservo.detach();          //detach the servo to prevent it from jittering
      // set the pin mode for the button
    pinMode(buttonPin, INPUT_PULLDOWN);

    // set the pin mode for the LED
    pinMode(ledPin, OUTPUT);

    //set the pin mode for the buzzer
    pinMode(buzzer, OUTPUT);


    // register the  cloud variable "buttonState"
    Particle.variable("buttonState", buttonState);

    // register the cloud function
    Particle.function("blinkGreen", blinkGreen);
    Particle.function("closeWater", closeWater);
    Particle.function("openWater", openWater);


}

void loop()
{
  digitalWrite(LED_BUILTIN, HIGH);

  //loop returns true if connected to Azure IoT Hub
  if (hub.loop())
  {
    if (count++ % 50 == 0)  // slow down the publish rate to every 25 loops
    {
      Serial.printf("msg id: %d\n", msgId);
      hub.publish(telemetryToJson());
    }
  }

  delay(20); // allow for a short blink before turning led off
  digitalWrite(LED_BUILTIN, HIGH);
  delay(200);

    // read the current state of the button:
    // HIGH: button is pressed
    // LOW: button is not pressed
    buttonState = digitalRead(buttonPin);

    // switch the led on if the button is pressed
    if (buttonState == HIGH) {
        // turn the led on
        digitalWrite(ledPin, HIGH);
        digitalWrite(buzzer, HIGH);
        blinkGreen("blinkGreen");
        Particle.publish("buttonState", "1");
        // publish an event if the button was not pressed before
        if (buttonState != defaultButtonState) {
            Particle.publish("buttonStateChanged", "TRUE");
            hub.publish(buttonPressedTelemetryToJson());
        }

    } else {
        // turn the led off
        digitalWrite(ledPin, LOW);
        digitalWrite(buzzer, LOW);
        Particle.publish("buttonState", "0");
        // publish an event if the button was not released before
        if (buttonState == defaultButtonState) {
            //Particle.publish("buttonStateChanged", "FALSE");

        }
    }
}

// process cloud to device message
void callbackCloud2Device(char *topic, byte *payload, unsigned int length)
{
  char* msg = (char*)payload;
  toLowerCase(msg, length);

  if (strncmp(msg, "red", length) == 0)
  {
    RGB.color(255, 0, 0);
  }
  else if (strncmp(msg, "green", length) == 0)
  {
    RGB.color(0, 255, 0);
  }
  else if (strncmp(msg, "blue", length) == 0)
  {
    RGB.color(0, 0, 255);
  }
}

/*
    process direct message
    https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
    Return codes by convention: 200 = sucess, 400 = invalid request, 500 = issue processing request
    */
int callbackDirectMethod(char *method, byte *payload, unsigned int payloadLength)
{
  toLowerCase(method, strlen(method));

  if (strcmp(method, "lighton") == 0)
  {
    RGB.color(255, 255, 0);
  }
  else if (strcmp(method, "lightoff") == 0)
  {
    RGB.color(0, 0, 0);
  }
  else
  {
    return 400;
  }

  return 200;
}


int jsonGetValue(byte* payload, int payloadLength, char * key){
  char pl[payloadLength + 1];
  memcpy(pl, payload, payloadLength);
  pl[payloadLength] = NULL;

  //only expecting json with a single element - allow a little extra padding
  // https://arduinojson.org/v5/assistant/

  const int capacity = JSON_OBJECT_SIZE(2) + 30;
  DynamicJsonDocument jsonBuffer(capacity);
  deserializeJson(jsonBuffer ,pl);
  JsonObject root = jsonBuffer.as<JsonObject>() ;

  if (!root.isNull()) {
    int speed = root[key];
    return speed;
  } else {
    return -1;
  }
}

void toLowerCase(char* s, size_t length){
  for (size_t i = 0; i < length; i++)
  {
    s[i] = tolower(s[i]);
  }
}


// Cloud functions must return int and take one String
int blinkGreen(String command) {

        // publish an event when blinking starts
    Particle.publish("blinkingStateChanged", "started blinking");

    // blink red for 3000 ms
    RGB.color(0, 255, 0);
    delay(3000);
    RGB.color(0, 0, 0);

    // publish an event when blinking stops
    Particle.publish("blinkingStateChanged", "stopped blinking");
    Serial.printf("msg id: %d\n", msgId);
    alarmSound();
    //(tone(buzzer, 2400);
    /*for (int i = 0; i < 5; i++) {
       //alarmSound();
       delay(1000);
    }*/


    return 0;
}


int alarmSound(){
        // Sounds the buzzer at the frequency relative to the note C in Hz
    tone(buzzer,261);
    // Waits some time to turn off
    delay(200);
    //Turns the buzzer off
    noTone(buzzer);
    // Sounds the buzzer at the frequency relative to the note D in Hz
    tone(buzzer,293);
    delay(200);
    noTone(buzzer);
    // Sounds the buzzer at the frequency relative to the note E in Hz
    tone(buzzer,329);
    delay(200);
    noTone(buzzer);
    // Sounds the buzzer at the frequency relative to the note F in Hz
    tone(buzzer,349);
    delay(200);
    noTone(buzzer);
    // Sounds the buzzer at the frequency relative to the note G in Hz
    tone(buzzer,392);
    delay(200);
    noTone(buzzer);
}
// Cloud functions must return int and take one String
int closeWater(String command) {
    //int steps = 0;
    /*do{
        stepper.setSpeed(1); // 1 rpm
        stepper.step(1);
        ++steps;
    }while (steps<1019);*/
    // publish an event when water stream gets opened
    myservo.attach(servoPin);
    myservo.write(waterStreamClosed);
    delay(500);
    Particle.publish("waterStreamStateChanged", "water is closed");
    RGB.color(255, 0, 0);
    delay(3000);
    RGB.color(0, 0, 0);
    return 0;
}

// Cloud functions must return int and take one String
int openWater(String command) {
    //int steps = 0;
    /**do{

      stepper.setSpeed(1); // 1 rpm
      stepper.step(1);
      ++steps;
    }while (steps<1019);*/
    // publish an event when water stream gets opened
    myservo.attach(servoPin);
    myservo.write(waterStreamOpen);
    delay(500);
    Particle.publish("waterStreamStateChanged", "water is open");
    RGB.color(0, 0, 255);
    delay(3000);
    RGB.color(0, 0, 0);
    // publish an event when blinking stops
    Particle.publish("blinkingStateChanged", "stopped blinking");

    return 0;
}

char *buttonPressedTelemetryToJson(){
  /*  https://arduinojson.org/
    use to calculate JSON_OBJECT_SIZE https://arduinojson.org/v5/assistant/
    Have allowed for a few extra json fields that actually being used at the moment
*/
  DynamicJsonDocument jsonBuffer(JSON_OBJECT_SIZE(12) + 250);
  JsonObject root = jsonBuffer.to<JsonObject>();

  root["deviceid"] = hub.getDeviceId();
  root["buttonStateChanged"] = "TRUE";

  root["geo"] = "Sydney";
  root["utc"] = Time.format(Time.now(), TIME_FORMAT_ISO8601_FULL).c_str();
  root["mem"] = System.freeMemory();
  root["id"] = ++msgId;
  root["schema"] = 1;

  //root.printTo(telemetryBuffer, sizeof(telemetryBuffer));
  serializeJson(root , telemetryBuffer, sizeof(telemetryBuffer));

  return telemetryBuffer;
}

char *telemetryToJson()
{
  /*  https://arduinojson.org/
    use to calculate JSON_OBJECT_SIZE https://arduinojson.org/v5/assistant/
    Have allowed for a few extra json fields that actually being used at the moment
*/
  DynamicJsonDocument jsonBuffer(JSON_OBJECT_SIZE(12) + 250);
  JsonObject root = jsonBuffer.to<JsonObject>();

  root["deviceid"] = hub.getDeviceId();

  root["temp"] = 20 + random(-3, 3); // random temperature for sample
  root["humidity"] = 70 + random(-20, 20);
  root["pressure"] = 1080 + random(-100, 100);
  root["light"] = 50 + random(-50, 50);

  // switch the led on if the button is pressed
    if (buttonState == HIGH) {
        root["button"] = 1;
        digitalWrite(ledPin, HIGH);
    } else {
        root["button"] = 0;
        digitalWrite(ledPin, LOW);
    }
  root["geo"] = "Sydney";
  root["utc"] = Time.format(Time.now(), TIME_FORMAT_ISO8601_FULL).c_str();
  root["mem"] = System.freeMemory();
  root["id"] = ++msgId;
  root["schema"] = 1;

  //root.printTo(telemetryBuffer, sizeof(telemetryBuffer));
  serializeJson(root , telemetryBuffer, sizeof(telemetryBuffer));

  return telemetryBuffer;
}
