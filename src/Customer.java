import java.util.Arrays;

public class Customer { //  손님
    int 기다리는시간;
    String[] 주문메뉴;
    // 유형
    // 요구사항
    int 위치; // 픽업대1, 2, 3, 4
    int 상태; // 1 기분 좋음, 2 초조, 3 나쁨

    public void 주문하기(GameFrame gameFrame){ // 카운트에서 주문하는 것이니까 주문픽업대에 저장시키ㅣㄱ?
        System.out.println("주문 시작 !!!");
        this.주문메뉴 = new String[]{"밥", "연어초밥"};
        gameFrame.주문메뉴들[this.위치].setText(Arrays.toString(this.주문메뉴));
        gameFrame.주문시간들[this.위치].setText(String.valueOf(this.기다리는시간));

    }
    public void 계산하기(){}
    public void 감정표현하기(){}

}
