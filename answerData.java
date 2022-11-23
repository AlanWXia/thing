import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//needed to import these to write to a file

class answerData{
  static Double[] Q1res = null; //array storing the answers to one of the questions
  static Double[] Q2res = null; //array storing the answers to one of the questions
  static Double[] Q3res = null; //array storing the answers to one of the questions
  static Scanner reader = new Scanner(System.in); //initializing scanner to be used globally
    /**
     * take the user inputted answer file and splits it at every header (A1, A2, A3)
     * stores the answers in an arrays
     */

  public void readInResults(){
        System.out.println("Please input the name of a file containing answer data: ");
        String fileName = reader.nextLine();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
            
            //Reading the file using readLine() method
            String contentLine = "";
            while (contentLine != null) {
                contentLine = br.readLine();
                if(contentLine == null) break;
                if(contentLine.toUpperCase().equals("A1")){
                    contentLine = br.readLine();
                    if(contentLine.toUpperCase().equals("N/A")){
                        Q1res = new Double[2];
                        Q1res[0] = null;
                        Q1res[1] = null;
                    } else{
                        String[] ans = contentLine.split(";");
                        Q1res = new Double[ans.length];
                        for(int i=0; i<ans.length; i++){
                            Q1res[i] = Double.parseDouble(ans[i].split("=")[1]);
                        }
                    }
                } else if(contentLine.toUpperCase().equals("A2")){
                    contentLine = br.readLine();
                    if(contentLine.toUpperCase().equals("N/A")){
                        Q2res = new Double[2];
                        Q2res[0] = null;
                        Q2res[1] = null;
                    } else{
                        String[] ans = contentLine.split(";");
                        Q2res = new Double[ans.length];
                        for(int i=0; i<ans.length; i++){
                            Q2res[i] = Double.parseDouble(ans[i].split("=")[1]);
                        }
                    }
                } else if(contentLine.toUpperCase().equals("A3")){
                    contentLine = br.readLine();
                    if(contentLine.toUpperCase().equals("N/A")){
                        Q3res = new Double[2];
                        Q3res[0] = null;
                        Q3res[1] = null;
                    } else{
                        String[] ans = contentLine.split(";");
                        Q3res = new Double[ans.length];
                        for(int i=0; i<ans.length; i++){
                            Q3res[i] = Double.parseDouble(ans[i].split("=")[1]);
                        }
                    }
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
}