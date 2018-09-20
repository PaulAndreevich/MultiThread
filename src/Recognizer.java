import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Values {
    private static final Map<String,Integer> myMap;
    static {
        Map<String, Integer> aMap = new HashMap<String,Integer>();
        aMap.put("zero",0);
        aMap.put("one", 1);
        aMap.put("two", 2);
        aMap.put("three", 3);
        aMap.put("four", 4);
        aMap.put("five", 5);
        aMap.put("six", 6);
        aMap.put("seven", 7);
        aMap.put("eight", 8);
        aMap.put("nine", 9);
        aMap.put("ten", 10);
        aMap.put("eleven", 11);
        aMap.put("twelve", 12);
        aMap.put("thirteen", 13);
        aMap.put("fourteen", 14);
        aMap.put("fiveteen", 15);
        aMap.put("sixteen", 16);
        aMap.put("seventeen", 17);
        aMap.put("eighteen", 18);
        aMap.put("nineteen", 19);
        aMap.put("twenty", 20);
        aMap.put("thirty", 30);
        aMap.put("fourty", 40);
        aMap.put("fifty", 50);
        aMap.put("sixty", 60);
        aMap.put("sevety", 70);
        aMap.put("eighty", 80);
        aMap.put("ninety", 90);
        aMap.put("hundred", 100);
        aMap.put("thousand", 1000);
        myMap = Collections.unmodifiableMap(aMap);
    }

    public static java.lang.Integer getValue(String word){

        if (myMap.get(word) == null) {
            return null;
        }
        return myMap.get(word);
    }
}

public class Recognizer {
    Map<Integer, String> numbersMap;

    public Recognizer(){
        numbersMap = new HashMap<Integer, String>();
    }

    public void addToMap(String number) {
        if (!number.isEmpty()) {
            Integer result = 0;
            String[] words = number.split("\\W+");

            if (words.length != 0) {
                Boolean thou = true;
                Boolean hund = true;
                Boolean wrongInput = false;

                if (number.contains("thousand")) thou = false;
                if (number.contains("hundred")) hund = false;

                for (int i = 0; i < words.length; i++) {
                    if (Values.getValue(words[i]) == null) {
                        wrongInput = true;
                        break;
                    }
                    if (words[i].equals("thousand")) {
                        result += Values.getValue(words[i - 1]) * Values.getValue(words[i]);
                        thou = true;

                    } else if (words[i].equals("hundred")) {
                        result += Values.getValue(words[i - 1]) * Values.getValue(words[i]);
                        hund = true;

                    } else if (thou == true && hund == true) {
                        result += Values.getValue(words[i]);
                    }
                }

                if(!wrongInput) numbersMap.put(result, number);
            }

        }

    }

    public String getMinAndDelete(){
        Iterator it = numbersMap.entrySet().iterator();

        Integer min = (Integer)((Map.Entry)it.next()).getKey();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if ((Integer)pair.getKey() < min) {min = (Integer)pair.getKey();}
            //System.out.println(pair.getKey() + " = " + pair.getValue());
        }

        String temp = numbersMap.get(min);

        numbersMap.remove(min);

        return temp;
    }



    public Boolean isEmpty(){
        return numbersMap.isEmpty();
    }
}
