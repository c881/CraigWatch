//
/*
Needed components:
 Comunication manager - URL to craiglist  -Send/Recieve HTTP
 Parser - parsing
 Address metcher - WWW to local address
 Notifier - Favorite format massage
*/
public class Main {
    int i = 3;
    Integer it = 3;
    Integer it2 = new Integer(7);
    String s;

    public Main() {
    }

    public Main(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main myMain = new Main();
        Main myMain2 = new Main(6);
        System.out.println("main " + myMain.i);
        System.out.println("main2 " + myMain2.i);
        myMain.i = 27;
        myMain.incMyI(5);
        System.out.println("main " + myMain.i);

        myMain.printMyI();
    }

    public void incMyI(int a){
        i += a;
    }
    public void printMyI(){
        System.out.println(i);
    }

}
