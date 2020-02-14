
public class App {
    public static void main(String[] args) throws Exception {
        dbcon connect = new dbcon();
//        System.out.println("Hello Java");
        System.out.println(connect.checkAuth());
    }
}