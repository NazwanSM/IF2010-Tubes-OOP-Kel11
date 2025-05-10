import java.util.Scanner;

public class WorldMap extends Map {
    public WorldMap(String name, int width, int length) {
        super(name, width, length);
    }

    public void interact() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Kamu telah memasuki World Map.");
        System.out.println("Kemana kamu ingin pergi?");
        System.out.println("1. Rumah Mayor Tadi");
        System.out.println("2. Forest River");
        System.out.println("3. Mountain Lake");
        System.out.println("4. Ocean");
        System.out.println("5. Store Emily");
        System.out.print("Pilihanmu: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Mengunjungi rumah Mayor Tadi...");
                break;
            case 2:
                System.out.println("Mengunjungi Forest River...");
                break;
            case 3:
                System.out.println("Mengunjungi Mountain Lake...");
                break;
            case 4:
                System.out.println("Mengunjungi Ocean...");
                break;
            case 5:
                System.out.println("Mengunjungi Store Emily...");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }
}