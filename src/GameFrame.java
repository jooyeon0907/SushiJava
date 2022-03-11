import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.GregorianCalendar;


public class GameFrame implements ActionListener {
    //setText///////////////////////////////////////////////////////////////
    public String 태그포맷팅(String 태그, String[] 값들){
        StringBuffer sb = new StringBuffer();
        String 값 = "";
        for (int i = 0; i < 값들.length; i++)값 += 값들[i];
        return String.valueOf(sb.append("<").append(태그).append(">").append(값).append("</").append(태그).append(">"));
     }
     public String[] 여러개태그포맷팅(String 태그, String[] 값들){
        String[] result = new String[값들.length];
        for (int i = 0; i < 값들.length; i++) result[i] = 태그포맷팅(태그, new String[]{값들[i]});
        return result;
     }
    String p센터 = "p align=\"center\"";
    public void init레인지(int 위치){레인지들[위치].setText("레인지"+(위치+1));}

    public void set조리중(String 이름, int 위치, int 조리시간){
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "조리중", String.valueOf(조리시간)});
        레인지들[위치].setText(태그포맷팅("html", p태그들));
    }
    public void set조리완료(String 이름, int 위치, int 조리시간){ // 오버쿡 되고 있는 상태
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "조리완료", String.valueOf(조리시간)});
        레인지들[위치].setText(태그포맷팅("html", p태그들));
    }
    public void set오버쿡(String 이름, int 위치, int 조리시간){
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{"탄 "+이름, "오버쿡"});
        레인지들[위치].setText(태그포맷팅("html", p태그들));
    }
    public void set조리완료(String 이름, int 위치){
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "조리완료"});
        레인지들[위치].setText(태그포맷팅("html", p태그들));
    }

    public void init토핑대(int 위치){토핑재료들[위치].setText(Game.토핑재료이름들[위치]);}
    public void set토핑중(String 이름, int 위치, int 토핑시간){
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "토핑중", String.valueOf(토핑시간)});
        토핑재료들[위치].setText(태그포맷팅("html", p태그들));
    }
    public void set토핑완료(String 이름, int 위치){
       String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "토핑완료"});
       토핑재료들[위치].setText(태그포맷팅("html", p태그들));
    }


    public void init음료디스펜스(int 위치){음료들[위치].setText(Game.음료이름들[위치]);}
    public void set음료따르는중(String 이름, int 위치, int 음료따르는시간){
        String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "음료따르는중", String.valueOf(음료따르는시간)});
        음료들[위치].setText(태그포맷팅("html", p태그들));
    }
    public void set음료따르기완료(String 이름, int 위치){
       String[] p태그들 = 여러개태그포맷팅(p센터, new String[]{이름, "음료따르기완료"});
       음료들[위치].setText(태그포맷팅("html", p태그들));
    }

    public void set아이템개수(int 개수, int 위치){
        게임아이템들[위치].setText(Game.게임아이템이름들[위치]+" ("+개수+"개)");
    }

    public void set현재매출(int 결제금액){현재매출값.setText(String.valueOf(결제금액));}
    public void set클릭된메뉴(String 이름){클릭된메뉴.setText(이름);}
    public void init클릭된메뉴(){클릭된메뉴.setText("");}

    public void 픽업대주문메뉴받기(int 위치, String 주문메뉴){
        System.out.println("위치 : " + 위치+", 주문메뉴 : " + 주문메뉴);
        주문메뉴들[위치].setText(주문메뉴);}
    public void 픽업대주문완료(int 결제금액, int 위치){
        주문메뉴들[위치].setText(Integer.toString(결제금액));
        손님수값.setText(String.valueOf(Game.손님수));
        손님들[위치].setText("");
        주문시간들[위치].setText("");
    }
    public void 코인회수하기(int 위치){
        set현재매출(Game.현재매출);
        주문메뉴들[위치].setText("주문&픽업대");
    }

    public void 손님주문하기(int 위치, String 주문메뉴, int 기다리는시간){
        주문메뉴들[위치].setText(주문메뉴); // 위치 변경
        주문시간들[위치].setText(String.valueOf(기다리는시간)); //  위치 변경
    }
    public void init기다리는시간(int 위치, int 기다리는시간){주문시간들[위치].setText(String.valueOf(기다리는시간));}

    //end setText/////////////////////////////////////////////////////////////


    JFrame 프레임 = new JFrame();
    JPanel 베이스패널;

    JPanel 정보패널;
        JLabel 타이머; JLabel 타이머값;
        JLabel 목표매출;
        JPanel 매출패널;
            JLabel 현재매출값; JLabel 목표매출값;
        JLabel 손님수; JLabel 손님수값;
        JLabel 클릭된메뉴이름; JLabel 클릭된메뉴;
    JPanel 손님들패널;
        JLabel[] 주문시간들;
        JLabel[] 손님들;
    JPanel 픽업대패널;
        JButton[] 주문메뉴들;
    JPanel 키친패널;
        JPanel 게임아이템패널;
            JButton[] 게임아이템들;
        JPanel 음료패널;
            JButton[] 음료들;
        JPanel 레인지패널;
            JButton[] 레인지들;
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
            타이머 = new JLabel("타이머: "); 타이머값 = new JLabel("2분 20초");
            목표매출 = new JLabel("달성 매출 / 목표 매출: ");
            매출패널 = new JPanel();
                현재매출값 = new JLabel("0"); 목표매출값 = new JLabel("/ 30000");
            손님수 = new JLabel("손님수: "); 손님수값 = new JLabel("0");
            클릭된메뉴이름 = new JLabel("클릭된 메뉴: "); 클릭된메뉴 = new JLabel("");

        손님들패널 = new JPanel();
            주문시간들 = new JLabel[Game.픽업대개수];
            for (int i = 0; i < Game.픽업대개수; i++) 주문시간들[i] = new JLabel("주문시간", JLabel.CENTER);
            손님들 = new JLabel[Game.픽업대개수];
            for (int i = 0; i < Game.픽업대개수; i++) 손님들[i] = new JLabel("손님"+(i+1), JLabel.CENTER);

        픽업대패널 = new JPanel();
            주문메뉴들 = new JButton[Game.픽업대개수];
            for (int i = 0; i < Game.픽업대개수; i++) 주문메뉴들[i] = new JButton("주문&픽업대");

        키친패널 = new JPanel();
            게임아이템패널 = new JPanel();
                게임아이템들 = new JButton[Game.게임아이템이름들.length];
                for (int i = 0; i < Game.게임아이템이름들.length; i++) 게임아이템들[i] = new JButton(Game.게임아이템이름들[i]);

            음료패널 = new JPanel();
                음료들 = new JButton[Game.음료이름들.length];
                for (int i = 0; i < Game.음료이름들.length; i++) 음료들[i] = new JButton(Game.음료이름들[i]);

            레인지패널 = new JPanel();
                레인지들 = new JButton[Game.레인지개수];
                for (int i = 0; i < Game.레인지개수; i++) 레인지들[i] = new JButton("레인지"+(i+1));

            토핑패널 = new JPanel();
                토핑재료들 = new JButton[Game.토핑재료이름들.length];
                for (int i = 0; i < Game.토핑재료이름들.length; i++) 토핑재료들[i] = new JButton(Game.토핑재료이름들[i]);

            베이스재료패널 = new JPanel();
                베이스재료들 = new JButton[Game.베이스재료이름들.length];
                for (int i = 0; i < Game.베이스재료이름들.length; i++) 베이스재료들[i] = new JButton(Game.베이스재료이름들[i]);

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
//        프레임.setVisible(true); // 프레임 보이기 설정
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
        정보패널.add(타이머); 정보패널.add(타이머값);
        정보패널.add(목표매출);
        정보패널.add(매출패널);
        매출패널.setLayout(null);
