import java.util.ArrayList;

public class Customer { //  손님
    int 기다리는시간셋팅값=12;
    int 기다리는시간;
    ArrayList<String> 주문메뉴;
//    String 유형; // 일반
//    int 위치; // 픽업대1, 2, 3, 4
    int 상태; // 1 기분 좋음, 2 초조, 3 나쁨
//    int 결제금액;
        // TODO: 픽업대에서 관리하지만 그래도 손님이 픽업대에 두고 가는 개념이니 손님에서 선언하고 가져오는 로직이 맞지 않을까??

//    public int 유형별기다리는시간(){
//        int 기다리는시간 = this.유형.equals("급한손님")? 10:20;
//        return 기다리는시간;
//    }

    public Customer(){}


    public void 주문하기(GameFrame gameFrame, ArrayList<String> 주문메뉴, int 위치){
        // 카운트에서 주문하는 것이니까 주문픽업대에 저장시키기? X
        System.out.println("주문 시작 !!!");
        // 주문메뉴 정하기 - 레벨에 따라 달라짐
        // 주문메뉴는 게임 레벨에 따라 미리 한정시킴

        this.주문메뉴 = 주문메뉴;
        기다리는시간리셋();
        gameFrame.손님주문하기(위치, String.valueOf(주문메뉴), 기다리는시간);
        // 스레드 생성하여 기다리는 시간 카운트 다운
    }
    public void 계산하기(){
    }

    public void 감정표현하기(int 기다리는시간){
      // 스레드 사용하여 실시간 감정 표현
    }
    public void 기다리는시간리셋(){this.기다리는시간 = this.기다리는시간셋팅값*this.주문메뉴.size();}

}
