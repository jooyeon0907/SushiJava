import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Game implements ActionListener {
    static int 게임레벨;
    //// 게임 한판 할 때 필요한 변수
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
    static String[] 게임아이템이름들 = {"시간정지", "캔디", "만능요리", "초특급요리"};
    static int 레인지개수 = 2;
    static int 픽업대개수 = 4;
    static int 재료최고품질 = 4;
    static int 장비최고품질 = 3;
    static HashMap<String, Integer> 메뉴가격표;
    ArrayList<Customer> 손님유형들;


    static GameFrame gameFrame; // TODO : 전역변수로 수정함 -> 맞게 바꾸기 (GameFrame gameFrame 쓴 곳 찾아서 전역변수로 불러오기)

    GameUser 게임유저;

    ArrayList<BaseIngredient> 베이스재료들;
//    ArrayList<ToppingIngredient> 토핑재료들;
//    ArrayList<BeverageIngredient> 음료들;
    ArrayList<Range> 레인지들;
    ArrayList<Dispensor> 음료디스펜서;
    ArrayList<ToppingTable> 토핑대들;
    ArrayList<Pickup> 픽업대들;
    ArrayList<GameItem> 게임아이템들;

    GameConsole 게임콘솔 = new GameConsole(this);

    Scanner 스캐너 = new Scanner(System.in);
    String 대답;




    public void 게임유저셋팅(String 유저이름){
        게임유저 = new GameUser();
        게임유저.이름 = 유저이름;
        게임유저.보유코인수 = 20000;
        게임유저.게임레벨 = 1;

        System.out.println(유저이름 + "님이 등록되었습니다.");
    }

    public void 손님유형셋팅(){
        손님유형들 = new ArrayList<>();
//        손님유형들.add()
    }

    public void 아이템셋팅(){ //  {"시간정지", "캔디", "만능요리", "초특급요리"};
        게임아이템들 = new ArrayList<GameItem>();
        게임아이템들.add(new TimeStopItem(게임아이템이름들[0], 700));
        게임아이템들.add(new CandyItem(게임아이템이름들[1], 600));
        게임아이템들.add(new AllPurposeItem(게임아이템이름들[2], 1000));
        게임아이템들.add(new SuperSpecialItem(게임아이템이름들[3], 1400));
        for (int i = 0; i < 게임아이템들.size(); i++){
            gameFrame.set아이템개수(게임아이템들.get(i).개수, i);
            gameFrame.게임아이템들[i].setEnabled(true);
        }
    }


    public void 메뉴셋팅(){  //// 가격 셋팅하는 부분을 따로 만들어야될듯   // 메뉴셋팅 -> 버튼셋팅 ?
        // for문 사용이유 : 추후 추가되는 아이템이 있다면 이름들 리스트에 이름만 추가하면 for 문을 돌면서 자동으로 추가되도록 함
        베이스재료들 = new ArrayList<BaseIngredient>();
        for (int i = 0; i < 베이스재료이름들.length; i++){
            베이스재료들.add(new BaseIngredient(베이스재료이름들[i]));
        }

        // 주방셋팅
        레인지들 = new ArrayList<Range>();
        for (int i = 0; i < 레인지개수; i++){
            if(i==0) 레인지들.add(new Range("레인지"+(i+1))); // 처음 게임 시작할 때는 레인지 1개만 제공
            else 레인지들.add(new Range("레인지"+(i+1), 0)); // 구매 안된 상태로 만들기 위해 품질 0으로 설정
        }

        음료디스펜서 = new ArrayList<Dispensor>();
        for (int i = 0; i < 음료이름들.length; i++){
            음료디스펜서.add(new Dispensor(음료이름들[i]+"디스펜서", new BeverageIngredient(음료이름들[i])));
        }

        // TODO : 게임레벨에 따라 토핑재료 이름들 개수 제한 시키기
        토핑대들 = new ArrayList<ToppingTable>();
        for (int i = 0; i < 토핑재료이름들.length; i++){
            토핑대들.add(new ToppingTable(토핑재료이름들[i]+"토핑대", new ToppingIngredient(토핑재료이름들[i])));
        }
        // 매장 셋팅
        픽업대들 = new ArrayList<Pickup>();
        for (int i = 0; i < 픽업대개수; i++) 픽업대들.add(new Pickup());

    }

    public void 가격셋팅(){
        //쌀
        베이스재료들.get(0).재료가격표셋팅(new int[]{600, 900, 1400, 2100}, new int[]{0, 900, 1400, 2100});
        //면
        베이스재료들.get(1).재료가격표셋팅(new int[]{500, 800, 1300, 2000}, new int[]{0, 800, 1300, 2000});
        // 연어
        토핑대들.get(0).토핑재료.재료가격표셋팅(new int[]{500, 800, 1300, 200}, new int[]{0, 800, 1300, 200});
        // 장어
        토핑대들.get(1).토핑재료.재료가격표셋팅(new int[]{800, 1100, 1600, 2300}, new int[]{0, 1100, 1600, 2300});
        // 한우
        토핑대들.get(2).토핑재료.재료가격표셋팅(new int[]{900, 1200, 1700, 2400}, new int[]{0, 1200, 1700, 2400});
        // 참치
        토핑대들.get(3).토핑재료.재료가격표셋팅(new int[]{1000, 1300, 1800, 2500}, new int[]{0, 1300, 1800, 2500});
        // 콜라
        음료디스펜서.get(0).음료재료.재료가격표셋팅(new int[]{450, 750, 1250, 1950}, new int[]{450, 750, 1250, 1950});
        // 사이다
        음료디스펜서.get(1).음료재료.재료가격표셋팅(new int[]{450, 750, 1250, 1950}, new int[]{450, 750, 1250, 1950});

        // 레인지
        for (int i = 0; i < 레인지개수; i++){
            레인지들.get(i).장비가격표셋팅(new int[]{10, 7, 5}, new int[]{2000, 4000, 7000});
        }
        // 토핑대
        for (int i = 0; i < 토핑재료이름들.length; i++){
            토핑대들.get(i).장비가격표셋팅(new int[]{10, 7, 5}, new int[]{0, 3800, 6800});
        }
        // 음료디스펜서
        for (int i = 0; i < 음료이름들.length; i++){
            음료디스펜서.get(i).장비가격표셋팅(new int[]{10, 7, 5}, new int[]{0, 3700, 5700});
        }

    }


    public void 메뉴가격표셋팅(){
        // TODO: 재료 업그레이드 시 메뉴가격표도 업데이트해줘야함
        // TODO : 게임레벨에 따라 토핑재료 이름들 개수 제한 시키기

        메뉴가격표 = new HashMap<>();

        // 베이스재료만
        for (int i = 0; i < 베이스재료들.size(); i++){
            String 메뉴이름 = 베이스재료들.get(i).조리된이름();
            int 메뉴가격 = 베이스재료들.get(i).가격;
            메뉴가격표.put(메뉴이름, 메뉴가격);
        }
        // 베이스재료+ 토핑재료
        for (int i = 0; i < 베이스재료들.size(); i++){
          for (int j = 0; j < 토핑대들.size(); j++) {
              String 메뉴이름 = 토핑대들.get(j).토핑재료.이름 + 베이스재료들.get(i).조리된이름();
              int 메뉴가격 = 토핑대들.get(j).토핑재료.가격 + 베이스재료들.get(i).가격;
              메뉴가격표.put(메뉴이름, 메뉴가격);
          }
        }
        // 음료
        for (int i = 0; i < 음료디스펜서.size(); i++){
            String 메뉴이름 = 음료디스펜서.get(i).음료재료.이름;
            int 메뉴가격 = 음료디스펜서.get(i).음료재료.가격;
            메뉴가격표.put(메뉴이름, 메뉴가격);
        }

        System.out.println(메뉴가격표);
        // 메뉴 이름을 키로 이용하여 메뉴 가격 가져오기
        System.out.println(메뉴가격표.get("연어초밥"));
//        Set<String> keySet = 메뉴가격표.keySet();
//        for(String key: keySet){
//            System.out.println(key  + ", " + 메뉴가격표.get(key));
//        }


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
    
    public void run스시자바(){
        gameFrame = new GameFrame();
        gameFrame.버튼리스너등록(this);
//        게임유저셋팅(게임콘솔.게임유저등록());
        게임유저셋팅("주연쓰");
        아이템셋팅();
        메뉴셋팅(); //
        가격셋팅();
        메뉴가격표셋팅();
//        게임콘솔.재료업그레이드_화면출력();
//        게임콘솔.장비업그레이드_화면출력();
        게임콘솔.메인화면출력();
//        게임시작();
    }

    public void 아이템장착(){
        // 게임유저.보유아이템
        // 부스터 아이템
            //
        System.out.println("부스터 기능 뺄까... ");
    }


    public void 게임시작(){ // 스레드
        클릭된메뉴 = null;
        gameFrame.프레임.setVisible(true); // 프레임 보이기 설정

        // 난이도 셋팅

        // 스레드 사용할건데 우선 테스트값으로 손님 2명 배치


        int 위치 = 2;
        // 랜덤으로 주문메뉴 리스트 생성 - 게임 레벨, 캐릭터 유형에 따라 달라짐
        ArrayList<String> 주문메뉴 = new ArrayList<String>( // 랜덤으로 값 넣은거임 -> 주문하기에서 주문메뉴가 정해짐
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
//                클릭된메뉴저장(new Food(베이스재료이름, 1, 베이스재료가격));
                클릭된메뉴저장(new Food(베이스재료이름, 1));
            }
        }

        for (int i = 0; i < gameFrame.레인지들.length; i++){
            if(!e.getSource().equals(gameFrame.레인지들[i])) continue;
            if(레인지들.get(i).상태==0 && 클릭된메뉴 instanceof Food && ((Food) 클릭된메뉴).조리상태==1){ ////
                레인지들.get(i).조리하기((Food)클릭된메뉴);
                클릭된메뉴초기화();
//                 조리완료 htmlText - 아직 스레드 기능이 없어 일단 조리완료로 바로 변경
                gameFrame.set조리완료(레인지들.get(i).조리음식.이름, i);
                // 레인지버튼 비활성화
//                gameFrame.레인지들[i].setEnabled(false);
            }else if(클릭된메뉴==null && 레인지들.get(i).상태 > 2){
                클릭된메뉴저장(레인지들.get(i).조리음식);
                레인지들.get(i).조리음식초기화();
                gameFrame.init레인지(i);
                // 레인지버튼 활성화
//                gameFrame.레인지들[i].setEnabled(true);
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
//            if(클릭된메뉴 != null && 픽업대.상태==1 &&  ////// 픽업대 주문중 상태이고 클릭된 메뉴가 음료이거나 조리된 음식일 때
//                (클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 > 1) || 클릭된메뉴 instanceof Beverage){
           if(클릭된메뉴 != null && 픽업대.상태==1){ // TODO
//               if(클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 < 2) continue;
               Boolean 음식받기 = 픽업대.음식받기(gameFrame, 클릭된메뉴);  // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
                if(음식받기) {
                    gameFrame.픽업대주문메뉴받기(i, String.valueOf(픽업대.손님.주문메뉴));
                    // 주문메뉴 모두 서빙했다면
                    if(픽업대.손님.주문메뉴.size()< 1) 픽업대.주문완료(gameFrame);
                    if(클릭된메뉴.이름.equals("만능요리")){ // TODO : 버튼 원래색을 되돌리기 -> 코드 위치 수정
                        for (int j = 0; j < gameFrame.주문메뉴들.length; j++){
                            gameFrame.주문메뉴들[j].setBackground(new Color(238, 238, 238)); // TODO : 기본 색상으로 어떻게 되돌리지?
                        }
                    }
                    클릭된메뉴초기화();
                }
            }else if(픽업대.상태==2){
                게임유저.코인회수하기(gameFrame, 픽업대);
                gameFrame.코인회수하기(i);
            }
        }

        for (int i = 0; i < gameFrame.게임아이템들.length; i++){ // TODO: 아이템버튼들 비활성화 -> 아이템 사용완료시 다시 활성화
            if(!e.getSource().equals(gameFrame.게임아이템들[i])) continue;
            GameItem 게임아이템 = 게임아이템들.get(i);
            if(게임아이템.이름.equals("만능요리")){
                클릭된메뉴저장(((AllPurposeItem)게임아이템).만능메뉴);
//                게임아이템.아이템사용();
                // 픽업대 버튼 클릭 유도하도록 눈에 띄게 색깔 바꾸기
                for (int j = 0; j < gameFrame.주문메뉴들.length; j++){
                    gameFrame.주문메뉴들[j].setBackground(Color.blue);
                }
                // 키친패널 버튼 비활성화
//                for (int 키친패널위치 =0; 키친패널위치 < gameFrame.키친패널.getComponentCount(); 키친패널위치++){
//                    JPanel 패널 = (JPanel) gameFrame.키친패널.getComponent(키친패널위치);
//                    for (int 패널위치 = 0; 패널위치 < 패널.getComponentCount(); 패널위치++){
//                        패널.getComponent(패널위치).setEnabled(false);
//                    }
//                }
            }else if(게임아이템.이름.equals("초특급요리")){ // TODO : 수정 -> 마지막 픽업대도 수정이되네
               for (int 픽업대위치= 0; 픽업대위치 < 픽업대들.size(); 픽업대위치++){
                   Pickup 픽업대 = 픽업대들.get(픽업대위치);
                   if(픽업대.상태 ==1 ) {
                       클릭된메뉴저장(((SuperSpecialItem)게임아이템).만능메뉴);
                       Boolean 음식받기 = 픽업대.음식받기(gameFrame, 클릭된메뉴);  // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
                       if (음식받기) {
                           gameFrame.픽업대주문메뉴받기(픽업대.위치, String.valueOf(픽업대.손님.주문메뉴));
                           if (픽업대.손님.주문메뉴.size() < 1) 픽업대.주문완료(gameFrame);
                       }
                       클릭된메뉴초기화();
                   }
               }
            }else if(게임아이템.이름.equals("캔디")){
                클릭된메뉴저장(((CandyItem)게임아이템).캔디);
            } else{
                게임아이템.아이템사용();
            }
            gameFrame.set아이템개수(게임아이템.개수, i);
            if(게임아이템.개수 < 1 ) gameFrame.게임아이템들[i].setEnabled(false);
        }

        if(e.getSource().equals(gameFrame.일시정지)) 게임콘솔.일시정지();
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

