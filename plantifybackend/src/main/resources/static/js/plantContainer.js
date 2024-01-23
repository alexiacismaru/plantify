console.log("hello");
let data = [
  { "plant": "plant1" ,
    "humidity":42,
      "temperature":27.9,
      "brightness":63,
      "moisture":90
  },
  {
    "plant": "plant2" ,
    "humidity":45,
      "temperature":20.3,
      "brightness":54,
      "moisture":34
  },
  {
    "plant": "plant3" ,
    "humidity":40,
      "temperature":19.2,
      "brightness":49,
      "moisture":67
  },
  {
    "plant": "plant4" ,
    "humidity":70,
      "temperature":14,
      "brightness":35,
      "moisture":22
  }
]

const plantsh3 = document.getElementsByClassName(".h3plants")
const article = document.getElementsByTagName("body div main div article")

alert("this is a message");
alert(plantsh3.item(1).innerHTML);
alert(plantsh3.item(3).innerHTML);
alert(plantsh3.item(2).innerHTML);
