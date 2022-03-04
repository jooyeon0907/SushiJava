import javax.swing.*;
import java.awt.*;

public class GameForm{
    private JPanel jPanel1;
    private JPanel panelInfo;
    private JPanel panelCustomer;
    private JPanel panelPickup;
    private JPanel panelKitchen;
    private JPanel 음료;
    private JPanel 토핑;
    private JPanel 재료;
    private JButton 픽업1;
    private JButton 픽업2;
    private JButton 픽업3;
    private JButton 픽업4;
    private JPanel 손님1;
    private JLabel 손님이미지1;
    private JPanel 손님2;
    private JLabel 손님이미지2;
    private JLabel 주문1;
    private JLabel 주문2;
    private JLabel 주문3;
    private JPanel 손님3;
    private JLabel 손님이미지3;
    private JPanel 손님4;
    private JLabel 주문4;
    private JLabel 손님이미지4;
    private JPanel 화구;
    private JButton 콜라;
    private JButton 사이다;
    private JButton 쌀;
    private JButton 면;
    private JButton 화구1Button;
    private JButton 연어Button;

    public static void main(String[] args){
        JFrame frame = new JFrame("Sushi Java");
        frame.setContentPane(new GameForm().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setResizable(false); // 창 크기 조절 비활성화
        frame.setLocationRelativeTo(null); // 중간에 창 띄우기 ?
        frame.setSize(1000, 700); // 프레임 크기 지정
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
