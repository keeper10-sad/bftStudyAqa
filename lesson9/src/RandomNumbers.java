public class RandomNumbers {
    static void main(String[] args) {
        int[]numbers = new int[3];

        for (int i = 0; i < numbers.length; i++) {  // создан массив из 3 строк
            numbers [i] = (int)(Math.random() * 3);
        }

        for (int i = 0; i < numbers.length; i++) { // заполнение строк массива
            System.out.println("Строка "+ i +" : "+numbers[i]);
        }

        selectRandom(numbers); // вывод рандомной строки с помощью метода

    }
    public static  void selectRandom(int[] numbers) {
        int randomIndex = (int)(Math.random()* numbers.length);
        System.out.println("Строка " + numbers[randomIndex]);
    }
}
