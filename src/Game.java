import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener {
    int 손님수;
    int 손님만족횟수;
    int 음식버린횟수;
    int 게임시간;
    int 현재매출;
    int 목표매출;
    Boolean 일시정지;
    Object 클릭된메뉴;
//    String 클릭된메뉴;
//    ClickMenu 클릭된메뉴;
//    JButton 클릭된메뉴;

    GameFrame gameFrame;

    GameUser 게임유저;

    ArrayList<KitchenIngredient> 베이스재료들;
    ArrayList<KitchenIngredient> 토핑재료들;
    ArrayList<KitchenIngredient> 음료들;
    ArrayList<Food> 음식들;
    ArrayList<KitchenEquipment> 화구들;
    ArrayList<KitchenEquipment> 음료디스펜서;
    ArrayList<KitchenEquipment> 토핑대;
    ArrayList<Pickup> 픽업대들;

    public void createGui() {
        // addActionListener
        gameFrame = new GameFrame();

        for(int i=0; i<gameFrame.주문메뉴들.length; i++) gameFrame.주문메뉴들[i].addActionListener(this);
        for(int i=0; i<gameFrame.음료들.length; i++)gameFrame.음료들[i].addActionListener(this);
        for(int i=0; i<gameFrame.화구들.length; i++)gameFrame.화구들[i].addActionListener(this);
        for(int i=0; i<gameFrame.토핑재료들.length; i++)gameFrame.토핑재료들[i].addActionListener(this);
        for(int i=0; i<gameFrame.베이스재료들.length; i++)gameFrame.베이스재료들[i].addActionListener(this);
        gameFrame.일시정지.addActionListener(this);
        gameFrame.쓰레기통.addActionListener(this);
    }

    public void 캐릭터셋팅(){
        게임유저 = new GameUser();
        게임유저.이름 = "유저1";
        게임유저.게임점수 = 0;
        게임유저.보유코인수 = 1000;
        게임유저.게임레벨 = 1;
    }

    public void 아이템셋팅(){}

    public void 메뉴셋팅(){ // 버튼 - 메뉴 객체 연결 짓기 , 주방셋팅?
        KitchenIngredient 쌀 = new KitchenIngredient("쌀", 200, "베이스");
        KitchenIngredient 면 = new KitchenIngredient("면", 200, "베이스");
        베이스재료들 = new ArrayList<KitchenIngredient>();
        베이스재료들.add(쌀);
        베이스재료들.add(면);

        KitchenIngredient 연어 = new KitchenIngredient("연어", 200, "토핑");
        KitchenIngredient 장어 = new KitchenIngredient("장어", 200, "토핑");
        KitchenIngredient 한우 = new KitchenIngredient("한우", 200, "토핑");
        토핑재료들 = new ArrayList<KitchenIngredient>();
        토핑재료들.add(연어);
        토핑재료들.add(장어);
        토핑재료들.add(한우);

        KitchenIngredient 사이다 = new KitchenIngredient("사이다", 100, "음료");
        KitchenIngredient 콜라 = new KitchenIngredient("콜라", 100, "음료");
        음료들 = new ArrayList<KitchenIngredient>();
        음료들.add(사이다);
        음료들.add(콜라);

        음식들 = new ArrayList<Food>();


        // 주방셋팅
        화구들 = new ArrayList<KitchenEquipment>();
        화구들.add(new KitchenEquipment("화구1", "화구"));
        화구들.add(new KitchenEquipment("화구2", "화구"));

        음료디스펜서 = new ArrayList<KitchenEquipment>();
        음료디스펜서.add(new KitchenEquipment("사이다디스펜서", "음료디스펜서"));
        음료디스펜서.add(new KitchenEquipment("콜라디스펜서", "음료디스펜서"));


        토핑대 = new ArrayList<KitchenEquipment>();
        토핑대.add(new KitchenEquipment("연어토핑대", "토핑대"));
        토핑대.add(new KitchenEquipment("장어토핑대","토핑대"));
        토핑대.add(new KitchenEquipment("한우토핑대", "토핑대"));

        // 매장 셋팅
        픽업대들 = new ArrayList<Pickup>();
        픽업대들.add(new Pickup());
        픽업대들.add(new Pickup());
        픽업대들.add(new Pickup());
        픽업대들.add(new Pickup());

    }

    public void 게임시작(){ // 스레드
        createGui();
        캐릭터셋팅();
        메뉴셋팅();
//        this.클릭된메뉴 = new ClickMenu();
        클릭된메뉴 = null;
        // 난이도 셋팅


        // test 손님배치하여 주문하기
        // 손님 등장
//        Customer 손님1 = new Customer();
//        손님1.기다리는시간 = 20;
//        손님1.위치 = 2; // 랜덤으로 지정시키기
//        // 손님 배치하기
//        gameFrame.손님들[손님1.위치].setText("안녕하세요"); //
//        Pickup 픽업대1 = new Pickup(손님1);
//        손님1.주문하기(gameFrame);

        // 조리하기


    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(this.클릭된메뉴);
//        System.out.println(this.클릭된메뉴.getText());
//        System.out.println(this.클릭된메뉴.getText().equals(""));
        for(int i=0; i<gameFrame.베이스재료들.length; i++){
            // 현재 클릭된 메뉴가 빈상태이면 베이스 재료 선택됨
//            if(!클릭된메뉴.getText().equals("")) break;
//            if(this.클릭된메뉴.클릭된상태 == true) break;
            if(클릭된메뉴 != null) break;
            if(e.getSource().equals(gameFrame.베이스재료들[i])){
//                클릭된메뉴 = gameFrame.베이스재료들[i];
//                System.out.println("> " +( gameFrame.베이스재료들[i].getParent()==gameFrame.베이스재료패널))
                클릭된메뉴 = new Food(gameFrame.베이스재료들[i].getText(), 1);
                gameFrame.클릭된메뉴.setText(gameFrame.베이스재료들[i].getText());
//                음식들.add(new Food(gameFrame.베이스재료들[i].getText(), 1)); ///
            }
        }

        for (int i = 0; i < gameFrame.화구들.length; i++){
            if(!e.getSource().equals(gameFrame.화구들[i])) continue;
            if(화구들.get(i).상태==0 && 클릭된메뉴 instanceof Food && ((Food) 클릭된메뉴).조리상태==1){
                화구들.get(i).조리하기((Food)클릭된메뉴);
                클릭된메뉴 = null;
                gameFrame.클릭된메뉴.setText("");
                // 화구버튼 비활성화
//                gameFrame.화구들[i].setEnabled(false);
            }else if(클릭된메뉴==null && 화구들.get(i).상태 > 1){
                클릭된메뉴 = 화구들.get(i).조리음식;
                gameFrame.클릭된메뉴.setText(화구들.get(i).조리음식.이름);
                화구들.get(i).조리음식 = null;
                // 화구버튼 활성화
//                gameFrame.화구들[i].setEnabled(true);
            }
//            if(화구들.get(i).상태==0 && (클릭된메뉴.getParent() == gameFrame.베이스재료패널)){   // 		- 현재 클릭된 메뉴가 음식.상태1이고 빈 화구이면 조리시작
//                // 조리시작
//                화구들.get(i).조리하기(new Food(클릭된메뉴.getText(), 2));
//                클릭된메뉴 = new JButton();
//            }
//            else if(클릭된메뉴.getText().equals("") && 화구들.get(i).상태 > 1){ //2,3
//                클릭된메뉴 = gameFrame.화구들[i];
//            }
        }

        for (int i = 0; i < gameFrame.토핑재료들.length; i++){
            if(!e.getSource().equals(gameFrame.토핑재료들[i])) continue;
            if(토핑대.get(i).상태 ==0 && 클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태==2){
                // 토핑시작
                토핑대.get(i).조리하기((Food)클릭된메뉴);
                클릭된메뉴 = null;
                gameFrame.클릭된메뉴.setText("");
                // 토핑버튼 비활성화
//                gameFrame.토핑재료들[i].setEnabled(false);
            }else if(클릭된메뉴 == null && 토핑대.get(i).상태 > 1){
                클릭된메뉴 = 토핑대.get(i).조리음식;
                gameFrame.클릭된메뉴.setText(토핑대.get(i).조리음식.이름);
                토핑대.get(i).조리음식 = null;
                // 토핑된 음식 클릭
//                gameFrame.토핑재료들[i].setEnabled(true);
            }
        }
        for(int i=0; i<gameFrame.음료들.length; i++){
            // 현재 클릭된 메뉴가 빈상태이면 베이스 재료 선택됨
            if(!e.getSource().equals(gameFrame.음료들[i])) continue;
            if(음료디스펜서.get(i).상태==0){
                // 음료 따르기
                음료디스펜서.get(i).음료따르기(음료들.get(i));
                // 음료버튼 비활성화
            }else if(클릭된메뉴 == null && 음료디스펜서.get(i).상태 == 1) {
                // 완료된 음료 클릭하기
                클릭된메뉴=음료디스펜서.get(i).음료;
                gameFrame.클릭된메뉴.setText(음료디스펜서.get(i).음료.이름);
                음료디스펜서.get(i).음료 = null;
                // 음료버튼 활성화
            }
        }
        
        for (int i = 0; i < gameFrame.주문메뉴들.length; i++){
            if(!e.getSource().equals(gameFrame.주문메뉴들[i])) continue;
            if(픽업대들.get(i).상태==0) break;
            if(픽업대들.get(i).상태==1){ //
                // 서빙하기
                // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크


            }else if(픽업대들.get(i).상태==2){
                // 코인회수
                게임유저.코인회수하기(픽업대들.get(i));
            }
        }

        if(e.getSource().equals(gameFrame.일시정지)){
            System.out.println("일시정지");
            // 화면 비활성화 
        }else if(e.getSource().equals(gameFrame.쓰레기통)){
            System.out.println("쓰레기통");
            if(클릭된메뉴!=null){ // 음식 버리기
                음식버린횟수 += 1;
                클릭된메뉴 = null;
                gameFrame.클릭된메뉴.setText("");
            }
        }

        System.out.println(클릭된메뉴);
//        System.out.println(this.클릭된메뉴.getText());
//        System.out.println(this.클릭된메뉴.getText());
//        System.out.println(this.클릭된메뉴 == gameFrame.베이스재료들[1]);
//        System.out.println("this.클릭된메뉴.getParent() == gameFrame.베이스재료패널: " +(this.클릭된메뉴.getParent() == gameFrame.베이스재료패널));

    }
}


// 미션 클래스

