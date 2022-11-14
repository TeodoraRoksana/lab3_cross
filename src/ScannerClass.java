import java.io.*;
import java.util.*;
public class ScannerClass {
    Map<String, Set<String>> map = new LinkedHashMap<>();
    String text; //name year circulation periodicity publishing
    NewspaperClass newspaper = new NewspaperClass();

    ScannerClass(String path){
        final File file = new File(path);
        text = ScanFile(file);
        System.out.println(text);
        swapSet();
        deleteMoreThan();
        mapInFile();
        newspaper.dataInFile();
    }

    private void deleteMoreThan() {
        try {Scanner scanner = new Scanner(System.in);
            Map<String, Set<String>> mapTemp = new LinkedHashMap<>();
            System.out.println("Enter the min number of newspaper. Less than this would be deleted");
            int i = scanner.nextInt();
            for (Map.Entry<String, Set<String>> set : map.entrySet()) {
                if (set.getValue().size() >= i) {
                    mapTemp.put(set.getKey(), set.getValue());
                }
            }
            map = mapTemp;
        } catch (Exception e) {
            deleteMoreThan();
        }
    }

    private void swapSet() {
        int i = 1;
        Set<String> tempTextToEven = getMapValue(1);
        Set<String> tempTextToOdd = null;

        for (Map.Entry<String, Set<String>> set : map.entrySet()) {
            if (i % 2 != 0) {
                tempTextToEven = set.getValue();
                map.put(set.getKey(), tempTextToOdd);
                i++;
            } else {

                tempTextToOdd = set.getValue();
                map.put(set.getKey(), tempTextToEven);
                i++;
            }
        }
        map.put(getMapKey(1), tempTextToEven);

        System.out.println(map);
    }

    private void mapInFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("newspaper(new).txt"));
            writer.write(mapInText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String mapInText(){
        String text = "";
        for(Map.Entry<String, Set<String>> set : map.entrySet()){
            text += set.getKey()+":"+set.getValue()+"\n";
        }
        return text;
    }

    private Set getMapValue(int i){
        Set<String> s = new HashSet<>();
        int k = 1;
        for (Map.Entry<String, Set<String>> set : map.entrySet()) {
            if(k==i){
                return set.getValue();
            }
            k++;
        }
        return null;
    }

    private String getMapKey(int i){
        Set<String> s = new HashSet<>();
        int k = 1;
        for (Map.Entry<String, Set<String>> set : map.entrySet()) {
            if(k==i){
                return set.getKey();
            }
            k++;
        }
        return null;
    }

    private boolean publAlreadyExist(String s) {
        for(Map.Entry<String, Set<String>> set : map.entrySet()){
            if(set.getKey().equals(s)){
                return true;
            }
        }
        return false;
    }


    private String ScanFile(File file) {
        String out = "";
        String partOut;
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){

                partOut = scanner.nextLine();
                String[] temp = partOut.split(":");
                newspaper.getNewspaper().add(new Newspaper(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
                        Integer.parseInt(temp[3]), temp[4]));

                Set<String> a = new HashSet<>();
                if(publAlreadyExist(temp[4])){  //if this publish already in Map, we add old Set to new Set
                    a = map.get(temp[4]);       //
                }                               //
                a.add(temp[0]);                 //and add new valuable to new Set
                map.put(temp[4], a);            //and put new Set in map

                out += partOut+"\n";

            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with file");
            e.printStackTrace();
        }
        return out;
    }
}
