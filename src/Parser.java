/*∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗
    ∗ @file: Parser.java
    ∗ @description: This program implements a parser that processes input
       commands to perform operations on a Binary Search Tree (BST) of
       integers. The parser reads commands from my dataset and fills a BST with
       objects of your custom class, operates on the BST accordingly (insert,
       search, remove, or print), and writes the results to a file.
    ∗ @author: Katherine Demetris
    ∗ @date: September 26 , 2024
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class Parser {

    //Create a BST tree of Integer type
    private BST<HousingPricesData> mybst = new BST<>();
    private ArrayList<HousingPricesData> dataList = new ArrayList<>();

    public Parser(String filename) throws FileNotFoundException { // constructor that initializes the parser with a file input
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {

        //call operate_BST method;
        Scanner sc = new Scanner(input);

        // Iterate through each line of the file
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();  // Remove redundant spaces
            if (!line.isEmpty()) {               // Ignore blank lines
                String[] command = line.split("\\s+");  // Split command by spaces
                operate_BST(command);
            }
        }
        sc.close();
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        switch (command[0].toLowerCase()) {
            case "insert" -> {
                if (command.length < 2) {
                    writeToFile("Invalid insert command", "./output.txt");
                    return;
                }
                HousingPricesData value = createDummyData(command[1]);
                mybst.insert(value);
                writeToFile("insert " + command[1], "./output.txt");
            }
            case "search" -> {
                if (command.length < 2) {
                    writeToFile("Invalid search command", "./output.txt");
                    return;
                }
                HousingPricesData value = createDummyData(command[1]);
                Node<HousingPricesData> result = mybst.find(value);
                if (result != null) {
                    writeToFile("found " + command[1], "./output.txt");
                } else {
                    writeToFile("search failed", "./output.txt");
                }
            }
            case "remove" -> {
                if (command.length < 2) {
                    writeToFile("Invalid remove command", "./output.txt");
                    return;
                }
                HousingPricesData value = createDummyData(command[1]);
                Node<HousingPricesData> result = mybst.remove(value);
                if (result != null) {
                    writeToFile("removed " + command[1], "./output.txt");
                } else {
                    writeToFile("remove failed", "./output.txt");
                }
            }
            case "print" -> {
                Iterator<HousingPricesData> it = mybst.iterator();
                StringBuilder sb = new StringBuilder();
                while (it.hasNext()) {
                    HousingPricesData data = it.next();
                    sb.append(data).append(" ");  // Uses toString() method of HousingPricesData
                }
                writeToFile(sb.toString().trim(), "./output.txt");
            }
            default -> writeToFile("Invalid Command", "./output.txt");
        }
    }

    private HousingPricesData createDummyData (String suburb){
        return new HousingPricesData(suburb, "", 0, ' ', 0, ' ', "", "", 0, "", 0, 0.0, "");
    }


    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {  // Open the file in append mode
            bw.write(content);   // Write the content
            bw.newLine(); // add a new line
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage()); // Print error stack trace if writing fails
        }
    }
}