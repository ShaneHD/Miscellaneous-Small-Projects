package com.github.shanehd;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Gives a total amount spent on steam
 *
 * @author https://www.github.com/ShaneHD
 *         Created by Shane on 03/01/2017.
 */
public class SteamInvoiceTotalCalculator {
    public static void main(String[] args) {
        try {
            String fileDir = args[0];
            String currencyPrefix = args[1];

            File file = new File(fileDir);
            Scanner scanner = new Scanner(file);

            int invoiceAmount = 0;
            double total = 0;
            boolean isInvoice = false;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if(line.startsWith("Total")) {
                    if(!isInvoice)
                        isInvoice = true;

                    double invoice = Double.parseDouble(line.split("Total: " + currencyPrefix)[1]);
                    total+= invoice;
                    invoiceAmount++;
                }
            }

            if(!isInvoice)
                throw new Exception();

            System.out.println("Invoices: " + invoiceAmount);
            System.out.println("Total: " + currencyPrefix + new DecimalFormat("#.##").format(total));
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing arguments; \"C:/Users/me/Documents/invoice file.txt\" \"£\"");
        } catch(FileNotFoundException e) {
            System.out.println("File provided is missing.");
        } catch(Exception e) {
            System.out.println("File does not include a steam invoice.");
        }
    }

}
