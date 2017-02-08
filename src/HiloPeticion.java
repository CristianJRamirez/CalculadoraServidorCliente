import java.io.*;
import java.net.Socket;

/**
 * Created by 45858000w on 08/02/17.
 */
public class HiloPeticion extends Thread{

    private Socket newSocket;

    public HiloPeticion() {
    }

    public HiloPeticion(Socket newSocket) {
        this.newSocket = newSocket;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = newSocket.getInputStream();

            OutputStream os = newSocket.getOutputStream();

            byte[] mensaje = new byte[50];
            is.read(mensaje);

            Double resultado=calcular(new String (mensaje).trim());

            System.out.println("Mensaje recibido : "+ new String (mensaje));


            PrintWriter salida = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(newSocket.getOutputStream())), true);

            salida.println("El resultado es :-> "+resultado);

            System.out.println("Cerrando el socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Double calcular(String operacion)
    {   Double salida = 0.0;

        String valores[]=null;

        if (operacion.contains("+"))
        {
            salida=suma(operacion.split("\\+"));
        }
        else if (operacion.contains("-"))
        {
            salida=resta(operacion.split("-"));
        }
        else if (operacion.contains("*"))
        {
            salida=multiplicacion(operacion.split("\\*"));
        }
        else if (operacion.contains("/"))
        {
            salida=division(operacion.split("\\/"));
        }


        return salida;
    }


    private Double suma(String[] valores)
    {
        System.out.println(valores[0]+"--"+Double.parseDouble(valores[1]));
        Double resultad =Double.parseDouble(valores[0]) + Double.parseDouble(valores[1]);
        return resultad;
    }

    private Double resta(String[] valores)
    {
        System.out.println(Double.parseDouble(valores[0])+"--"+Double.parseDouble(valores[1]));
        Double resultad =Double.parseDouble(valores[0])-Double.parseDouble(valores[1]);

        return resultad;
    }

    private Double multiplicacion(String[] valores)
    {
        System.out.println(Integer.parseInt(valores[0])+"--"+Integer.parseInt(valores[1]));
        Double resultad =Double.parseDouble(valores[0])*Double.parseDouble(valores[1]);

        return resultad;
    }

    private Double division(String[] valores)
    {
        System.out.println(Double.parseDouble(valores[0])+"--"+Double.parseDouble(valores[1]));
        Double resultad =Double.parseDouble(valores[0])/Double.parseDouble(valores[1]);

        return resultad;
    }






}
