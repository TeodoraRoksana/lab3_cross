import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class NewspaperClass {
    List<Newspaper> newspaper = new ArrayList<>();

    NewspaperClass(){

    }

    public List<Newspaper> getNewspaper() {
        return newspaper;
    }
    private String getFullText(){
        String data = "";
        for(int i = 0; i<newspaper.size(); i++){
            data+=newspaper.get(i).getText()+"\n";
        }
        System.out.println(data);
        return data;
    }
    public void dataInFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("NewspaperFrequencyResponse.txt"));
            writer.write(getFullText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
