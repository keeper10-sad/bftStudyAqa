import java.util.Arrays;

public class Massive{
    static void main(String[] args) {
        String[] words = {"привет", "ПОКА", "гипербола"};
        System.out.println(Arrays.toString(words));
        System.out.println(words[0].toUpperCase());
        System.out.println(words[1].toLowerCase());
        System.out.println(words[2].replace("п","О"));
        StringBuilder builder = new StringBuilder(words[2]);
        builder.insert(3," ");
        System.out.println(builder.replace(2,3,"О"));
    }
}

