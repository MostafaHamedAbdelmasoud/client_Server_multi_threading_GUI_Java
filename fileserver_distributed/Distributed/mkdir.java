package tutorial;

/**
 *
 * @author Carter John
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class Tutorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter Path: ");
        Scanner input = new Scanner(System.in);
        String path1= input.nextLine();
        Path path = Paths.get(path1);
        createDirectory(path.toString());
    }
    
    private static void createDirectory(String pathName){
        File files = new File(pathName);
        if(!files.exists()){ //if the file folder does not exist
            if(files.mkdirs()){ //if you want to create sub folder. you can change mkdir to mkdirs
                System.out.println("Directory Created in"+files.getAbsolutePath());
            }
            else{
                System.out.println("Problem Occured creating directory"); 
            }
        }
        else{
            System.out.println("Directory "+files.getAbsolutePath()+" already exist !");
        }
    }
    
}
