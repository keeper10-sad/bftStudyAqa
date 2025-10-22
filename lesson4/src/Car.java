public class Car {
    public String model;
    public int year;
    static int volumeLevel = 100;

        public Car(){
        this.model = model;
        this.year = year;
    }

    public void makeBeBe(){
        if(year>=2025){
            volumeLevel = 100;
        } else if (year<2025 && year>= 2010){
            volumeLevel -= 10;
        } else if (year<2010 && year>= 2000) {
            volumeLevel -= 20;
        } else {
            volumeLevel -= 30;
        }

        System.out.println("Модель:"+model+" Год выпуска:" +year+" Уровень звука:" + volumeLevel);
    }

    public static class Main{
        static void main(String[] args) {
            Car car1 = new Car();
            Car car2 = new Car();
            Car car3 = new Car();
            Car car4 = new Car();

            car1.model = "Kia Rio";
            car1.year = 2008;

            car2.model = "Opel Kadet";
            car2.year = 1990;

            car3.model = "Chery Tiggo 7";
            car3.year = 2025;

            car4.model = "Nissan Qashqai";
            car4.year = 2020;

            car1.makeBeBe();
            car2.makeBeBe();
            car3.makeBeBe();
            car4.makeBeBe();

        }
    }
}
