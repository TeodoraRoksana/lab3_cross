public class Newspaper {
    //name year circulation periodicity publishing
    String name = "";
    int year = 0;
    int circulation = 0;
    int periodicity = 0;
    String publishing = "";
    int frequencyResponse = 0;

    public Newspaper(String s, String s1){
        name = s;
        publishing = s1;
    }
    public Newspaper(String s, int i, int parseInt, int anInt, String s1) {

        name = s;
        year = i;
        circulation = parseInt;
        periodicity = anInt;
        publishing = s1;
        if(periodicity!=0) {
            frequencyResponse = circulation / periodicity;
        }
    }
    public Newspaper(String s, int i, int parseInt, int anInt, String s1, int f) {

        name = s;
        year = i;
        circulation = parseInt;
        periodicity = anInt;
        publishing = s1;
        frequencyResponse = f;
    }

    public String getName(){
        return name;
    }

    public String getText(){
        return name+":"+year+":"+ circulation+":"+ periodicity+":"+ publishing+":"+frequencyResponse;
    }
}
