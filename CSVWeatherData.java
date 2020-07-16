//Created by Christopher Mogush
import edu.duke.*;
import java.io.*;
import java.util.Scanner;
import org.apache.commons.csv.*;

public class CSVWeatherData {  
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Humidity to check: ");
        int value = reader.nextInt();
        String avgTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        System.out.println("The average temperature in this file at humidity of " + value + " is: " + avgTemp);
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("The average temperature in this file is: " + avgTemp);
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource(); //iterates over many files
        CSVRecord lowestSoFar = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //use method to get largest file
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String filename =  fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        System.out.println("Filename with coldest temp: " + filename);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
        CSVParser parser = fr.getCSVParser();
        System.out.println("All temperatures on the coldest day were:");
        for (CSVRecord currentRow : parser){
           System.out.println(currentRow.get("TemperatureF")); 
        }
    }
    
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
    
    public void testHottestInDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    
        public void testColdestInDay(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }
    
        public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource(); //iterates over many files
        CSVRecord largestSoFar = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //use method to get largest file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestHourInFile(CSVParser parser){
        //start with largestSoFar as null
        CSVRecord largestSoFar = null;
        //for each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
           largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //return largestSoFar;
        return largestSoFar;
    }
    
        public CSVRecord coldestHourInFile(CSVParser parser){
        //start with smallest as null
        CSVRecord smallestSoFar = null;
        String columnType = "TemperatureF";
        //for each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
           smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, columnType);
        }
        //return largestSoFar;
        return smallestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
                    //if largestSoFar == nothing, largestSoFar == currentRow
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }
            //else if currentRow's temp > largestSoFar, largestSoFar == currentTemp
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
            }  
            //return largestSoFar;
            return largestSoFar;
    }
    
        public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar, String columnType){
            //if smallestSoFar == nothing, smallestSoFar == currentRow
            if (currentRow.get(columnType).contains("N") == false){ 
                double currentVar = Double.parseDouble(currentRow.get(columnType));
                if (smallestSoFar == null && currentVar > -200 ){
                    smallestSoFar = currentRow;
                }
                //else if currentRow's temp > smallestSoFar, smallestSoFar == currentTemp
                else{
                    double smallestVar = Double.parseDouble(smallestSoFar.get(columnType));
                    if(currentVar < smallestVar && currentVar > -200){
                        smallestSoFar = currentRow;
                    }
                }  
            }
            //return smallestSoFar;
            return smallestSoFar;
    }

    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource(); //iterates over many files
        //start with smallestSoFar as null
        String filename = ""; //filename
        CSVRecord smallestSoFar = null;
        //for each row (currentRow) in the CSV File
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //use method to get largest file
            String columnType = "TemperatureF";
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            CSVRecord oldSmallestSoFar = smallestSoFar; //hold old smallestSoFar value
            //update smallestSoFarValue
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, columnType);
            if(oldSmallestSoFar != smallestSoFar){ //if smallestSoFar chenged, update the filename
                 filename = f.getPath();
            }
        }
         //return String of filename from selected files that has the coldest temp
        return filename;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        //start with smallest as null
        CSVRecord smallestSoFar = null;
        String columnType = "Humidity";
        //for each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
           smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, columnType);
        }
        //return smallest CSVRecord;
        return smallestSoFar;    
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        //returns a double that represents the average temp in the file
        double totalTemp = 0;
        double countRecords = 0;
        //for each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
           totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
           countRecords += 1;
        }
        //return avgTemp;
        double avgTemp = totalTemp / countRecords;
        return avgTemp;    
    }
    
    public String averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0;
        double countRecords = 0;
        //for each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
           if (currentRow.get("Humidity").contains("N") == false && currentRow.get("Humidity").contains("N") == false){ 
               double humidity = Double.parseDouble(currentRow.get("Humidity"));
               if(humidity >= value){
                   totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
                   countRecords += 1;
               }
           }
        }
        //return avgTemp;
        if(countRecords == 0){
            return "No temperatures with that humidity";
        }
        //returns a double that represent avgTemp when humidity >= value
        double avgTemp = totalTemp / countRecords;
        return Double.toString(avgTemp);    
    }
}
//int value = Integer.parseInt(yearStr); //converts the String yearStr to int value
//Double.parseDouble for real numbers