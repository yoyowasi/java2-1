package chap04;

import java.util.Scanner;

public class Ev3_14 {
    class Rectangle {
        int width;
        int height;

        public int getArea() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Ev3_14 ev3_14 = new Ev3_14();
        Ev3_14.Rectangle rect = ev3_14.new Rectangle();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">> ");
        rect.width = scanner.nextInt();
        rect.height = scanner.nextInt();
        System.out.println("사각형의 면적은 " + rect.getArea());

        scanner.close();
    }
}