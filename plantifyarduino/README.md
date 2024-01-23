# Plantify

### Team Members:
- Mocanu Paul-Cristian
- Nohra Firas
- Etman Max
- Cismaru Alexia
- Hermans Elliot

## Responsible for this part

Firas Nohra

## Who reviewed deliverables 

Paul-Cristian Mocanu

## Installation and/or configuration instructions

The project utilizes the following components:

. Arduino UNO R3
. Electric board
. Electric wires
. Capacitive Soil Moisture Sensor v1.2
. KY-15 (Air temperature and moisture sensors)
. KY-18 (Light sensor/photoresistor module)
. LED strip
. ESP32-VROOM-32 (WIFI module)
. KY-19 Relay
. DC6V micro pump (Pump)
. 6V battery

Installation:

In order to make the connections permanent and less likely to fall apart, all connections were soldered to an electric board. All components are connected to both the 5V and ground ports of the Arduino board in parallel circuits, except for the Pump. Then, we have the data transmission pins:

Arduino connections:

Wi-Fi 33 module G16 pin -> Arduino digital 1 pin
Wi-Fi 33 module G17 pin -> Arduino digital 0 pin
LED strip S pin -> Arduino digital 9 pin
KY-15 S pin -> Arduino digital 4 pin
KY-18 S pin -> Arduino Analog 2 pin
Capacitive moisture sensor V1.2 S pin -> Arduino analog 0 pin 
KY-19 Vcc+ pin -> Arduino digital pin 10

KY-19 relay connections:

KY-19 COM (common port) -> 6V battery + (positive pole)
KY-19 ON (Normally Open port) -> Pump 5V pin

In case of wire damage or connections coming apart, wires are easily identified by their color and a label describing its function;

Power wires are red
Ground wires are black
Digital wires are green
Analog wires are yellow 
Wi-Fi 33 adapter G16 wire is white
Wi-Fi 33 adapter G17 wire is Grey

Note: The Arduino works independently and doesn't need to be connected to a PC for it to work. The only thing it needs is a power source that is connected to the WIFI adapter which will in turn power the Arduino board itself via the 5V and Ground connections.

configuration:

In order to make the System work, two different sets of code have to be uploaded to the Arduino board and the WIFI adapter VIA a wired serial connection. It can be done using the Arduino IDE or Visual Studio code but you have to include the libraries correctly for it to work with the second option. 

## Hardware (sensor) specifications

. Capacitive Soil Moisture Sensor v1.2:
Input: 5V
Needs a digital pin
This sensor reads the moisture levels found in the soil it is placed in and returns it as a % from 0 to 100.

. KY-15 (Air temperature and moisture sensors)
Input: 5V
Needs a digital pin
This sensor measures the temperature and air humidity of the environment it is places in. The temperature is returned as degrees Celsius and the humidity as a % from 0 to 100.

. KY-18 (Light sensor/photoresistor module)
input: 5V
Needs an analogue pin
This sensor returns a resistance value in Ohms that is inertly proportional to the amount of light this sensor receives and it is then converted to LUX. However, this sensor can only give reading from 0 to 1024 LUX due to hardware limitations.

. LED strip
input: 5V
Needs a digital pin
This module emits light in any color based on the RGB value sent to it in the following format; (XXX, XXX, XXX).

. ESP32-VROOM-32 (WIFI module)
input: 5V
Needs two digital pins
This module sends and receives data on a WIFI connection. The data it receives is sent to its IP directly and the data sent is also sent to a fixed preset IP address and port number. The data it sends is a Jason file holding the last readings of the other sensors mentioned above. 

. KY-19 Relay
input: 5V
Needs a digital pin
The relay is connected to 2 circuits. First, to the Arduino via it's 5V, ground and Vcc+ pins. This circuit controls the output of the relay so it acts like a switch and only turns on when 5V are sent to it. Secondly, the relay is connected to the 6V battery and Pump to control it.

. DC6V micro pump (Pump)
input: 6V
Needs power source and nothing else. Note: it should not run dry to prevent damage.
This module runs water through it when provided with power to irrigate plants.
