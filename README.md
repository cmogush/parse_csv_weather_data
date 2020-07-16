# parse_csv_weather_data
 Java program to parse weather data from a CSV for analysis

Analyzes weather data from a CSV using CSVRecord types. CSVWeatherData.class includes the following methods:

* lowestHumidityInManyFiles - returns a CSVRecord that has the lowest humidity over all the files
* hottestInManyDays - returns a CSVRecord of the hottest temperature in multiple CSV files
* hottestHourInFile - returns a CSVRecord of the hottest temperature in a CSV file
* coldestHourInFile - returns a CSVRecord of the coldest temperature in a CSV file
* getLargestOfTwo - helper method to compare two CSVRecords, and returns the larger of the two; keeps track of the largest record
* getSmallestOfTwo - helper method to compare two CSVRecords, and returns the smaller of the two; keeps track of the largest record
* fileWithColdestTemperature - returns the filename of the file with the coldest temperature from a directory of files
* lowestHumidityInFile - returns the CSVRecord that has the lowest humidity. If there is a tie, then return the first such record that was found
* averageTemperatureInFile - returns a double that represents the average temperature in the file
* averageTemperatureWithHighHumidityInFile - returns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value

link to exercise: https://www.coursera.org/learn/java-programming/supplement/wkC85/programming-exercise-parsing-weather-data
