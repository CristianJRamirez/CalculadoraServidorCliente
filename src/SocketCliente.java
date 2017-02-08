import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 45858000w on 01/02/17.
 */
public class SocketCliente {

    private static String repetir;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la IP");
        System.out.println("172.31.numClase.NumPC");
        String ip=sc.nextLine();

        do {


        System.out.println("Escribeme la operacion");
        String operacion=sc.nextLine();

        //for (int i = 0; i < 64512; i++) {

            try {
                // System.out.println("Creamos un cliente (creando socket)");

                //Socket de TCP
                Socket cliente = new Socket();
                //Socket de UDP
                // DatagramSocket udp = new DatagramSocket();

                //System.out.println("Estableciendo conexion");

                //localhost = 127.0.0.1
                //InetSocketAddress addres = new InetSocketAddress(ip, i);
                InetSocketAddress addres = new InetSocketAddress(ip, 5555);

                cliente.connect(addres);

                InputStream is = cliente.getInputStream();
                OutputStream os = cliente.getOutputStream();

                //System.out.println("Enviando Mensaje");

                //String mensaje = "Mi primer mensaje que navega";
                os.write(operacion.getBytes());

                //Recojemos el resultado del servidor
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String linea = entrada.readLine();
                System.out.println(linea);

                //System.out.println("El puerto "+i+" esta abierto");

                //System.out.println("Cerrando el socket del cliente");


                cliente.close();

                //System.out.println("Terminado");


            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
            System.out.println("Repetir -> Si (s) / No (n)");
            repetir=sc.nextLine();
    }while(repetir.equals("s"));

    }
}
