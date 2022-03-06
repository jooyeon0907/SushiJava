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
    ArrayList<ToppigTable> 토핑대들;
    ArrayList<Pickup> 픽업대들;

    GameConsole 게임콘솔 = new GameConsole();

    Scanner 스캐너 = new Scanner(System.in);
    String 대답;

    public void 버튼리스너등록() {
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

        토핑대들 = new ArrayList<ToppigTable>();
        int 가격 = 700;
        for (int i = 0; i < 토핑재료이름들.length; i++){
            토핑대들.add(new ToppigTable(토핑재료이름들[i]+"토핑대", new ToppingIngredient(토핑재료이름들[i], 가격)));
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
        //  주문메뉴 주문하기
        손님.주문하기(gameFrame, 주문메뉴, 위치); //// 위치
        픽업대들.get(위치).상태 = 1;
    }

    //콘솔화면 출력///////////////////////////////////////////////////////
    public void run스시자바(){
        캐릭터셋팅(); // TODO : 처음 게임 실행 시 유저 이름 입력 받기
        메뉴셋팅(); //
        게임콘솔.메인화면출력(this);
    }
//    public void 메인화면출력(){
//        String 대답;
//        System.out.println("""
//                    \n<메인화면>
//                    1. 게임 시작
//                    2. 나의 정보
//                    3. 상점
//                    4. 종료
//                    """);
//        System.out.println(": 숫자를 입력해주세요.");
//        대답 = 숫자대답받기(1, 4);
//        switch(대답){
//            case "1":
//                게임시작();
//                break;
//            case "2":
//                나의정보출력();
//                break;
//            case "3":
//                break;
//            case "4":
//                System.out.println("스시자바를 종료합니다.");
//                System.exit(0);
//                break;
//        }//end switch
//    }

//    public void 나의정보출력(){
//        String 대답;
//        System.out.println("""
//\n<나의 정보>
//1. 유저 정보
//2. 재료 정보
//3. 장비 정보
//4. 보유 아이템
//5. 메인화면으로 돌아가기
//                    """);
//        System.out.println(": 숫자를 입력해주세요.");
//        대답 = 숫자대답받기(1, 5);
//        switch(대답){
//            case "1":
//                유저정보출력();
//                break;
//            case "2":
//                재료정보출력();
//                break;
//            case "3":
//                장비정보출력();
//                break;
//            case "4":
//                보유아이템출력();
//            case "5":
//                게임콘솔.메인화면출력(this);
//                break;
//        }//end switch
//    }
//    public void 메인화면으로돌아가기(){
//        System.out.println("\n1. 메인화면으로 돌아가기");
//        String 대답 = 숫자대답받기(1, 1);
//        if(대답.equals("1")) 게임콘솔.메인화면출력(this);
//    }
//    public void 유저정보출력(){
//        System.out.println("\n<유저 정보>");
//        System.out.println("이름 : " + 게임유저.이름);
//        System.out.println("게임레벨 : " + 게임유저.게임레벨);
//        System.out.println("보유코인수 : " + 게임유저.보유코인수);
//        메인화면으로돌아가기();
//    }
//    public void 재료정보출력(){
//        System.out.println("\n<재료 정보>");
//        System.out.println("--베이스 재료----------------------");
//        System.out.println("이름\t\t\t품질\t\t\t가격");
//        System.out.println("---------------------------------");
//        for (int i = 0; i < 베이스재료들.size(); i++){
//            BaseIngredient 베이스재료 = 베이스재료들.get(i);
//            System.out.println(베이스재료.이름 + "\t\t\t" +  베이스재료.품질  +"\t\t\t" + 베이스재료.가격);
//        }
//
//        System.out.println("\n--토핑 재료----------------------");
//        System.out.println("이름\t\t\t품질\t\t\t가격");
//        System.out.println("--------------------------------");
//        for (int i = 0; i < 토핑대들.size(); i++){
//            ToppingIngredient 토핑재료 = 토핑대들.get(i).토핑재료;
//            System.out.println(토핑재료.이름 + "\t\t\t" +  토핑재료.품질  +"\t\t\t" + 토핑재료.가격);
//        }
//
//        System.out.println("\n--음료 재료----------------------");
//        System.out.println("이름\t\t\t품질\t\t\t가격");
//        System.out.println("--------------------------------");
//        for (int i = 0; i < 음료디스펜서.size(); i++){
//            BeverageIngredient 음료재료 = 음료디스펜서.get(i).음료재료;
//            System.out.println(음료재료.이름 + "\t\t\t" +  음료재료.품질  +"\t\t\t" + 음료재료.가격);
//        }
//        메인화면으로돌아가기();
//    }
//
//    public void 장비정보출력(){
//        System.out.println("\n<장비 정보>");
//        System.out.println("--화구--------------------------------");
//        System.out.println("이름\t\t\t품질\t\t\t조리시간");
//        System.out.println("-------------------------------------");
//        for (int i = 0; i < 화구들.size(); i++){
//            Range 화구 = 화구들.get(i);
//            System.out.println(화구.이름 + "\t\t\t" +  화구.품질  +"\t\t\t" + 화구.조리시간);
//        }
//
//        System.out.println();
//        System.out.println("--토핑대------------------------------");
//        System.out.println("이름\t\t\t품질\t\t\t토핑시간");
//        System.out.println("-------------------------------------");
//        for (int i = 0; i < 토핑대들.size(); i++){
//            ToppigTable 토핑대 = 토핑대들.get(i);
//            System.out.println(토핑대.이름 + "\t\t\t" +  토핑대.품질  +"\t\t\t" + 토핑대.조리시간);
//        }
//
//        System.out.println();
//        System.out.println("--음료디스펜서-------------------------");
//        System.out.println("이름\t\t\t품질\t\t\t시간");
//        System.out.println("-------------------------------------");
//        for (int i = 0; i < 음료디스펜서.size(); i++){
//            Dispensor 디스펜서 = 음료디스펜서.get(i);
//            System.out.println(디스펜서.이름 + "\t\t\t" +  디스펜서.품질  +"\t\t\t" + 디스펜서.조리시간);
//        }
//        메인화면으로돌아가기();
//    }
//    public void 보유아이템출력(){
//
//        메인화면으로돌아가기();
//    }
//
//
//
//    public void 일시정지화면(){
//        this.일시정지 = true;
//        gameFrame.프레임.setVisible(false);
//            System.out.println("""
//                    \n<일시정지>
//                    1. 게임 재개
//                    2. 다시 하기
//                    3. 게임 종료
//                    """);
//            System.out.println(": 숫자를 입력해주세요.");
//            대답 = 숫자대답받기(1, 3);
//            switch(Integer.parseInt(대답)){
//                case 1:
//                    gameFrame.프레임.setVisible(true);
//                    this.일시정지 = false;
//                    break;
//                case 2:
//                    게임시작();
//                    this.일시정지 = false;
//                    break;
//                case 3:
//                    System.out.println("게임을 종료합니다.");
//                    게임콘솔.메인화면출력(this);
//                    break;
//            }
//    }
    //end 콘솔화면 출력///////////////////////////////////////////////////////


    public void 게임시작(){ // 스레드
        버튼리스너등록();
        클릭된메뉴 = null;
        gameFrame.프레임.setVisible(true); // 프레임 보이기 설정

        // 난이도 셋팅


        // 스레드 사용할건데 우선 테스트값으로 손님 2명 배치
        int 위치 = 2;
        // 랜덤으로 주문메뉴 리스트 생성 - 게임 레벨, 캐릭터 유형에 따라 달라짐
        ArrayList<String> 주문메뉴 = new ArrayList<String>(
        Arrays.asList("연어초밥", "장어초밥", "한우라멘"));
        손님입장(위치, 주문메뉴);

        위치 = 0;
        주문메뉴 = new ArrayList<String>(
        Arrays.asList("초밥", "참치라멘", "참치초밥"));
        손님입장(위치, 주문메뉴);

        위치 = 1;
        주문메뉴 = new ArrayList<String>(
        Arrays.asList("장어라멘", "사이다"));
        손님입장(위치, 주문메뉴);
    }

    public void 게임종료(){
        gameFrame.프레임.setVisible(false);
    }

    // 클릭 컨트롤
    public void 클릭된메뉴저장(Menu 클릭된메뉴){ //// Menu ? 형변환?
        this.클릭된메뉴 = 클릭된메뉴;
        gameFrame.set클릭된메뉴(클릭된메뉴.이름);
    }
    public void 클릭된메뉴초기화(){
        this.클릭된메뉴 = null;
        gameFrame.init클릭된메뉴();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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
                클릭된메뉴초기화();
//                 조리완료 htmlText - 아직 스레드 기능이 없어 일단 조리완료로 바로 변경
                gameFrame.set조리완료(화구들.get(i).조리음식.이름, i);
                // 화구버튼 비활성화
//                gameFrame.화구들[i].setEnabled(false);
            }else if(클릭된메뉴==null && 화구들.get(i).상태 > 2){
                클릭된메뉴저장(화구들.get(i).조리음식);
                화구들.get(i).조리음식초기화();
                gameFrame.init화구(i);
                // 화구버튼 활성화
//                gameFrame.화구들[i].setEnabled(true);
            }
        }

        for (int i = 0; i < gameFrame.토핑재료들.length; i++){
            if(!e.getSource().equals(gameFrame.토핑재료들[i])) continue;
            if(토핑대들.get(i).상태 ==0 && 클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태==2){
                // 토핑시작
                토핑대들.get(i).조리하기((Food)클릭된메뉴);
                클릭된메뉴초기화();
                // 조리완료 htmlText - 아직 스레드 기능이 없어 일단 토핑완료로 바로 변경
                gameFrame.set토핑완료(토핑대들.get(i).조리음식.이름, i);
                // 토핑버튼 비활성화
//                gameFrame.토핑재료들[i].setEnabled(false);
            }else if(클릭된메뉴 == null && 토핑대들.get(i).상태 > 1){
                클릭된메뉴저장(토핑대들.get(i).조리음식);
                토핑대들.get(i).조리음식초기화();
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
                gameFrame.set음료따르기완료(음료디스펜서.get(i).음료.이름, i);
                // 음료버튼 비활성화
            }else if(클릭된메뉴 == null && 음료디스펜서.get(i).상태 == 1) {
                // 완료된 음료 클릭하기
                클릭된메뉴저장(음료디스펜서.get(i).음료);
                음료디스펜서.get(i).음료초기화();
                gameFrame.init음료디스펜스(i);
                // 음료버튼 활성화
            }
        }
        
        for (int i = 0; i < gameFrame.주문메뉴들.length; i++){
            if(!e.getSource().equals(gameFrame.주문메뉴들[i])) continue;
            if(픽업대들.get(i).상태==0) break;
            Pickup 픽업대 = 픽업대들.get(i);
            if(클릭된메뉴 != null && 픽업대.상태==1 &&  ////// 픽업대 주문중 상태이고 클릭된 메뉴가 음료이거나 조리된 음식일 때
                (클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 > 1) || 클릭된메뉴 instanceof Beverage){
                Boolean 음식받기 = 픽업대.음식받기(gameFrame, 클릭된메뉴);  // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
                if(음식받기) {
                    gameFrame.픽업대주문메뉴받기(i, String.valueOf(픽업대.손님.주문메뉴));
                    클릭된메뉴초기화();
                    // 주문메뉴 모두 서빙했다면
                    if(픽업대.손님.주문메뉴.size()< 1) 픽업대.주문완료(gameFrame);
                }
            }else if(픽업대.상태==2){
                게임유저.코인회수하기(gameFrame, 픽업대);
                gameFrame.코인회수하기(i);
            }
        }

        if(e.getSource().equals(gameFrame.일시정지)) 게임콘솔.일시정지(this);
        else if(e.getSource().equals(gameFrame.쓰레기통)){
            if(클릭된메뉴!=null){ // 음식 버리기
                System.out.println("쓰레기통에 " + 클릭된메뉴.이름 + " 버리기");
                게임유저.음식버리기(); ////
//                음식버린횟수 += 1;  // 메소드로 관리하고 싶은데 어디에 위치시켜야 적절할까?
                클릭된메뉴초기화();
            }
        }

        System.out.println("클릭된메뉴: " +클릭된메뉴);
    }
}






// 미션 클래스

