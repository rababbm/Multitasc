import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class MulticastServer {
    private static final String MULTICAST_GROUP = "224.1.1.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            MulticastSocket multicastSocket = new MulticastSocket();

            Random random = new Random();
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < 30000) {  // Espera al menos 30 segundos
                String word = generateRandomWord();
                byte[] buffer = word.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                multicastSocket.send(packet);

                // Espera un breve tiempo antes de enviar la siguiente palabra
                Thread.sleep(2000);
            }

            multicastSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomWord() {
        String[] wordList = {"rabab", "brenda", "nereida", "martin", "alejandra", "Fatima"};
        Random random = new Random();
        return wordList[random.nextInt(wordList.length)];
    }
}
