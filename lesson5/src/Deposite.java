import java.util.Scanner;

public class Deposite {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ввод суммы депозита
        System.out.println("Введите сумму вклада: ");
        float money = scanner.nextFloat();

        //Ввод количества месяцев
        System.out.println("Введите количество месяцев: ");
        float month = scanner.nextFloat();

        // Процент увеличения с каждым месяцем
        double percent = 0.07; // 7%

        System.out.println("Сумма по вкалду составит: ");
        for (int i = 1; i<=month;i++){
            money += money * percent;
            System.out.printf("Месяц %d: сумма = %.2f руб%n", i, money);

        }
    }
}
