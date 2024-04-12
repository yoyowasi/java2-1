package chap04;

class Calc{
    public static int abs(int a) {return a>0}
}


class Ex04_11 {
    public static void main(String[] args) {
        System.out.println(Calc.abs(-5));
        System.out.println(Calc.max(10, 8));
        System.out.println(Calc.min(-3 , -8));
    }

    
}