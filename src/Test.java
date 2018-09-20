import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    private static List<String> output = new ArrayList<String>();
    private static List<String> result = new ArrayList<String>();
    static Boolean deleteFinished = false;
    static Boolean addFinished = false;

    public static void main(String[] args) throws InterruptedException {
        Recognizer rec = new Recognizer();
        List<String> inputs = new ArrayList<String>();


        output.add("one");
        output.add("two");
        output.add("three");
        output.add("eleven");
        output.add("twenty two");
        output.add("thirty");
        output.add("one hundred ten");
        output.add("three thousand two hundred one");

        System.out.print("\nTESTING!\n");
        System.out.print("\nWait until testing is finished...\n");

        inputs.add("three");
        inputs.add("русский");
        inputs.add("fff");
        inputs.add("thirty");


        rec.addToMap("one");
        rec.addToMap("two");
        rec.addToMap("eleven");
        rec.addToMap("twenty two");
        rec.addToMap("one hundred ten");
        rec.addToMap("three thousand two hundred one");
        rec.addToMap("");

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                for(String i: inputs){
                    rec.addToMap(i);
                }

                //System.out.print("\nAdd thread finished\n");
                addFinished = true;
            }
        };

        Thread t1 = new Thread(runnable3);

        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                String temp = new String();
                while (!rec.isEmpty()) {
                    result.add(rec.getMinAndDelete());
                }

              
                deleteFinished = true;
            }
        };

        Thread t2 = new Thread(runnable4);
        t1.start();
        t2.start();

        for(int i = 0; i < 2;i++){
            t1.join();
            t2.join();
        }


        System.out.print("\nTest result: ");
        if (result.equals(output)) {System.out.print("Tests passed\n");}
        else { System.out.print("Tests NOT passed\n"); }

    }
}
