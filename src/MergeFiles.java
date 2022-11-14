import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class MergeFiles {
    List <Newspaper> collection = new ArrayList<Newspaper>() {
    };

    MergeFiles(String p1, String p2) {
        fileToCollection1(p1);
        fileToCollection2(p2);
        sortCollection();
    }

    private void sortCollection() {
        if (collection.size() > 0) {
            Collections.sort(collection, new Comparator<Newspaper>() {
                @Override
                public int compare(final Newspaper object1, final Newspaper object2) {
                    return object1.getName().compareTo(object2.getName());
                }
            });
        }
        Collections.reverse(collection);



        for(int i = 0; i<collection.size(); i++){
            System.out.println(collection.get(i).getText());
        }
        List<Newspaper> noModify_collection = Collections.unmodifiableList(collection);
    }

    private void fileToCollection2(String p2) {
        final File file = new File(p2);
        String partOut;
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                partOut = scanner.nextLine();
                String[] temp = partOut.split(":");

                Newspaper a = new Newspaper(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),
                        Integer.parseInt(temp[3]),temp[4],Integer.parseInt(temp[5]));
                collection.add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fileToCollection1(String p1) {
        final File file = new File(p1);
        String partOut;
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){

                partOut = scanner.nextLine();
                partOut = partOut.replaceAll("\\[|\\]" , "");
                String[] temp = partOut.split(":");

                String[] tempForNames = temp[1].split("\\,\\s+");
                for(String t : tempForNames){
                    Newspaper n = new Newspaper(t, temp[0]);
                    collection.add(n);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
