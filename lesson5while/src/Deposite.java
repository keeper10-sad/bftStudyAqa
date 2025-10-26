import java.util.Scanner;

public class Deposite {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Ввод суммы вклада
        System.out.println("Укажите сумму вклада: ");
        float money = scanner.nextFloat();

        //Ввод количества месяцев
        System.out.println("Укажите на сколько месяцев: ");
        int months = scanner.nextInt();

        //Процент увеличения суммы с каждым месяцем, 5 %
        double percent = 0.05;
        int month = 1;

        System.out.println("Сумма по вкладу составит: ");
        while(month<=months){
            money += money*percent;
            System.out.printf("Месяц %d: сумма = %.2f руб%n",month,money);
            month++;
        }
    }
}
