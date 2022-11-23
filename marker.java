import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//needed to import these to write to a file

class marker{
  Scanner reader = new Scanner(System.in); //initializing scanner to be used globally
  int[][] grades = null; //2d array holding the grades of each person and each question answered
  private String[][] studentInfo = null; //2d array containing the student data from the file the user inputted
  Double[] Q1res = null; //array storing the answers to one of the questions
  Double[] Q2res = null; //array storing the answers to one of the questions
  Double[] Q3res = null; //array storing the answers to one of the questions
  public marker(){
    studentInfo = new String[5][4];
  }
  public void storingQ1(){
            Double Q1res[][] = new Double[3][3];
            for(int i=0; i<answerData.Q1res.length; i++){
                if(answerData.Q1res[i] == null){
                    Q1res[1][i] = null;
                }else{
                    Q1res[1][i] = answerData.Q1res[i];
                }
            }
    }
    
  public void storingQ2(){
            Integer Qres[][] = new Integer[3][3];
            for(int i=0; i<answerData.Q2res.length; i++){
                if(answerData.Q2res[i] == null){
                    Qres[1][i] = null;
                }else{
                    Qres[1][i] = answerData.Q2res[i].intValue();
                }
            }
    }

  public void storingQ3(){
            Integer Qres[][] = new Integer[3][3];
            for(int i=0; i<answerData.Q3res.length; i++){
                if(answerData.Q3res[i] == null){
                    Qres[1][i] = null;
                }else{
                    Qres[1][i] = answerData.Q3res[i].intValue();
                }
            }
    }
    public void loadResponseData(){
        System.out.println("Please input the name of a file containing student response data corresponding to the previous answer data: ");
        String fileName = reader.nextLine();
        //storing answers from before all into a single 2d array
        Integer Qres[][] = new Integer[3][3];
        if(fileWriter.Q1res != null){
          storingQ1();
        }
        if(fileWriter.Q2res != null){
          storingQ2();
        }
        if(fileWriter.Q3res != null){
          storingQ3();       
        }
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
            String answerStr[] = null;
            //Reading the file using readLine() method
            String contentLine = br.readLine();
            int count=0;
            while (contentLine != null) {
                contentLine = br.readLine();
                //break if the next line is empty
                if (contentLine == null || contentLine.equals("")) return;
                //splitting the line by "," and then storing each value in an array
                //-1 keeps the empty values
                String[] tokens = contentLine.trim().split(",", -1);
                //populating the 2d array; grades
                int gradeSize = Math.min(tokens.length-4, 3);
              
                if(grades == null){
                    //initialize grades based with indecies based off of the amount of students and the size of the questions
                    grades = new int[studentInfo.length][gradeSize];
                    answerStr = new String[gradeSize];
                }
                System.out.println(contentLine + " " + tokens.length + " " + gradeSize);
                int pos = 4;
                //finding the Qres size for given row
                for(int i=0; i<gradeSize; i++){
                    int QresSize = 0;
                    for (int j = 0; j<Qres[i].length;  j++){
                        //don't increase size if null is found
                        if (Qres[i][j] != null) QresSize++;
                    }
                    //array (answerStr) taking in the answer data provided in the file
                    answerStr[i] = tokens[pos];
                    //System.out.println("answerStr = " + answerStr[i]);
                    //splitting the data again by ";" to get each individual variable answer and stores it in a temporary array
                    if(answerStr[i].length() > 1 && answerStr[i].contains(";") && answerStr[i].split(";").length == QresSize){
                        String temp[] = answerStr[i].split(";");
                        //grading the answers
                        boolean gradeMatch = true;
                        inner: for(int j=0; j<Qres[i].length; j++){
                            //break out of loop marked "inner" if null is found
                            if(Qres[i][j] == null) break inner;
                            //break out of loop marked "inner" if the length of the 'variable' is less than 2 or does not have "="
                            //this is to find non fully answered questions
                            if(temp[j].length() < 2 || !temp[i].contains("=")){
                                gradeMatch = false;
                                break inner;
                            }
                            //splits the values again by the "=" to find the numbers corresponding to the variables
                            int val = Integer.parseInt(temp[j].split("=")[1]);
                            gradeMatch = gradeMatch && (Qres[i][j] != null) && val == Qres[i][j];
                            //System.out.println("yy " + temp[j]);
                        }
                        //populates the array with 1 if answer is correct and 0 if it is incorrect
                        if(gradeMatch){
                            grades[count][i] = 1;
                        }
                    //seperate case for "n/a" answer
                    //if person answered "n/a" and the question's actual answer is null, then answer is correct
                    //else incorrect
                    } else if(answerStr[i].toUpperCase().equals("N/A") && Qres[pos-4][i] == null){
                        grades[count][i] = 1;
                    } else{
                        grades[count][i] = 0;
                    }
                    pos++;
                }
                //prints out the grades given to each person split in between each question
                if (gradeSize == 3) System.out.println("XX " + grades[count][0] + " " + grades[count][1] + " " + grades[count][2] + " ");
                    else System.out.println("XX " + grades[count][0] + " " + grades[count][1] + " ");
                count++;
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

    }