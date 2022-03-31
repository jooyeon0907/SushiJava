import java.util.ArrayList;
import java.util.Random;

public class Customer { //  손님
    int 기다리는시간셋팅값=20; // TODO
    int 기다리는시간;
    ArrayList<String> 주문메뉴;
    String 유형; // 일반손님, 바쁜손님, 대식가손님
    int 상태; // 1 기분 좋음, 2 초조, 3 나쁨

//    public int 유형별기다리는시간(){ // 손님 유형별로 클래스 생성하지 않는다면 유형변수로 관리
//        int 기다리는시간 = this.유형.equals("급한손님")? 10:20;
//        return 기다리는시간;
//    }

    // TODO : 대식가 손님이 첫번째 주문인지 판단을 어떻게 할 것인지 ?
    // -> 대식가 손님의 첫주문이 클리어되면 유형을 대식가손님 -> 대식가손님2로 바꾸기
    public Customer(){}
    public Customer(String 유형) { // TODO : check
        this.유형 = 유형;
        this.상태 = 1; // 기본 1로 시작
    }

    public void 주문하기(int 위치){
        this.주문메뉴 = new ArrayList<>();
        // 카운트에서 주문하는 것이니까 주문픽업대에 1저장시키기? X
//        System.out.println("주문 시작 !!!");
        // 주문메뉴 정하기 - 레벨에 따라 달라짐
        // 주문메뉴는 게임 레벨에 따라 미리 한정시킴
        Random r = new Random();
//        int 주문메뉴갯수 =r.nextInt(Game.주문메뉴최대갯수);
        int[] 주문메뉴번호들 = new int[r.nextInt(Game.주문메뉴최대갯수)+1];
//        System.out.println(">>> 주문메뉴 갯수   : "  + 주문메뉴번호들.length);
        for (int i = 0; i < 주문메뉴번호들.length; i++){
            주문메뉴번호들[i] = r.nextInt(Game.메뉴판.size());
            for (int j = 0; j < i; j++){if(주문메뉴번호들[i] == 주문메뉴번호들[j]) i--; break;}
        }
        for (int i = 0; i < 주문메뉴번호들.length; i++){
//            System.out.println(위치  +  "     >>>> 메뉴 번호 : " + 주문메뉴번호들[i]  +
//                                " , 메뉴 이름 : " +  Game.메뉴이름들.get(주문메뉴번호들[i]) +
//                                " , 메뉴 가격 : " +  Game.메뉴판.get(Game.메뉴이름들.get(주문메뉴번호들[i])));
            this.주문메뉴.add(Game.메뉴이름들.get(주문메뉴번호들[i]));
        }
        기다리는시간리셋();
        Game.gameFrame.손님주문하기(위치, String.valueOf(this.주문메뉴), 기다리는시간);
        // TODO 스레드 : 스레드 생성하여 기다리는 시간 카운트 다운
    }

    public void 감정표현하기(int 기다리는시간){
      // TODO 스레드 : 사용하여 실시간 감정 표현

    }
    public void 기다리는시간리셋(){  // TODO
        this.기다리는시간 = this.기다리는시간셋팅값*this.주문메뉴.size();
        if(유형.equals("바쁜손님")) this.기다리는시간  = (int) (this.기다리는시간 * 0.8);
    }



}
