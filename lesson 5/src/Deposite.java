import java.util.Scanner;

public class Deposite {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ввод суммы вклада
        System.out.println("Укажите сумму вклада: ");
        float money = scanner.nextFloat();

        //Ввод количества месяцев
        System.out.println("Укажите на сколько месяцев: ");
        int month = scanner.nextInt();

        //Процент увеличения суммы с каждым месяцем, 5 %
        double percent = 0.05;

        System.out.println("Сумма по вкладу составит: ");
        for (int i = 1; i<=month; i++){
            money += money*percent;
            System.out.printf("Месяц = %d: сумма = %.2f руб%n",i,money);
        }
    }
}
