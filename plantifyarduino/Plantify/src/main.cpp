#include<ArduinoJson.h>
#include <DHT.h>
#include <FastLED.h>

#define NUM_LEDS    60
CRGB leds[NUM_LEDS];
#define LED_PIN    9

#define MoistureSensorPin A0  
#define LightSensorPin A2
#define DHTPIN 4
#define DHTTYPE DHT11 
DHT dht(DHTPIN, DHTTYPE);

const int capacity =  JSON_ARRAY_SIZE(4) + 5*JSON_OBJECT_SIZE(4);
StaticJsonBuffer<capacity> jb;
JsonArray& arrayOfMeasurments = jb.createArray();
int counter = 0;
int prevMoisture;
int moistureValue;
int ledSetting=0;
CRGB goodLight = CRGB (0, 255, 0);
CRGB badLight = CRGB (255, 180, 0);
CRGB criticalLight = CRGB (255, 0, 0);
CRGB customLight;
int i=0;
String rawString;
bool changed=false;
const int AirValue = 620;
const int WaterValue = 310;
int soilmoisturepercent=0;
int incomingByte=0;
int rawChar=-1;

//Arduino configuration
short isConfigured = 0;
int minMoisture=20;
int maxMoisture=80;
int minhumidity=10;
int minTemp=10;
int maxTemp=50;
int maxBrightness=700;
int moistureTollerence =(maxMoisture-minMoisture)/4;
int tempTollerence =(maxTemp-minTemp)/4;


void setup() {
  // Setting up the leds
  FastLED.addLeds<WS2812, LED_PIN, GRB>(leds, NUM_LEDS);
  FastLED.setBrightness(10);
  Serial.begin(9600);
  dht.begin();
  pinMode(10,OUTPUT);

  prevMoisture=2000;
  changed=true;
  ledSetting=0;

}

void loop() {
  incomingByte=0;
  rawChar=-1;
  // gettin the serial command from the wifi module
  while (Serial.available() > 0) {
    delay(50);
    rawChar=Serial.read();
    if(rawChar>47){
      incomingByte = (rawChar-48) + incomingByte*10;
    }
  }
  moistureValue = analogRead(MoistureSensorPin);
  soilmoisturepercent = map(moistureValue, AirValue, WaterValue, 0, 100);
  
  // making sure data is not out of bounds
  // a value above 100 and below 0 means a hardware issue
  if(soilmoisturepercent >= 100)
    moistureValue = 100;
  else if(soilmoisturepercent <=0)
    moistureValue = 0;
  else
    moistureValue = soilmoisturepercent;
  
  // turning on the pump when the moisture gets too low
  if(moistureTollerence>moistureValue){
    digitalWrite(10, HIGH); 
    delay(1500);
    digitalWrite(10, LOW); 
  }
  // Air humidity is measured
  float humidity = dht.readHumidity();
  // Temperature is measured
  float temperature = dht.readTemperature();
  //Getting values from photoresistor

  int AR=1024 - analogRead(LightSensorPin);
  double Vread = (AR * 0.0048828125);
  double RLDR = (10000.0 * (5 - Vread))/Vread;
  double brightness = (3800.0 / RLDR)*100;
  
  if(incomingByte==1){
    // Here it is checked whether the measurements have been carried out without errors
    // If an error is detected, a error will be displayed
    if (isnan(humidity) || isnan(temperature) || isnan(brightness) || isnan(moistureValue)) {
      Serial.println("Error while reading the sensor");
      return;
    }
  

  // Create a JsonObject
    JsonObject& measures = jb.createObject(); 
    measures["humidity"].set(humidity);
    measures["temperature"].set(temperature);
    measures["brightness"].set(brightness);
    measures["moisture"].set(moistureValue);
 
    arrayOfMeasurments.add(measures);

    arrayOfMeasurments.printTo(Serial);
    jb.clear();
    JsonArray& arrayOfMeasurments = jb.createArray();


    //Checking incoming communication from WIFI adapter
    incomingByte=0;
    rawChar=-1;
  
    while(!(Serial.available()>0))
      delay(500);    

    String command=Serial.readStringUntil(10);
    
    if(command.substring(0,1)=="1"){
      minMoisture = command.substring(1,3).toInt();
      maxMoisture = command.substring(3,5).toInt();
      minhumidity = command.substring(5,7).toInt();
      minTemp = command.substring(7,9).toInt();
      maxTemp = command.substring(9,11).toInt();
      maxBrightness = command.substring(11,15).toInt(); 
      moistureTollerence = (maxMoisture-minMoisture)/4;
      tempTollerence = (maxTemp-minTemp)/4;
    }
    else if(command.substring(0,1)=="2"&&(leds[0].r!=command.substring(1,4).toInt()||leds[0].g!=command.substring(4,7).toInt()||leds[0].b!=command.substring(7,10).toInt())){
      ledSetting=1;
      int red=command.substring(1,4).toInt();
      int green=command.substring(4,7).toInt();
      int blue=command.substring(7,10).toInt();

      if(!(red<0||red>255||green<0||green>255||blue<0||blue>255)){
      customLight=CRGB (red,blue,green);

      changed=true;
      }
    }
    else if(command.substring(0,1)=="3"){   
      ledSetting=0; 
      changed=true;            
    }
  }
  
  if(ledSetting==0){
    if(moistureValue <= minMoisture || moistureValue >= maxMoisture || humidity <= minhumidity || temperature <= minTemp/2 || temperature >= maxTemp || brightness >= maxBrightness*1.5){
      for (int i = 0; i < NUM_LEDS; i++) {
        leds[i] = criticalLight;
        
      }
    }
    else if(moistureValue <= minMoisture+moistureTollerence || moistureValue >= maxMoisture-moistureTollerence || humidity <= minTemp || temperature <= minTemp+tempTollerence || temperature >= maxTemp-tempTollerence || brightness > maxBrightness){
      for (int i = 0; i < NUM_LEDS; i++) {
        leds[i] = badLight;
      }  
    }
    else{
      for (int i = 0; i < NUM_LEDS; i++) {
        leds[i] = goodLight;
      }      
    }
  }
  else{
    for (int i = 0; i < NUM_LEDS; i++) {
        leds[i] = customLight;
      }
          
  }
  if(changed==true){
FastLED.show();
changed=false;
  }

}