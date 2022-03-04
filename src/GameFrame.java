import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class GameFrame implements ActionListener {
    JButton btn1 = new JButton("1번 버튼");
    JButton btn2 = new JButton("2번 버튼");
    JButton btn3 = new JButton("3번 버튼");
    JButton btn4 = new JButton("4번 버튼");
    JButton btn5 = new JButton("5번 버튼");

    JFrame 프레임 = new JFrame();
    JPanel 베이스패널;

    JPanel 정보패널;
        JLabel 타이머;
        JLabel 목표매출;
        JLabel 손님수;
        JLabel 클릭된메뉴이름;
        JLabel 클릭된메뉴;
    JPanel 손님들패널;
        JLabel[] 주문시간들;
        JLabel[] 손님들;
    JPanel 픽업대패널;
        JButton[] 주문메뉴들;
    JPanel 키친패널;
        JPanel 음료패널;
            JButton[] 음료들;
        JPanel 화구패널;
            JButton[] 화구들;
        JPanel 토핑패널;
            JButton[] 토핑재료들;
        JPanel 베이스재료패널;
            JButton[] 베이스재료들;
    JPanel 하단패널;
        JButton 일시정지;
        JButton 쓰레기통;
        
    public GameFrame(){
        프레임 = new JFrame();
        베이스패널 = new JPanel();

        정보패널 = new JPanel();
            타이머 = new JLabel("타이머");
            목표매출 = new JLabel("달성 매출 / 목표 매출");
            손님수 = new JLabel("손님수");
            클릭된메뉴이름 = new JLabel("클릭된 메뉴 : ");
            클릭된메뉴 = new JLabel("");

        손님들패널 = new JPanel();
//            주문메뉴들 = new JLabel[]{new JLabel("주문메뉴", JLabel.CENTER), new JLabel("주문메뉴", JLabel.CENTER), new JLabel("주문메뉴", JLabel.CENTER), new JLabel("주문메뉴", JLabel.CENTER)};
            주문시간들 = new JLabel[]{new JLabel("주문시간", JLabel.CENTER), new JLabel("주문시간", JLabel.CENTER), new JLabel("주문시간", JLabel.CENTER), new JLabel("주문시간", JLabel.CENTER)};
            손님들 = new JLabel[]{new JLabel("손님1", JLabel.CENTER), new JLabel("손님2", JLabel.CENTER), new JLabel("손님3", JLabel.CENTER), new JLabel("손님4", JLabel.CENTER)};


        픽업대패널 = new JPanel();
//            픽업들 = new JButton[]{new JButton("픽업1"), new JButton("픽업2"), new JButton("픽업3"), new JButton("픽업4")};
            주문메뉴들 = new JButton[]{new JButton("주문메뉴들"), new JButton("주문메뉴들"), new JButton("주문메뉴들"), new JButton("주문메뉴들")};

        키친패널 = new JPanel();
            음료패널 = new JPanel();
                음료들 = new JButton[]{new JButton("콜라"), new JButton("사이다")};

            화구패널 = new JPanel();
                화구들 = new JButton[]{new JButton("화구1"), new JButton("화구2")}; //  조리중 문구, 조리 시간

            토핑패널 = new JPanel();
                토핑재료들 = new JButton[]{new JButton("연어"), new JButton("장어"), new JButton("한우")};

            베이스재료패널 = new JPanel();
                베이스재료들 = new JButton[]{new JButton("쌀"), new JButton("면")};

        하단패널 = new JPanel();
            일시정지 = new JButton("일시정지");
            쓰레기통 = new JButton("쓰레기통");
        //////////////
        // 생성자에 안넣고 사용하면 오류나는데 왜 그러는지 파악해보기
        프레임.setTitle("Sushi Java");
        프레임.setSize(1000, 700); // 프레임 크기 지정
        프레임.setResizable(false); // 창 크기 조절 비활성화
        프레임.setLocationRelativeTo(null); // 중간에 창 띄우기 ?
        프레임.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼 설정
        프레임.setVisible(true); // 프레임 보이기 설정
        프레임.setLayout(null);

        //////////////
        int x=0,y=0,h=0;
        베이스패널.setSize(1000, 700);
        프레임.getContentPane().add(베이스패널);
        베이스패널.setLayout(null);
//        베이스패널.setLayout((new GridLayout(4, 4)));

        y+=h; h=100;
//        정보패널.setLayout(null);
        정보패널.setLayout(new GridLayout());
        정보패널.setBounds(0, y, 1000, h);
//        정보패널.setBackground(SystemColor.red);
        베이스패널.add(정보패널);
        정보패널.add(타이머);
        정보패널.add(목표매출);
        정보패널.add(손님수);
        정보패널.add(클릭된메뉴이름);
        정보패널.add(클릭된메뉴);

        y+=h; h=150;
        베이스패널.add(손님들패널);
        손님들패널.setBounds(0, y, 1000, h);
        손님들패널.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 20));

        손님들패널.setLayout(new GridLayout(2,1));
        for(int i=0; i<손님들.length; i++){
            손님들패널.add(주문시간들[i]);
//            손님들패널.add(주문메뉴들[i]);
//            손님들패널.add(손님들[i]);
        }
        for(int i=0; i<손님들.length; i++){
            손님들패널.add(손님들[i]);
        }
        y+=h; h=150;
        베이스패널.add(픽업대패널);
        픽업대패널.setBounds(0, y, 1000, h);
        픽업대패널.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 20));
        픽업대패널.setLayout(new GridLayout());
        for(int i=0; i<주문메뉴들.length; i++){
            픽업대패널.add(주문메뉴들[i]);
        }


        y+=h; h=200;
        베이스패널.add(키친패널);
        키친패널.setBounds(0, y, 1000, h);
        키친패널.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 20));
        키친패널.setLayout(new GridLayout());

        키친패널.add(음료패널);
        음료패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        음료패널.setLayout(new GridLayout(2,1));
        for(int i=0; i<음료들.length; i++){
            음료패널.add(음료들[i]);
        }

        키친패널.add(화구패널);
        화구패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        화구패널.setLayout(new GridLayout());
        for(int i=0; i<화구들.length; i++){
            화구패널.add(화구들[i]);
        }

        키친패널.add(토핑패널);
        토핑패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        토핑패널.setLayout(new GridLayout(2,3));
        for(int i=0; i<토핑재료들.length; i++){
            토핑패널.add(토핑재료들[i]);
        }

        키친패널.add(베이스재료패널);
        베이스재료패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        베이스재료패널.setLayout(new GridLayout());
        for(int i=0; i<베이스재료들.length; i++){
            베이스재료패널.add(베이스재료들[i]);
        }

        y+=h; h=50;
        베이스패널.add(하단패널);
//        하단패널.setLayout(null);
        하단패널.setLayout(new GridLayout());
        하단패널.setBounds(0, y, 1000, h);
//        정보패널.setBackground(SystemColor.red);
        하단패널.add(일시정지);
        하단패널.add(쓰레기통);


//        Container container = 프레임.getContentPane();
//        container.add(btn1, BorderLayout.NORTH);
//        container.add(btn2, BorderLayout.EAST);
//        container.add(btn3, BorderLayout.WEST);
//        container.add(btn4, BorderLayout.SOUTH);
//        container.add(btn5, BorderLayout.CENTER);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
