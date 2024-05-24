import java. awt.*;
import java.awt.event.*;
import javax. swing.*;

public class IndepClassListener extends Frame {
public IndepClassListener() {}
setTitle(title:"Action 이벤트 리스너 예제");
setDefaultcloseOperation (JFrame. EXIT_ON_CLOSE)
Container c = getContentPane();
c. setLayout (new FlowLayout());
Button btn = new Button (text: "Action");
btn. addActionListener(new MyActionListener())
c.add (btn);
setSize(width: 250, height: 120);
setVisible(b:true);