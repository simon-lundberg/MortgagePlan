import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MortgagePlan {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        try(final FileReader f=new FileReader("src/main/resources/prospects.txt");
            BufferedReader bf = new BufferedReader(f))
        {
            while (bf.ready()){
                list.add(bf.readLine());
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //remove first line (contains no customer information)
        list.remove(0);

        int prospectIndex=1;
        for (String line : list){

            //eliminate potential quotation marks
            line=line.replaceAll("\"", "");

            //separate the line into its elements
            String[] temp = line.split(",");

            //disregard empty lines (non-empty lines will have been split and have array length > 1)
            if (temp.length > 1){

                //account for potential comma in between first and last name
                if (temp.length > 4){
                    temp[1] = temp[0] + " " + temp[1];
                }

                String name = temp[temp.length-4];
                double loan = Double.parseDouble(temp[temp.length-3]);
                double interest = Double.parseDouble(temp[temp.length-2]);
                int years = Integer.parseInt(temp[temp.length-1]);

                double mPayment = monthlyPayment(loan,interest,years);

                System.out.printf("Prospect %d: %s wants to borrow %.2f€ for a period of %d years and pay %.2f€ each month%n",prospectIndex++, name, loan, years, mPayment);
            }
        }
    }

    /**
     * method that calculates the monthly payment of a loan
     * @param loan the initial loan
     * @param interest the yearly interest rate
     * @param years number of years
     * @return the fixed monthly payment
     */
    public static double monthlyPayment(double loan, double interest, int years){
        //convert terms to monthly rather than yearly
        double b = interest/1200;
        int p = years*12;

        //compute (1+b)^p without the use of the Math.pow function (math library not allowed)
        double base = b+1;
        double result = 1;
        for (int i = p; i != 0; i--){
            result=result*base;
        }

        //find the final result from the formula
        return loan*(b*result)/(result-1);
    }
}
