import javax.swing.*;
import java.awt.*;

public class RadioButtonEx extends JFrame {
   
   public RadioButtonEx() {
      // Set the title of the window
      setTitle("라디오버튼 만들기 예제");
      
      // Specify what happens when the user closes the window
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Get the content pane of the JFrame and set its layout to FlowLayout
      Container c = getContentPane();
      c.setLayout(new FlowLayout());

      // Create a ButtonGroup to group the radio buttons
      ButtonGroup g = new ButtonGroup();

      // Load an image icon for the cherry radio button
      ImageIcon img = new ImageIcon("images/체리.jpg");
      
      // Create radio buttons with labels
      JRadioButton apple = new JRadioButton("사과");
      JRadioButton pear = new JRadioButton("배", true); // Set "배" as the default selected button
      JRadioButton cherry = new JRadioButton(img);
      
      // Add the radio buttons to the ButtonGroup
      g.add(apple);
      g.add(pear);
      g.add(cherry);

      // Add the radio buttons to the content pane
      c.add(apple);
      c.add(pear);
      c.add(cherry);

      // Set the size of the JFrame
      setSize(250, 150);
      
      // Make the JFrame visible
      setVisible(true);
   }
   
   public static void main(String [] args) {
      // Create an instance of RadioButtonEx, which will display the window
      new RadioButtonEx();
   }
}
