import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//can only parse 3 different questions each with 2-3 variables
public class QParser{
    private int[][] Q1 = null; //2d array containing one of the questions from the sample question file
    private int[][] Q2 = null; //2d array containing one of the questions from the sample question file
    private int[][] Q3 = null; //2d array containing one of the questions from the sample question file
    public static void main(String args[]){ //main not needed
        QParser P = new QParser();
        P.readAndParseEquationData("C:\\Users\\alan3\\Downloads\\data\\data\\sample_q_1.txt");
        int[][] q1 = P.getQ(1);
        int[][] q2 = P.getQ(2);
        System.out.println(q1[0][1]); //tests
        System.out.println(q2[0][1]); //tests
    }
    public QParser(){

    }
    /**
     * 
     * @param questionNum the number of the question you want
     * @return the int array of the question number requested
     */
    public int[][] getQ(int questionNum){
        if(questionNum == 1){
            return Q1;
        } else if(questionNum == 2){
            return Q2;
        } else if(questionNum == 3){
            return Q3;
        }
        else return null;
    }
    /**
     * prompts user to input a file
     * calls the method to parse the data read from the user's inputted file
     * calls the method to populate the arrays for each question
     * @param fileName the file the user inputted
     */
    public void readAndParseEquationData(String fileName){
        //Scanner reader = new Scanner(System.in);
        
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
            
            //Reading the file using readLine() method
            String contentLine = "";// br.readLine();
            while (contentLine != null) {
                contentLine = br.readLine();
                if(contentLine == null) break;
                if(contentLine.toUpperCase().equals("Q1")){
                    Q1 = new int[3][4];
                    populateEqData(Q1, br);
                } else if(contentLine.toUpperCase().equals("Q2")){
                    Q2 = new int[3][4];
                    populateEqData(Q2, br);
                } else if(contentLine.toUpperCase().equals("Q3")){
                    Q3 = new int[3][4];
                    populateEqData(Q3, br);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (br != null){
                    br.close();
                }
            }
            catch (IOException e) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
  	}
    /**
     * populates the array Q with the equation data from the file the user specified
     * @param Q the array to be populated with the equation data [a, b, c, d] representing equation
     *                                                                          ax+by+cz+d=0
     * @param br buffer reader
     */
    private void populateEqData(int[][] Q, BufferedReader br){
        try{
            int row = 0;
            String contentLine = "";
            while(true){
                contentLine = br.readLine();
                if(contentLine == null || contentLine.equals("")){
                    break;
                }
                String[] eq = contentLine.split("=");
                String LHS = eq[0].toLowerCase();
                String RHS = eq[1].toLowerCase();
                //System.out.println(LHS + RHS);
                int[] LHSarr = parse(LHS);
                int[] RHSarr = parse(RHS);
                for(int i=0; i<4; i++){
                    Q[row][i] = LHSarr[i] - RHSarr[i];
                }
                row++;
            }
            /*
            System.out.println("Q results");
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    System.out.print(Q[i][j] + " ");
                }
                System.out.println("");
            }
            */
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * parses the data of one question taken from the file that the user inputted
     * input string like "3x+5y-6z=9" into an int array [3,5,-6,-9]
     * @param s_in the string input
     * @return the int array
     */
    private int[] parse(String s_in) {
        String s = s_in.replaceAll("\\s+", ""); //reformatting string by removing whitespaces first
        int res[] = {0, 0, 0, 0}; // [a,b,c,d]
        
        int leadingPos = 0;
        int endPos = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
        
            if (isVar(c)) {
                endPos = i;
                String bloc = s.substring(leadingPos, endPos);
                //System.out.println(bloc);
                if ((bloc.length() > 1) && containsOperator(bloc.substring(1))) {
                    //this block contains constant and a coefficient, e.g. d+ax
                    //parsing out constant first
                    inner:for (int pos = bloc.length()-1; pos > 0; pos--) {
                        char c_local = bloc.charAt(pos);
                        if (isOperator(c_local)) { //at the last operator
                            //finding d value
                            res[3] = Integer.parseInt(bloc.substring(0, pos));
                            bloc = bloc.substring(pos);
                            break inner;
                        }
                    }
                }
                if (c == 'x' || c == 'X') {
                    if (bloc.equals("") || bloc.equals("+")) res[0] = 1;
                    else if (bloc.equals("-")) res[0] = -1;
                    else res[0] = Integer.parseInt(bloc);
                } else if (c == 'y' || c == 'Y') {
                    if (bloc.equals("") || bloc.equals("+")) res[1] = 1;
                    else if (bloc.equals("-")) res[1] = -1;
                    else res[1] = Integer.parseInt(bloc);
                } else if (c == 'z' || c == 'Z') {
                    if (bloc.equals("") || bloc.equals("+")) res[2] = 1;
                    else if (bloc.equals("-")) res[2] = -1;
                    else res[2] = Integer.parseInt(bloc);
                }
                //moving index past the variable
                leadingPos = i+1;
            }
        }
        //checks if d value is the last value in the line
        if ((leadingPos >= endPos) && (leadingPos < s.length())) res[3] = Integer.parseInt(s.substring(leadingPos));
        return res;
    }
    private boolean containsOperator (String s) {
        return s.contains("+") || s.contains("-");
    }
    private boolean isOperator(char c) {
        return c == '+' || c == '-';
    }
    private boolean isVar (char c) {
        return (c == 'x' || c == 'X' || c == 'y' || c == 'Y' || c == 'z' || c == 'Z');
    }
}