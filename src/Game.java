import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game implements ActionListener {
    static int 손님수;
    static int 손님만족횟수;
    static int 음식버린횟수;
    static int 게임시간;
    static int 현재매출;
    static int 목표매출;
    Boolean 일시정지;
    Menu 클릭된메뉴;

    static String[] 베이스재료이름들 = {"쌀", "면"};
    static String[] 토핑재료이름들 = {"연어", "장어", "한우", "참치"};
    static String[] 음료이름들 = {"콜라", "사이다"};
    static int 화구개수 = 2;
    static int 픽업대개수 = 4;


    GameFrame gameFrame;

    GameUser 게임유저;

    ArrayList<BaseIngredient> 베이스재료들;
    ArrayList<ToppingIngredient> 토핑재료들;
    ArrayList<BeverageIngredient> 음료들;
    ArrayList<Range> 화구들;
    ArrayList<Dispensor> 음료디스펜서;
    ArrayList<ToppigTable> 토핑대;
    ArrayList<Pickup> 픽업대들;

    Scanner 스캐너 = new Scanner(System.in);
    String 대답;

    public void createGui() {
        gameFrame = new GameFrame();

        // addActionListener
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

    public void 메뉴셋팅(){ // 버튼 - 메뉴 객체 연결 짓기 , 주방셋팅?   //// 가격 셋팅하는 부분을 따로 만들어야될듯
        // 추후 추가되는 아이템이 있다면 이름들 리스트에 이름만 추가하면 for 문을 돌면서 자동으로 추가되도록
        베이스재료들 = new ArrayList<BaseIngredient>();
        for (int i = 0; i < 베이스재료이름들.length; i++){
            베이스재료들.add(new BaseIngredient(베이스재료이름들[i], 600));
        }

        // 주방셋팅
        화구들 = new ArrayList<Range>();
        for (int i = 0; i < 화구개수; i++){
            if(i==0) 화구들.add(new Range("화구"+(i+1))); // 처음 게임 시작할 때는 화구 1개만 제공
            else 화구들.add(new Range("화구"+(i+1), 0)); // 구매 안된 상태로 만들기 위해 품질 0으로 설정
        }

        음료디스펜서 = new ArrayList<Dispensor>();
        for (int i = 0; i < 음료이름들.length; i++){
            음료디스펜서.add(new Dispensor(음료이름들[i]+"디스펜서", new BeverageIngredient(음료이름들[i], 450)));
        }

        토핑대 = new ArrayList<ToppigTable>();
        int 가격 = 700;
        for (int i = 0; i < 토핑재료이름들.length; i++){
            토핑대.add(new ToppigTable(토핑재료이름들[i]+"토핑대", new ToppingIngredient(토핑재료이름들[i], 가격)));
            가격 += 100;
        }
        // 매장 셋팅
        픽업대들 = new ArrayList<Pickup>();
        for (int i = 0; i < 픽업대개수; i++) 픽업대들.add(new Pickup());
    }

//    public void 손님입장(){ // 스레드
    public void 손님입장(int 위치, ArrayList<String> 주문메뉴){ // 스레드
//        int 위치 = 2;// 랜덤숫자
        Customer 손님 = new Customer();
        손님.기다리는시간 = 20; // 유형에 따라 달라질 수 있음
        손님.상태 = 1; // 기본 1로 시작
        // 손님 배치하기
        gameFrame.손님들[위치].setText("안녕하세요"); //// 손님유형 적기
        픽업대들.get(위치).손님배정하기(손님, 위치);

        // 랜덤으로 주문메뉴 리스트 생성 - 게임 레벨, 캐릭터 유형에 따라 달라짐
//        ArrayList<String> 주문메뉴 = new ArrayList<String>(
//        Arrays.asList("연어초밥", "장어초밥", "한우라멘"));
        //  주문메뉴 주문하기
        손님.주문하기(gameFrame, 주문메뉴, 위치); //// 위치
        픽업대들.get(위치).상태 = 1;
    }


    public void 게임시작(){ // 스레드
        createGui();
        캐릭터셋팅(); // TODO : 처음 게임 실행 시 유저 이름 입력 받기
        메뉴셋팅(); //
        클릭된메뉴 = null;
        gameFrame.프레임.setVisible(true); // 프레임 보이기 설정

        // 난이도 셋팅


        // 스레드 사용할건데 우선 테스트값으로 손님 2명 배치
        int 위치 = 2;
        ArrayList<String> 주문메뉴 = new ArrayList<String>(
        Arrays.asList("연어초밥", "장어초밥", "한우라멘"));
        손님입장(위치, 주문메뉴);

        위치 = 0;
        주문메뉴 = new ArrayList<String>(
        Arrays.asList("초밥", "참치라멘", "참치초밥"));
        손님입장(위치, 주문메뉴);
    }

    public void 게임종료(){
        gameFrame.프레임.setVisible(false);
    }

    // 클릭 컨트롤
    public void 클릭된메뉴저장(Menu 클릭된메뉴){ //// Menu ? 형변환?
        this.클릭된메뉴 = 클릭된메뉴;
//        gameFrame.클릭된메뉴.setText(클릭된메뉴.이름);
        gameFrame.set클릭된메뉴(클릭된메뉴.이름);
    }
    public void 클릭된메뉴초기화(){
        this.클릭된메뉴 = null;
//        gameFrame.클릭된메뉴.setText("");
        gameFrame.init클릭된메뉴();
    }

    public boolean 숫자이다(String str){
        boolean result = true;
        if(str == null || str.length() == 0) result = false;
        else{
            for (int i = 0; i < str.length(); i++){
                if((int)str.charAt(i) < 48  || (int)str.charAt(i) > 57) result=false;
            }
        }
        return result;
    }

    public String 숫자대답받기(int min, int max){
        String 대답 = null;
        while(true){
                System.out.print("> " );
                대답 = 스캐너.nextLine();
                if(!숫자이다(대답)){
                    System.out.println("숫자가 아닙니다. 숫자를 입력해주세요.");
                } else if(Integer.parseInt(대답) < min || max < Integer.parseInt(대답)){
                    System.out.println("범위를 벗어났습니다. 보기에 있는 숫자를 입력해주세요.");
                } else break;
            }
        return 대답;
    }

    public String 태그포맷팅(String 태그, String[] 값들){
        StringBuffer sb = new StringBuffer();
        String 값 = "";
        for (int i = 0; i < 값들.length; i++)값 += 값들[i];
        return String.valueOf(sb.append("<").append(태그).append(">").append(값).append("</").append(태그).append(">"));
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] p태그들 = new String[]{};
        String p센터 = "p align=\"center\"";
        for(int i=0; i<gameFrame.베이스재료들.length; i++){
            if(클릭된메뉴 != null) break;
            if(e.getSource().equals(gameFrame.베이스재료들[i])){
                String 베이스재료이름 = gameFrame.베이스재료들[i].getText();
                int 베이스재료가격 = 베이스재료들.get(i).가격;
                클릭된메뉴저장(new Food(베이스재료이름, 1, 베이스재료가격));
            }
        }

        for (int i = 0; i < gameFrame.화구들.length; i++){
            if(!e.getSource().equals(gameFrame.화구들[i])) continue;
            if(화구들.get(i).상태==0 && 클릭된메뉴 instanceof Food && ((Food) 클릭된메뉴).조리상태==1){ ////
                화구들.get(i).조리하기((Food)클릭된메뉴);
                // 조리중 htmlText
//               p태그들 = new String[]{
//                        태그포맷팅(p센터,  new String[]{화구들.get(i).조리음식.이름}),
//                        태그포맷팅(p센터,  new String[]{"조리중"}),
//                        태그포맷팅(p센터,  new String[]{"00:"+화구들.get(i).조리시간})};
                gameFrame.화구들[i].setText(태그포맷팅("html", p태그들));
                클릭된메뉴초기화();
//                 조리완료 htmlText - 아직 스레드 기능이 없어 일단 조리완료로 바로 변경
//                p태그들 = new String[]{
//                        태그포맷팅(p센터,  new String[]{화구들.get(i).조리음식.이름}),
//                        태그포맷팅(p센터,  new String[]{"조리완료"})};
//                gameFrame.화구들[i].setText(태그포맷팅("html", p태그들));
                gameFrame.set조리완료(화구들.get(i).조리음식.이름, i);
                // 화구버튼 비활성화
//                gameFrame.화구들[i].setEnabled(false);
            }else if(클릭된메뉴==null && 화구들.get(i).상태 > 2){
                클릭된메뉴저장(화구들.get(i).조리음식);
                화구들.get(i).조리음식초기화();
//                gameFrame.화구들[i].setText("화구"+(i+1)); /// TODO: 다른 곳에서 관리
                gameFrame.init화구(i);
                // 화구버튼 활성화
//                gameFrame.화구들[i].setEnabled(true);
            }
        }

        for (int i = 0; i < gameFrame.토핑재료들.length; i++){
            if(!e.getSource().equals(gameFrame.토핑재료들[i])) continue;
            if(토핑대.get(i).상태 ==0 && 클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태==2){
                // 토핑시작
                토핑대.get(i).조리하기((Food)클릭된메뉴);
                클릭된메뉴초기화();
                // 조리완료 htmlText - 아직 스레드 기능이 없어 일단 토핑완료로 바로 변경  
//                p태그들 = new String[]{
//                        태그포맷팅(p센터,  new String[]{토핑대.get(i).조리음식.이름}),
//                        태그포맷팅(p센터,  new String[]{"토핑완료"})};
//                gameFrame.토핑재료들[i].setText(태그포맷팅("html", p태그들));
                gameFrame.set토핑완료(토핑대.get(i).조리음식.이름, i);
                // 토핑버튼 비활성화
//                gameFrame.토핑재료들[i].setEnabled(false);
            }else if(클릭된메뉴 == null && 토핑대.get(i).상태 > 1){
                클릭된메뉴저장(토핑대.get(i).조리음식);
                토핑대.get(i).조리음식초기화();
//                gameFrame.토핑재료들[i].setText(토핑재료이름들[i]); /// TODO: 다른 곳에서 관리
                gameFrame.init토핑대(i);
                // 토핑된 음식 클릭
//                gameFrame.토핑재료들[i].setEnabled(true);
            }
        }
        for(int i=0; i<gameFrame.음료들.length; i++){
            // 현재 클릭된 메뉴가 빈상태이면 베이스 재료 선택됨
            if(!e.getSource().equals(gameFrame.음료들[i])) continue;
            if(음료디스펜서.get(i).상태==0){
                // 음료 따르기
//                음료디스펜서.get(i).음료따르기(음료들.get(i));
                String 음료이름 = gameFrame.음료들[i].getText();
                음료디스펜서.get(i).음료따르기(new Beverage(음료이름)); ////
                // 음료따르기완료 setText
//                p태그들 = new String[]{
//                        태그포맷팅(p센터,  new String[]{음료디스펜서.get(i).음료.이름}),
//                        태그포맷팅(p센터,  new String[]{"음료따르기완료"})};
//                gameFrame.음료들[i].setText(태그포맷팅("html", p태그들));
                gameFrame.set음료따르기완료(음료디스펜서.get(i).음료.이름, i);
                // 음료버튼 비활성화
            }else if(클릭된메뉴 == null && 음료디스펜서.get(i).상태 == 1) {
                // 완료된 음료 클릭하기
                클릭된메뉴저장(음료디스펜서.get(i).음료);
                음료디스펜서.get(i).음료초기화();
//                gameFrame.음료들[i].setText(음료이름들[i]); /// TODO: 다른 곳에서 관리
                gameFrame.init음료디스펜스(i);
                // 음료버튼 활성화
            }
        }
        
        for (int i = 0; i < gameFrame.주문메뉴들.length; i++){
            if(!e.getSource().equals(gameFrame.주문메뉴들[i])) continue;
            if(픽업대들.get(i).상태==0) break;
            if(클릭된메뉴 != null && 픽업대들.get(i).상태==1 &&  //////
                (클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 > 1) || 클릭된메뉴 instanceof Beverage
            ){ //
                // 서빙하기
                // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
//                게임유저.음식서빙하기(픽업대들.get(i), ((Food)클릭된메뉴)); ///// 음료일 때 또 따로 Beverage로 형변환해줘야되네
                Boolean 음식받기 = 픽업대들.get(i).음식받기(gameFrame, 클릭된메뉴); ///// 음료일 때 또 따로 Beverage로 형변환해줘야되네
                if(음식받기) {
                    gameFrame.주문메뉴들[i].setText(String.valueOf(픽업대들.get(i).손님.주문메뉴));
                    클릭된메뉴초기화();
                }
            }else if(픽업대들.get(i).상태==2){
                // 코인회수
                게임유저.코인회수하기(gameFrame,픽업대들.get(i));
                gameFrame.주문메뉴들[i].setText("주문&픽업대");
            }
        }

        if(e.getSource().equals(gameFrame.일시정지)){  // TODO: 코드정리
            this.일시정지 = true;
            gameFrame.프레임.setVisible(false);
            System.out.println("""
                    \n<일시정지>
                    1. 게임 재개
                    2. 다시 하기
                    3. 게임 종료
                    """);
            System.out.println(": 숫자를 입력해주세요.");
            대답 = 숫자대답받기(1, 3);
            switch(Integer.parseInt(대답)){
                case 1:
                    gameFrame.프레임.setVisible(true);
                    this.일시정지 = false;
                    break;
                case 2:
//                    gameFrame.프레임.setVisible(true);
                    게임시작();
                    this.일시정지 = false;
                    break;
                case 3:
                    System.out.println("게임을 종료합니다.");
                    System.exit(0);
                    break;
            }
            // 화면 비활성화되고, 스레드들 일시중지 일시정지창 띄우기(이건 콘솔에다가 출력하자..)
                // - 게임재개 (일시정지창 내리고 화면 활성화. 스레드 재개)
                // - 다시시작 (스레드 없애고 다시 생성하여 시작)
                // - 게임종료(알림창 띄운 후 예 선택 시 게임스레드 종료)
        }else if(e.getSource().equals(gameFrame.쓰레기통)){
            if(클릭된메뉴!=null){ // 음식 버리기
                System.out.println("쓰레기통에 " + 클릭된메뉴.이름 + " 버리기");
//                게임유저.음식버리기(); ////
                음식버린횟수 += 1;  // 메소드로 관리하고 싶은데 어디에 위치시켜야 적절할까?
                클릭된메뉴초기화();
            }
        }

        System.out.println("클릭된메뉴: " +클릭된메뉴);
    }
}






// 미션 클래스

