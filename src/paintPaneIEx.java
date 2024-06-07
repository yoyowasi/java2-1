import javax.swing.*;
import java.awt.*;

public class paintPaneIEx extends JFrame {
    public paintPaneIEx() {
        setTitle("JPanel의 paintComponent() 예제"); // JFrame의 제목을 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 버튼 클릭 시 애플리케이션 종료
        setContentPane(new MyPanel()); // JFrame의 콘텐츠 팬을 MyPanel 인스턴스로 설정
        setSize(250, 500); // JFrame의 크기를 너비 250, 높이 200으로 설정
        setVisible(true); // JFrame을 화면에 보이도록 설정
    }

    public static void main(String[] args) {
        new paintPaneIEx();
    }
}

class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 여기에 그래픽을 그리는 코드를 추가
        g.setColor(Color.RED);
        g.fillRect(50, 50, 150, 100); // 예시: 빨간색 사각형 그리기
    }
}
