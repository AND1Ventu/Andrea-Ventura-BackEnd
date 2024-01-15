import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class MultimediaPlayerClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            String welcomeMessage = (String) inFromServer.readObject();
            System.out.println(welcomeMessage);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Image File");
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                outToServer.writeObject(filePath);

                // Receive the image from the server
                int length = inFromServer.readInt();
                byte[] imageBytes = new byte[length];
                inFromServer.readFully(imageBytes);

                // Convert the byte array back to a BufferedImage
                BufferedImage receivedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                // Display the received image (You need to implement this part based on your UI)
                // For simplicity, we just display a message dialog
                JOptionPane.showMessageDialog(null, "Image received from server!");

                // Close the socket
                socket.close();
            } else {
                System.out.println("File selection canceled by the user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
