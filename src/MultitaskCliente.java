import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class MultitaskCliente{
    private static final String MULTICAST_GROUP = "224.1.1.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            MulticastSocket multicastSocket = new MulticastSocket(PORT);

            multicastSocket.joinGroup(group);

            Map<String, Integer> wordCountMap = new HashMap<>();

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                multicastSocket.receive(packet);

                String word = new String(packet.getData(), 0, packet.getLength());
                processReceivedData(word, wordCountMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processReceivedData(String data, Map<String, Integer> wordCountMap) {
        System.out.println("Received: " + data);

        // Actualiza el mapa de conteo
        wordCountMap.put(data, wordCountMap.getOrDefault(data, 0) + 1);

        // Muestra el conteo actualizado
        System.out.println("Repeticion: " + wordCountMap.get(data));
    }
}