//        매출패널.setLayout(new GridLayout());
//        매출패널.setBackground(Color.darkGray);
        매출패널.add(현재매출값); 매출패널.add(목표매출값);
        현재매출값.setBounds(0, y, 50, h);
        목표매출값.setBounds(35, y, 1000, h);
        정보패널.add(손님수); 정보패널.add(손님수값);
        정보패널.add(클릭된메뉴이름); 정보패널.add(클릭된메뉴);

        y+=h; h=150;
        베이스패널.add(손님들패널);
        손님들패널.setBounds(0, y, 1000, h);
        손님들패널.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 20));

        손님들패널.setLayout(new GridLayout(2,1));
        for(int i=0; i<손님들.length; i++){
            손님들패널.add(주문시간들[i]);
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

        키친패널.add(게임아이템패널);
        게임아이템패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        게임아이템패널.setLayout(new GridLayout(4,1));
        for(int i=0; i<게임아이템들.length; i++){
            게임아이템패널.add(게임아이템들[i]);
        }

        키친패널.add(음료패널);
        음료패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        음료패널.setLayout(new GridLayout(2,1));
        for(int i=0; i<음료들.length; i++){
            음료패널.add(음료들[i]);
        }

        키친패널.add(레인지패널);
        레인지패널.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 10));
        레인지패널.setLayout(new GridLayout());
        for(int i=0; i<레인지들.length; i++){
            레인지패널.add(레인지들[i]);
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


    }

    public void 버튼리스너등록(Game game) {// addActionListener
        for(int i=0; i<주문메뉴들.length; i++) 주문메뉴들[i].addActionListener(game);
        for(int i=0; i<음료들.length; i++)음료들[i].addActionListener(game);
        for(int i=0; i<레인지들.length; i++)레인지들[i].addActionListener(game);
        for(int i=0; i<토핑재료들.length; i++)토핑재료들[i].addActionListener(game);
        for(int i=0; i<베이스재료들.length; i++)베이스재료들[i].addActionListener(game);
        for(int i=0; i<게임아이템들.length; i++)게임아이템들[i].addActionListener(game);
        일시정지.addActionListener(game);
        쓰레기통.addActionListener(game);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
