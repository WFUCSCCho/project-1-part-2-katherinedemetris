/******************************************************************************
 * @file: HousingPrices.java
 * @description: This program reads housing price data from a CSV file,
 *               stores it in HousingPricesData objects, sorts the data,
 *               and prints it to the console.
 * @author: Katherine Demetris
 * @date: September 26, 2024
 ******************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HousingPrices {

    /**
     * Main method to process the housing prices data.
     * @param args Command line arguments (expects a filename)
     * @throws IOException If there's an error reading the file
     */
    public static void main(String[] args) throws IOException {

        // For file input
        String fileName;
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        FileReader fileReader = null;
        BufferedReader reader = null;

        // Check command-line arguments
        if (args.length != 1) {
            System.out.println("Usage: java housing prices <filename>");
            System.exit(1);
        }

        try { // (1) download your own dataset
            fileReader = new FileReader(args[0]); //open file and read line by line
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            return; // Exit the program gracefully
        }


        // Open the input file
        inputFileNameStream = new FileInputStream(args[0]);
        inputFileNameScanner = new Scanner(inputFileNameStream);


        ArrayList<HousingPricesData> housingPriceList = new ArrayList<HousingPricesData>();

        while(inputFileNameScanner.hasNext()){
            String line = inputFileNameScanner.nextLine();
            String[] parts = line.split(","); // split the string into multiple parts

            String suburb = parts[0];

            if (parts[0].equals(suburb)) {
                HousingPricesData data = new HousingPricesData(
                        parts[0],                      // suburb
                        parts[1],                      // address
                        Integer.parseInt(parts[2]),    // rooms
                        parts[3].charAt(0),            // type (char)
                        Integer.parseInt(parts[4]),    // price
                        parts[5].charAt(0),            // method (char)
                        parts[6],                      // sellerG
                        parts[7],                      // date
                        Integer.parseInt(parts[8]),    // postcode
                        parts[9],                      // regionName
                        Integer.parseInt(parts[10]),   // propertyCount
                        Double.parseDouble(parts[11]), // distance
                        parts[12]                      // councilArea
                );
                housingPriceList.add(data);
            } else {
                System.out.println("Invalid data format in line: " + line);
            }
        }

        inputFileNameStream.close();

        // Sort the HousingPrices data
        Collections.sort(housingPriceList);

        // Print the HousingPrices data
        for (HousingPricesData data : housingPriceList) {
            System.out.println(data);
        }
    }
}
