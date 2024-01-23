import pandas as pd
import psycopg2
import math

def movingAverageForecasting(period):
    def result(past):
        n = len(past)
        if n < period:
            return math.nan
        return pd.Series(past[(n - period):n]).mean()
    return result

def get_connection():
    try:
        return psycopg2.connect(
            database="plantifyDefault",
            user="postgres",
            password="Student_1234",
            host="127.0.0.1",
            port=5432,
        )
    except:
        return False

# GET THE CONNECTION OBJECT
conn = get_connection()

if conn:
    print("Connection to the PostgreSQL established successfully.")
else:
    print("Connection to the PostgreSQL encountered and error.")

# CREATE A CURSOR USING THE CONNECTION OBJECT
curr = conn.cursor()

# EXECUTE THE SQL QUERY
curr.execute("SELECT temperatureavg, humidityavg, moistureavg, lightavg FROM detailsarchive WHERE plantid=1;")

# FETCH ALL THE ROWS FROM THE CURSOR
queryResult=curr.fetchall()

tempavg=[]
humavg=[]
moistavg=[]
lightavg=[]

for row in queryResult:
    tempavg.append(row[0])
    humavg.append(row[1])
    moistavg.append(row[2])
    lightavg.append(row[3])

if len(tempavg)>3:
    forecast=[[],[],[],[]]
    for i in range(4):
        #temperatureavg
        forecast[i].append(movingAverageForecasting(4)(tempavg))
        tempavg.append(movingAverageForecasting(4)(tempavg))
        # humidityavg
        forecast[i].append(movingAverageForecasting(4)(humavg))
        humavg.append(movingAverageForecasting(4)(humavg))
        # moistureavg
        forecast[i].append(movingAverageForecasting(4)(moistavg))
        moistavg.append(movingAverageForecasting(4)(moistavg))
        # lightavg
        forecast[i].append(movingAverageForecasting(4)(lightavg))
        lightavg.append(movingAverageForecasting(4)(lightavg))

    print("forecast:")
    print(forecast)
else:
    print("not long enough archive")