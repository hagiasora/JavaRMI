package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public Client() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        Scanner sc = new Scanner(System.in);
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println("Hallo, Selamat datang di E-tikceting \nSilakan masukkan nama : ");
            String namaPengunjung = sc.nextLine();
            System.out.println("Halo "+namaPengunjung+ " mau pesan berapa tiket?");
            int jumlah = sc.nextInt();
            String isJombloNgenes = stub.isSingle(jumlah);
            System.out.println(isJombloNgenes);
            for(int i=1; i < jumlah+1; i++) {
                System.out.println("Nomer kursi " + i);
                int row = sc.nextInt();
                String isSeatEmpty = stub.emptySeat(row);
                System.out.println("response: " + isSeatEmpty);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
