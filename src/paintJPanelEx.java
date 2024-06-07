import javax.swing.*;
import java.awt.*;

public class paintJPanelEx extends JFrame {
    public paintJPanelEx() {
        setTitle("JPanel의 paintComponent() 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(250, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new paintJPanelEx();
    }
}

class MyPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 이 부분이 가장 먼저 호출되어야 함. 이전에 그려진 것들을 지움.

        // 파란색 선택
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, 50, 50); // 파란색 사각형

        // 문자열 그리기
        g.setColor(new Color(255, 0, 0)); // 빨간색
        g.drawString("자바가 정말 재미있다~", 30, 30);

        // 폰트 설정
        g.setFont(new Font("Arial", Font.ITALIC, 30));

        // 타원 그리기
        g.setColor(Color.BLACK);
        g.drawOval(100, 10, 50, 50); // 검은색 타원

        // 사각형 그리기
        g.setColor(Color.GREEN);
        g.drawRect(10, 70, 100, 80); // 초록색 사각형

        // 둥근 모서리 사각형 그리기
        g.setColor(Color.ORANGE);
        g.drawRoundRect(120, 70, 100, 80, 40, 60); // 주황색 둥근 모서리 사각형
    }
}
