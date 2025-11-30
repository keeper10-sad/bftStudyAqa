import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box {
    private double height;
    private double width;
    private double depth;

    Box(double width, double height, double depth){
        this.depth = depth;
        this.height = height;
        this.width = width;
    }
    public String toString(){
        return "Box("+width+"*"+height+"*"+depth+")"; // если не переопределить toString, то java будет выводить ссылки на адрес объекта в памяти
    }

    public static void main(String[] args) { //Коллекция с коробками
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(20,30,50));
        boxes.add(new Box(10,40,70));
        boxes.add(new Box(40,80,10));
        boxes.add(new Box(1,32,10));
        boxes.add(new Box(100,60,80));

        List<Box>longWidthBox = new ArrayList<>(); //коллекция для коробок шире 30 см
        replaceLongWidthBox(boxes, longWidthBox);

        System.out.println("Оставшиеся: "+boxes);
        System.out.println("перемещенные: "+longWidthBox);
    }

    static void replaceLongWidthBox(List<Box> from, List<Box> to){ // Метод для переноса коробок шире 30 см
        Iterator<Box> iterator = from.iterator(); //Итератор для перебора

        while (iterator.hasNext()){
            Box box = iterator.next();
            if (box.width > 30){
                to.add(box);
                iterator.remove();
            }
        }
    }
}
