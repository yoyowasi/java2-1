package chap04;

public class Ex4_04 {
    String title;
    String author;

    public Ex4_04(String t){
        title = t; author = "작자미상";
    }

    public Ex4_04(String t,String a){
        title = t; author = a;
    }

    public static void main(String [] args) {
        Ex4_04 littlePrince = new Ex4_04("어린왕자", "생텍쥐페리");
        Ex4_04 loveStory = new Ex4_04("춘향전"); 
        System.out.println(littlePrince.title + " " + littlePrince.author);
          System.out.println(loveStory.title + " " + loveStory.author);
     }

    
}