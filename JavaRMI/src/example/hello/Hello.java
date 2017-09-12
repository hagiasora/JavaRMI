package example.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Hello extends Remote {
    String sayHello() throws RemoteException;
    String name(String namaPengunjung) throws RemoteException;
    String emptySeat(int row) throws RemoteException;
    String isSingle (int jumlah) throws RemoteException;
}
