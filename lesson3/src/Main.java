class Phone {
    public String model; // модель телефона
    public double price; // цена телефона
    public int year = 0; // сколько лет прошло
    public static double priceReduction = 100.00; // с каждым годом цена на телефон уменьшается на 100 рублей

    public Phone (){
        this.model = model;
        this.price = price;
        this.year = year;
    }

    //Метод, который уменьшает цену каждый год
    public void decreaseInYear(){
        if (year++ < 5){
            price -= priceReduction; // в первые 5 лет цена снижается на 100 р
        } else {
            price -= priceReduction * 2; // после 5 лет скидка увеличивается в 2 раза
        }
        System.out.println("Год :" + year+ " цена :"+price);
    }
}

public class Main {
void main(String[] args) {
    Phone phone1 = new Phone();
    Phone phone2 = new Phone();
    Phone phone3 = new Phone();
    phone1.model = "Huawei Mate X6";
    phone1.price = 113200;
    phone1.year = 2023;
    phone2.model = "Motorola Razr 60";
    phone2.price = 83490;
    phone2.year = 2024;
    phone3.model = "Samsung Galaxy A17";
    phone3.price = 12790;
    phone3.year = 2025;

    phone1.decreaseInYear();
    phone2.decreaseInYear();
    phone3.decreaseInYear();
}

}