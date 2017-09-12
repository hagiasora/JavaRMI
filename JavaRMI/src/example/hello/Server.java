package example.hello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Hello {

    public Server(ArrayList availableSeat) {
        this.availableSeat = availableSeat;
    }

    ArrayList availableSeat = new ArrayList();

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }

    @Override
    public String emptySeat(int row) throws RemoteException {
        if (availableSeat.get(row) != null) {
            return "Udah ada isinya nih Kak, mau pangku-pangkuan? :v";
        }
        availableSeat.set(row, 1);
        return "OK kak, Booked";
    }

    @Override
    public String isSingle(int jumlah) throws RemoteException {
        if (jumlah == 1){
            return "Cieee yang sendirian ajaaa :v";
        }else if(jumlah == 2){
            return "Cieee sama gebetan yaaa";
        }else if(jumlah > 25){
            return "Maaf kak, kursinya cuman ada 25 tuh :( mau bikinin gedung baru nggak?";
        }
        return "OK";
    }

    public void initSeat(ArrayList seat) {
        for (int i=0; i<9; i++) {
            seat.add(null);
        }
    }
    @Override
    public String name(String namaPengunjung) throws RemoteException {
        if (namaPengunjung != null){
            return "Please type your name.";
        }
        return namaPengunjung;
    }



    public static void main (String args[]) {
        ArrayList availableSeat = new ArrayList();
        Server obj = new Server(availableSeat);
        obj.initSeat(availableSeat);

        try {
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj,0);

            //Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
