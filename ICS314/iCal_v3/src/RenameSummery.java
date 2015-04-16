import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RenameSummery {
    public static void main(String [] args) {
    	
    	// The name of the file to open.
        String fileName    = "downloads/file.ics";
        String newFileName = "downloads/meetingFile.ics";
        
        // This will reference one line at a time
        String   line      = null;
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(newFileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line.contains("SUMMARY")){
                	bufferedWriter.write("SUMMARY:POSSIBLE MEETING TIME");
                	bufferedWriter.newLine();
                }
                else{
                	bufferedWriter.write(line);
                	bufferedWriter.newLine();
                }
            }    
            
            // Always close files.
            bufferedReader.close();   

            // Always close files.
            bufferedWriter.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("Error with file");                
        }
        catch(IOException ex) {
            System.out.println("Error with file");                   
        }
    }
}