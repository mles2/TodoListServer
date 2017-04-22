import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server
{
    private int port;
    private String lastXml;

    Server(int p_port)
    {
        port = p_port;
    }

    void run() throws IOException
    {
        while(true) {

            String message = "";
            //creating socket on port
            ServerSocket socket = new ServerSocket(port);

            //server is waiting here for client
            Socket connectionSocket = socket.accept();

            BufferedReader bufferFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream streamForResponse =
                    new DataOutputStream(connectionSocket.getOutputStream());
            String command;
            command = bufferFromClient.readLine();
            switch (command) {
                case "push": {
                    String line;
                    while (((line = bufferFromClient.readLine()) != null) && !line.equals("q")) {
                        System.out.println(line);
                        message += line + "\n";
                    }
                    System.out.println(message);
                    lastXml = message;
                    streamForResponse.writeBytes("I received your message \n");
                }
                break;
                case "pull": {
                    streamForResponse.writeBytes(lastXml);
                }
                break;
                default:
                    System.out.println("Unrecognized Command!");

            }
            connectionSocket.close();
            socket.close();
            System.out.println("Server is done!");

        }
    }
}
