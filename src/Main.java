/**
 * Created by mles on 2017-04-22.
 */
public class Main {
    public static void main(String args[])
    {
        Server server = new Server(1234);
        try {
            server.run();
        }catch(Exception e){
            System.out.println("Bad init of server!");
        }
    }

}
