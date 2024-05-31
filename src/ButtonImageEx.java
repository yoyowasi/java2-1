import javax.swing.*;
import java.awt.*;

public class ButtonImageEx extends JFrame {
   public ButtonImageEx() {
      setTitle("이미지 버튼 예제");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setLayout(new FlowLayout());

      ImageIcon normalIcon = new ImageIcon("image/스크린샷 2024-05-17 141131.png");
      ImageIcon rolloverIcon = new ImageIcon("image/스크린샷 2024-05-30 113058.png");
      ImageIcon pressedIcon = new ImageIcon("iamge/스크린샷 2024-05-17 141131.png");      
      
      JButton btn = new JButton("call~~", normalIcon);
      btn.setPressedIcon(pressedIcon);
      btn.setRolloverIcon(rolloverIcon);
      c.add(btn);
      
      setSize(250,150);
      setVisible(true);
   }
   
   public static void main(String [] args) {
      new ButtonImageEx();
   }
}