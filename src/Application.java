import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Recognizer rec = new Recognizer();

        rec.addToMap("one");
        rec.addToMap("two");
        rec.addToMap("eleven");
        rec.addToMap("twenty two");
        rec.addToMap("one hundred ten");
        rec.addToMap("three thousand two hundred one");
        rec.addToMap("");
        rec.addToMap("fff");
        rec.addToMap("русский");
        rec.addToMap("three");

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                String temp = new String();
                while (!rec.isEmpty()) {
                    rec.addToMap(temp);
                    Scanner scanner = new Scanner(System.in);
                    temp = scanner.nextLine();
                    //rec.addToMap("nine hundred");
                }
                System.out.print("\nAdd thread finished");
            }
        };

        Thread t1 = new Thread(runnable1);

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (!rec.isEmpty()) {
                    System.out.print(rec.getMinAndDelete() + "\n");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.print("\nDelete thread interrupted");
                    }
                }

                System.out.print("\nDelete thread finished");
            }
        };

        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
    }

}