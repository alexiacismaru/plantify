#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>
  
const char* ssid1 = "Firasn";

const char* password1 = "1234567890";

#define RXp2 16
#define TXp2 17

#define physicalId 101
  
void setup() {
  
  Serial.begin(115200);
  Serial2.begin(9600, SERIAL_8N1, RXp2, TXp2);
  delay(4000);   //Delay needed before calling the WiFi.begin
  
  WiFi.begin(ssid1, password1); 

  
  while (WiFi.status() != WL_CONNECTED) { //Check for the connection
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
  
  Serial.println("Connected to the WiFi network");
  
}
  
void loop() {
  
 if(WiFi.status()== WL_CONNECTED){   //Check WiFi connection status
  
  Serial2.println("1");
   Serial2.flush();


  HTTPClient http;   
  WiFiClient client;
  http.begin("http://192.168.43.35:8081/plants/adddetails");  //Specify destination for HTTP request
  http.addHeader("Content-Type", "application/octet-stream");             //Specify content-type header
  delay(1000);
     
  String requestBody=Serial2.readString();
  if(requestBody!=""){
    requestBody=physicalId+ requestBody;
     
    int httpResponseCode = http.POST(requestBody);
    Serial.println(requestBody);
  
    if(httpResponseCode>0){
  
      String response = http.getString();                       //Get the response to the request
      String data=response.substring(response.indexOf("<span>"));
      // data="<span>P0L1C001,011,111</span>";
      Serial.println("data");

      Serial.println(data);

      String command = data.substring(6,7);
      Serial.println("command");

      Serial.println(command);

      if(command =="1"){
        Serial2.println(1);      
        command+=data.substring(7,21);          
      }      
      else if(command =="2"){
        Serial2.println(2);
        command+=data.substring(7,16);
        Serial.println(command);
      }
      else
        Serial2.println("0");    
          
      Serial2.flush();
      delay(50); 
      Serial2.println(command);
      Serial2.flush();
  
    }
  }
  
  http.end();  //Free resources
  
  }
  
  delay(10000);  //Send a request every 10 seconds
  
}