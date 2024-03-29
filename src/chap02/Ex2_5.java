package chap02;
import java.util.Scanner;

public class Ex2_5 {

    public static void main(String[] args) {
        
    System.out.println("이름, 도시 ,나이를 빈칸으로 분리하여 입려갛세요");

    Scanner scanner = new Scanner(System.in);
    String name = scanner.next();
    System.out.println("당신의 이름은 " + name + "입니다.");
    String city = scanner.next();
    System.out.println("당신의 도시는" + city + "입니다.");
    int age = scanner.nextInt();
    System.out.println("당신의 나이는" + age + "입니다.");
    }
}