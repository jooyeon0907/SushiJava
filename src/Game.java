import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Game implements ActionListener {
    int 게임레벨 = 15;
    // 게임 레벨에 따라 갯수가 셋팅됨
    int 베이스재료갯수;
    int 토핑재료갯수;
    static int 주문메뉴최대갯수;

    //// 게임 한판 할 때 필요한 변수
    static int 손님수;
    static int 음식버린횟수;
    static int 게임시간;
    static int 현재매출;
    static int 목표매출;
    Boolean 일시정지;
    Menu 클릭된메뉴;

    String[] 베이스재료이름들 = {"쌀", "면"};
    String[] 토핑재료이름들 = {"연어", "장어", "한우", "참치"};
    String[] 음료이름들 = {"콜라", "사이다"};
    String[] 게임아이템이름들 = {"시간정지", "캔디", "만능요리", "초특급요리"};
    int 레인지개수 = 2;
    int 픽업대개수 = 4;
    int 재료최고품질 = 4; // TODO : KitchenIngredient 에 설정?
    int 장비최고품질 = 3; // TODO : KitchenEquipment 에 설정?
    static HashMap<String, Integer> 메뉴판;
    static ArrayList<String> 메뉴이름들;
//    ArrayList<Customer> 손님유형들; // TODO 삭제?
    GameMission 게임미션;
    GameConsole 게임콘솔;
    static GameFrame gameFrame;

    GameUser 게임유저;

    ArrayList<BaseIngredient> 베이스재료들;
    ArrayList<ToppingTable> 토핑대들;
    ArrayList<Pickup> 픽업대들;
    ArrayList<Range> 레인지들;
    ArrayList<Dispensor> 음료디스펜서;

    public void 게임유저셋팅(String 유저이름){
        게임유저 = new GameUser(유저이름, 20000);
        System.out.println(유저이름 + "님이 등록되었습니다.");
    }

//    public void 손님유형셋팅(){ // TODO 삭제
//        손님유형들 = new ArrayList<>();
//        손님유형들.add(new RegualrCustomer());
//        손님유형들.add(new ImpatientCustomer());
//        손님유형들.add(new GourmentCustomer());
//    }

    public void 아이템셋팅(){ //  {"시간정지", "캔디", "만능요리", "초특급요리"}; // run스시자바에서 실행
//        게임아이템들 = new ArrayList<GameItem>();
        게임유저.게임아이템들.add(new TimeStopItem(게임아이템이름들[0], 700));
        게임유저.게임아이템들.add(new CandyItem(게임아이템이름들[1], 600));
        게임유저.게임아이템들.add(new AllPurposeItem(게임아이템이름들[2], 1000));
        게임유저.게임아이템들.add(new SuperSpecialItem(게임아이템이름들[3], 1400));
        for (int i = 0; i < 게임유저.게임아이템들.size(); i++){
            gameFrame.set아이템개수(게임유저.게임아이템들.get(i).개수, i);
            gameFrame.게임아이템들[i].setEnabled(true);
        }
    }


    public void 주방셋팅(){  //// 가격 셋팅하는 부분을 따로 만들어야될듯   // 메뉴셋팅 -> 버튼셋팅 ?
        // for문 사용이유 : 추후 추가되는 아이템이 있다면 이름들 리스트에 이름만 추가하면 for 문을 돌면서 자동으로 추가되도록 함
        베이스재료들 = new ArrayList<BaseIngredient>();
        for (int i = 0; i < 베이스재료이름들.length; i++){
            베이스재료들.add(new BaseIngredient(베이스재료이름들[i]));
        }

        레인지들 = new ArrayList<Range>();
        for (int i = 0; i < 레인지개수; i++){
            if(i==0) 레인지들.add(new Range("레인지"+(i+1))); // 처음 게임 시작할 때는 레인지 1개만 제공
            else 레인지들.add(new Range("레인지"+(i+1), 0)); // 구매 안된 상태로 만들기 위해 품질 0으로 설정
        }

        음료디스펜서 = new ArrayList<>();
        for (int i = 0; i < 음료이름들.length; i++){
            음료디스펜서.add(new Dispensor(음료이름들[i]+"디스펜서", new BeverageIngredient(음료이름들[i])));
        }

        토핑대들 = new ArrayList<ToppingTable>();
        for (int i = 0; i < 토핑재료이름들.length; i++){
            토핑대들.add(new ToppingTable(토핑재료이름들[i]+"토핑대", new ToppingIngredient(토핑재료이름들[i])));
        }
        // 매장 셋팅
        픽업대들 = new ArrayList<Pickup>();
        for (int i = 0; i < 픽업대개수; i++) 픽업대들.add(new Pickup());

    }

    public void 가격셋팅(){ // TODO 리팩토링 -> 생각해보기
        //쌀
        베이스재료들.get(0).재료가격표셋팅(new int[]{600, 900, 1400, 2100}, new int[]{0, 900, 1400, 2100});
        //면
        베이스재료들.get(1).재료가격표셋팅(new int[]{500, 800, 1300, 2000}, new int[]{0, 800, 1300, 2000});
        // 연어
        토핑대들.get(0).토핑재료.재료가격표셋팅(new int[]{500, 800, 1300, 2000}, new int[]{0, 800, 1300, 2000});
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


    public void 메뉴판셋팅(){
        // 재료 업그레이드 시 메뉴판도 업데이트해줘야함 ->  게임 시작할 때마다 업그레이드되도록 위치시킴.
        // 게임레벨에 따라 토핑재료 이름들 개수 제한 시키기
        // 게임레벨셋팅이 먼저 실행 (레벨에 따라 재료 갯수가 다르게 셋팅되고 그에 따라 판매하는 메뉴 갯수도 달라지므로)
        메뉴판 = new HashMap<>();

        // 베이스재료만
        for (int i = 0; i < 베이스재료갯수; i++){
            String 메뉴이름 = 베이스재료들.get(i).조리된이름();
            int 메뉴가격 = 베이스재료들.get(i).가격;
            메뉴판.put(메뉴이름, 메뉴가격);
        }
        // 베이스재료+ 토핑재료
        for (int i = 0; i < 베이스재료갯수; i++){
          for (int j = 0; j < 토핑재료갯수; j++) {
              String 메뉴이름 = 토핑대들.get(j).토핑재료.이름 + 베이스재료들.get(i).조리된이름();
              int 메뉴가격 = 토핑대들.get(j).토핑재료.가격 + 베이스재료들.get(i).가격;
              메뉴판.put(메뉴이름, 메뉴가격);
          }
        }
        // 음료
        for (int i = 0; i < 음료디스펜서.size(); i++){
            String 메뉴이름 = 음료디스펜서.get(i).음료재료.이름;
            int 메뉴가격 = 음료디스펜서.get(i).음료재료.가격;
            메뉴판.put(메뉴이름, 메뉴가격);
        }

//        System.out.println(메뉴판);
        Iterator<String> keys = 메뉴판.keySet().iterator();
        메뉴이름들 = new ArrayList<>(); // 메뉴이름 값들을 리스트에 모아서 주문가능한 메뉴들을 알 수 있음 -> 그 중에서 랜덤으로 고를 수 있도록
        while(keys.hasNext())메뉴이름들.add(keys.next());
//        System.out.println(">>>> " +메뉴이름들);

    }

    public void 게임레벨셋팅(){ // max level = 30  // TODO: 레벨업 기능 추가되면 거기서도 호출해야됨
        // 레벨에 따라 재료개수, 주문메뉴최대갯수가 다르게 셋팅된다.
        // 레벨에 따라 등장하는 손님유형수가 달라짐.
        if (게임레벨<5) 베이스재료갯수 = 1;
        else 베이스재료갯수 = 2;

        // 레벨에 따른 토핑 갯수 셋팅 max=4
        if (게임레벨<2) 토핑재료갯수 = 1;
        else if(게임레벨<10) 토핑재료갯수 = 2;
        else if(게임레벨<15) 토핑재료갯수 = 3;
        else 토핑재료갯수 = 4;

        // 레벨에 따른 주문메뉴최대 갯수 셋팅
        if (게임레벨<6) 주문메뉴최대갯수 = 2;
        else if(게임레벨<21) 주문메뉴최대갯수 = 3;
        else 주문메뉴최대갯수 = 4;

        // 레벨에 따른 손님유형 등장 설정
        // 레벨 5이하는 일반손님만
        // 레벨 10이하는 일반손님, 바쁜손님
        // 레벨 11부터는 일반손님, 바쁜손님, 대식가손님

        // 레벨에 따른 손님 빈도수
        // TODO : 손님스레드 생성 속도 조졸
    }


//    public void 게임미션셋팅(){ // 스레드 기능 넣을 때 개발해야될 듯
     /*
     			§ 목표 금액 달성하기
			§ 기분 나쁜 손님  X
				□  시간 안에 음식을 받지 못한 손님이 1명이라도 나오면 게임 즉시 종료
			§ 만족스러운 식사 n회
				□ 만족스러운 식사를 한 손님이 제시된 횟수 이상으로 나와야함
					® 만족스러운 식사 기준 -> n초 안에 음식 서빙 (기본 기다리는 시간/2 이상?)
			§ 요리 태우기 X
				□ 한 번이라도 요리를 태우게 되면 게임 즉시 종료
			§ 쓰레기 통 X
				□ 한 번이라도 음식을 버리게 되면 게임 즉시 종료
			§ 손님 n명이상 응대
제시된 사람 수 이상으로 음식 서빙 해야됨
      */


        // 기분 나쁜 손님 X
        // 손님.상태 =  3 이되면 게임이 종료됨

        // 만족스러운 식사 n 회
        // 게임제한시간 끝나고나서 만족스러운 식사 횟수가 n회 이상되는지 체크하여 게임 미션 성공/실패 판단

        // 요리 태우기 x
        // 음식.조리상태 = 0 이되면 게임이 실패되고 종료됨

        // 쓰레기통 X
        // 쓰레기통에 버리면 게임이 실패되고 종료됨 .

        // 손님 n명 이상 응대
        // 게임 제한 시간 끝나고 나서 손님수 횟수가 n회 이상 되는지 체크하여 게임 미션 성공/실패 판단함.
//   }

    public void run스시자바(){
        System.out.println("스시자바를 시작합니다.\n\n\n");
        게임레벨셋팅();
        게임미션 = new GameMission();
        gameFrame = new GameFrame(this);
        gameFrame.버튼리스너등록(this);
        게임콘솔 = new GameConsole(this);
        게임유저셋팅(게임콘솔.게임유저등록화면());
//        게임유저셋팅("주연쓰");
        아이템셋팅();
        주방셋팅(); //
        가격셋팅();
        게임콘솔.메인화면출력();
//        게임시작();
    }

    // TODO : 시연할 때 보여줄 시연용셋팅 함수 만들기
    public void 게임시작(){ // 스레드
        System.out.println("게임시작!");
        메뉴판셋팅(); // TODO : 레벨업 기능 추가하면 거기로 옮기기
//        게임미션.게임미션셋팅(this.게임레벨);
        클릭된메뉴 = null;
        gameFrame.초기화셋팅();
        gameFrame.프레임.setVisible(true); // 프레임 보이기 설정

        // 게임미션.게임시간 타이머 시작

        //

        // 스레드 사용할건데 우선 테스트값으로 손님 3명 배치
        int 위치 = 2;
        // TODO 쓰레드 : 랜덤으로 주문메뉴 리스트 생성 - 게임 레벨, 캐릭터 유형에 따라 달라짐
        픽업대들.get(위치).손님배정하기("일반손님", 위치);
        위치 = 0;
        픽업대들.get(위치).손님배정하기("바쁜손님", 위치);
        위치 = 1;
//        손님입장("대식가손님", 위치);
        픽업대들.get(위치).손님배정하기("대식가손님", 위치);


        // TODO 쓰레드 : 손님.기다리는시간 < 0 되면 손님 나감
            // 손님패널, 픽업대패널 초기화
    }

    public void 게임종료(boolean 시간만료, boolean 게임미션통과){
        gameFrame.프레임.setVisible(false);
        // 게임 결과 창 보여주기
        // TODO : 게임나가기 버튼눌러서 종료된 것인지, 게임시간이 만료되거나 게임 통과가 되어 종료된 것인지 판단하기
        //  -> 게임시간으로 판단? or boolean 인자
        if(시간만료){
            // 정산
            // 현재매출을 유저.코인수에 더해줌
            게임유저.보유코인수 += this.현재매출;
            // 게임 미션 통과 여부 확인
            if(게임미션통과){
                this.게임레벨 += 1;
            }
        }


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
    public void actionPerformed(ActionEvent e) { // 게임 버튼들 컨트롤
        for(int i=0; i<gameFrame.베이스재료들.length; i++){
            if(클릭된메뉴 != null) break;
            if(e.getSource().equals(gameFrame.베이스재료들[i])){
                String 베이스재료이름 = gameFrame.베이스재료들[i].getText();
                int 베이스재료가격 = 베이스재료들.get(i).가격;
                클릭된메뉴저장(new Food(베이스재료이름, 1, 베이스재료가격));
            }
        }

        for (int i = 0; i < gameFrame.레인지들.length; i++){
            if(!e.getSource().equals(gameFrame.레인지들[i])) continue;
            if(레인지들.get(i).상태==0 && 클릭된메뉴 instanceof Food && ((Food) 클릭된메뉴).조리상태==1){ ////
                레인지들.get(i).조리하기((Food)클릭된메뉴, i);
                클릭된메뉴초기화();
//                 조리완료 htmlText - 아직 스레드 기능이 없어 일단 조리완료로 바로 변경
                // 레인지버튼 비활성화
//                gameFrame.레인지들[i].setEnabled(false);
            }else if(클릭된메뉴==null && 레인지들.get(i).상태 > 2){
                클릭된메뉴저장(레인지들.get(i).조리음식);
                레인지들.get(i).장비초기화(i);
                // 레인지버튼 활성화
//                gameFrame.레인지들[i].setEnabled(true);
            }
        }

        for (int i = 0; i < gameFrame.토핑재료들.length; i++){
            if(!e.getSource().equals(gameFrame.토핑재료들[i])) continue;
            if(토핑대들.get(i).상태 ==0 && 클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태==2){
                // 토핑시작
                토핑대들.get(i).토핑하기((Food)클릭된메뉴, i);
                클릭된메뉴초기화();
                // 조리완료 htmlText - 아직 스레드 기능이 없어 일단 토핑완료로 바로 변경
                // 토핑버튼 비활성화
//                gameFrame.토핑재료들[i].setEnabled(false);
            }else if(클릭된메뉴 == null && 토핑대들.get(i).상태 > 1){
                클릭된메뉴저장(토핑대들.get(i).조리음식);
                토핑대들.get(i).장비초기화(i);
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
                음료디스펜서.get(i).음료따르기(new Beverage(음료이름), i);
                // 음료버튼 비활성화
            }else if(클릭된메뉴 == null && 음료디스펜서.get(i).상태 == 1) {
                // 완료된 음료 클릭하기
//                클릭된메뉴저장(음료디스펜서.get(i).음료);
                클릭된메뉴저장(new Beverage(음료디스펜서.get(i).음료.이름, 음료디스펜서.get(i).음료재료.가격));
                음료디스펜서.get(i).장비초기화(i);
                // 음료버튼 활성화
            }
        }
        
        for (int i = 0; i < gameFrame.주문메뉴들.length; i++){
            if(!e.getSource().equals(gameFrame.주문메뉴들[i])) continue;
            if(픽업대들.get(i).상태==0) break; // 주문 없는 상태면 멈추기
            Pickup 픽업대 = 픽업대들.get(i); // 해당 픽업대 객체 가져오기
//            if(클릭된메뉴 != null && 픽업대.상태==1 &&  ////// 픽업대 주문중 상태이고 클릭된 메뉴가 음료이거나 조리된 음식일 때
//                (클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 > 1) || 클릭된메뉴 instanceof Beverage){
           if(클릭된메뉴 != null && 픽업대.상태==1){ // 클릭된 메뉴가 있고, 픽업대가 주문이 있는 상태라면  TODO
//               if(클릭된메뉴 instanceof Food && ((Food)클릭된메뉴).조리상태 < 2) continue;
              // Food 조리상태 -  0 탄음식,1 베이스재료 ,2 레인지에 가열된 음식,3 토핑된 음식
               Boolean 음식받기 = 픽업대.음식받기(클릭된메뉴);  // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크하여 처리
                if(음식받기) { // 클릭한 픽업대의 주문메뉴에 현재 저장된 클릭된 메뉴가 있다면 음식받기 성공
                    gameFrame.픽업대주문메뉴받기(i, String.valueOf(픽업대.손님.주문메뉴)); // 픽업대에 적힌 주문메뉴 업데이트
                    // 픽업대의 주문메뉴 모두 서빙했다면 주문완료 처리하기
                    if(픽업대.손님.주문메뉴.size()< 1) 픽업대.주문완료();
                    if(클릭된메뉴.이름.equals("만능요리")){
                        for (int j = 0; j < gameFrame.주문메뉴들.length; j++) gameFrame.주문메뉴들[j].setBackground(gameFrame.베이스컬러);
                        for (int 아이템위치 =0; 아이템위치 < gameFrame.게임아이템들.length; 아이템위치++)gameFrame.게임아이템들[아이템위치].setEnabled(true);
                    }
                    클릭된메뉴초기화();
                }
            }else if(픽업대.상태==2){
                게임유저.코인회수하기(픽업대);
            }
        }

        for (int i = 0; i < gameFrame.게임아이템들.length; i++){ // 아이템버튼들 비활성화 -> 아이템 사용완료시 다시 활성화
            if(!e.getSource().equals(gameFrame.게임아이템들[i])) continue;
            GameItem 게임아이템 = 게임유저.게임아이템들.get(i);
            if(게임아이템.이름.equals("만능요리")){
                // 주문 상태인 픽업대가 없으면 아이템 사용 X
                if(!주문상태인픽업대있음()) break;
                클릭된메뉴저장(((AllPurposeItem)게임아이템).만능메뉴);
                // 픽업대 버튼 클릭 유도하도록 눈에 띄게 픽업대들 색깔 바꾸기
                for (int j = 0; j < gameFrame.주문메뉴들.length; j++) {
                    if(픽업대들.get(j).상태 ==1) gameFrame.주문메뉴들[j].setBackground(gameFrame.만능요리컬러);
                }
                // 아이템 버튼들 비활성화 -> a아이템 버튼을 클릭했는데 사용하기 전에 b아이템버튼을 클릭하는 것을 방지
                for (int 아이템위치 =0; 아이템위치 < gameFrame.게임아이템들.length; 아이템위치++)gameFrame.게임아이템들[아이템위치].setEnabled(false);
                게임아이템.아이템사용();
            }else if(게임아이템.이름.equals("초특급요리")){
                // 주문 상태인 픽업대가 없으면 아이템 사용 X
                if(!주문상태인픽업대있음()) break;
                클릭된메뉴저장(((SuperSpecialItem)게임아이템).만능메뉴);
                for (int 픽업대위치= 0; 픽업대위치 < 픽업대들.size(); 픽업대위치++){
                   Pickup 픽업대 = 픽업대들.get(픽업대위치);
                   if(픽업대.상태 ==1 ) { // 주문상태된 상태인 픽업대들만 처리하도록
                       클릭된메뉴저장(((SuperSpecialItem)게임아이템).만능메뉴);
                       Boolean 음식받기 = 픽업대.음식받기(클릭된메뉴);  // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
                       if (음식받기) {
                           gameFrame.픽업대주문메뉴받기(픽업대.위치, String.valueOf(픽업대.손님.주문메뉴));
                           픽업대.주문완료();
                       }
                       클릭된메뉴초기화();
                   }
                }
                게임아이템.아이템사용();
            }else if(게임아이템.이름.equals("캔디")){
//                클릭된메뉴저장(((CandyItem)게임아이템).캔디); // TODO ??
                ((CandyItem)게임아이템).아이템사용(픽업대들);
            }else if(게임아이템.이름.equals("시간정지")){ //  TODO : 스레드
                // 게임.시간스레드를 게임아이템.시간동안 정지
                 게임아이템.아이템사용();
            }
//            게임아이템.아이템사용();
            gameFrame.set아이템개수(게임아이템.개수, i); // 아이템 사용 후 개수 차감된 개수 표시
            if(게임아이템.개수 < 1 ) gameFrame.게임아이템들[i].setEnabled(false); // 아이템을 모두 소진하면 해당 아이템 버튼 비활성화
        }

        if(e.getSource().equals(gameFrame.일시정지)) 게임콘솔.일시정지();
        else if(e.getSource().equals(gameFrame.쓰레기통)){
            if(클릭된메뉴!=null){ // 음식 버리기
                게임유저.음식버리기(클릭된메뉴, 게임미션.음식버리지않기);
                클릭된메뉴초기화();
            }
        }

//        System.out.println("클릭된메뉴: " +클릭된메뉴);
    }


    public boolean 주문상태인픽업대있음(){
        for (int 픽업대위치= 0; 픽업대위치 < 픽업대들.size(); 픽업대위치++) {
            if (픽업대들.get(픽업대위치).상태 == 1) return true;
        }
        return false;
    }


} // end class




