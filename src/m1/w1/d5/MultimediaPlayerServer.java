import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultimediaPlayerServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server in attesa di connessioni...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione accettata.");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            sendTextMessage("Benvenuto al lettore multimediale multiplayer!");

            String filePath = (String) inFromClient.readObject();
            System.out.println("Ricevuto file: " + filePath);

            BufferedImage image = readImage(filePath);
            sendImage(image);

            // Display the received image on the server
            displayImage(image);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendTextMessage(String message) {
        try {
            outToClient.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendImage(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            outToClient.writeInt(imageBytes.length);  // Send the length of the byte array
            outToClient.write(imageBytes);           // Send the byte array
            outToClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage readImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void displayImage(BufferedImage image) {
        // Your code to display the image on the server GUI goes here
        // For simplicity, you can print a message
        System.out.println("Displaying the received image on the server.");
    }
}
