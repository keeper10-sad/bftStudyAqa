abstract class Shape {
    public String color;

    public Shape(String color){
        this.color = color;
    }

    abstract void draw(); // абстрактный метод реализуется в каждом подклассе

    static class Circle extends Shape{
        public Circle(String color){
            super(color);
        }

        @Override
        void draw() {
            System.out.println(color + " круг рисуется");
        }
    }

    static class Rectangle extends Shape{
        public Rectangle(String color){
            super(color);
        }

        @Override
        void draw() {
            System.out.println(color + " прямоугольник рисуется");
        }
    }

    static void main(String[] args) {
        Shape.Circle circle = new Shape.Circle("Желтый");
        Shape.Rectangle rectangle = new Shape.Rectangle("Фиолетовый");

        circle.draw();
        rectangle.draw();
    }
}
