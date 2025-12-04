package it.unical.demacs;

public class App {
    public static void main(String[] args) {
        Inventory inv = new Inventory();

        inv.addProduct("Pasta", 50);
        inv.addProduct("Pecorino Romano", 20);
        inv.addProduct("Guanciale", 40);
        inv.addProduct("Uova", 10);
        inv.addProduct("Peppe Nero", 20);

        System.out.printf("Peppe Nero %su: %s%n", 10, inv.makeOrder("peppe nero", 10));
        System.out.printf("Guanciale %su: %s%n", 30, inv.makeOrder("guanCiale", 30));
        System.out.printf("Pasta %su: %s%n", 60, inv.makeOrder("pasta", 60));
        System.out.printf("Peppe Bianco %su: %s%n", 60, inv.makeOrder("peppe Bianco", 60));
        System.out.printf("Peppe Nero %su: %s%n", 50, inv.makeOrder("peppe Nero", 50));
        System.out.printf("Pecorino Romano %su: %s%n", 20, inv.makeOrder("Pecorino ROMANO", 20));

    }
}
