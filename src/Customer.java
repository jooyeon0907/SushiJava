import java.util.ArrayList;

public class Customer { //  손님
    int 기다리는시간;
    ArrayList<String> 주문메뉴;
    // 유형
    // 요구사항
//    int 위치; // 픽업대1, 2, 3, 4
    int 상태; // 1 기분 좋음, 2 초조, 3 나쁨
//    int 결제금액;

    public void 주문하기(GameFrame gameFrame, ArrayList<String> 주문메뉴, int 위치){
        // 카운트에서 주문하는 것이니까 주문픽업대에 저장시키ㅣㄱ?
        System.out.println("주문 시작 !!!");
        this.주문메뉴 = 주문메뉴;
        gameFrame.주문메뉴들[위치].setText(String.valueOf(this.주문메뉴)); // 위치 변경
        gameFrame.주문시간들[위치].setText(String.valueOf(this.기다리는시간)); //  위치 변경

        // 스레드 생성하여 기다리는 시간 카운트 다운

    }
    public void 계산하기(){
    }

    public void 감정표현하기(int 기다리는시간){
      // 스레드 사용하여 실시간 감정 표현

    }

}
