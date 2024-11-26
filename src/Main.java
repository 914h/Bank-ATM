import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ATMService atmService = new ATMService();
        System.out.println("Welcome to the ATM!");
        atmService.start();
    }
}
