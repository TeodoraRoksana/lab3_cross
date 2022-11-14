public class Main {
    public static void main(String[] args) {

        ScannerClass scannerClass = new ScannerClass("newspapers.txt");
        MergeFiles mergeFiles = new MergeFiles("newspaper(new).txt", "NewspaperFrequencyResponse.txt");
    }
}