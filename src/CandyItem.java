import java.util.ArrayList;

public class CandyItem extends GameItem {
    int 기다리는시간 = 5;
    Menu 캔디;
    public CandyItem(String 이름, int 가격) {
        super(이름, 가격);
        this.캔디 = new Menu("캔디");
    }

    public void 아이템설명(){System.out.println("손님의 기분이 좋아집니다. (주문 중인 손님들 기다리는 시간 리셋)");}
    public void 아이템사용(ArrayList<Pickup> 픽업대들){
        for (int i = 0; i < 픽업대들.size(); i++){
            픽업대들.get(i).손님.기다리는시간리셋();
            Game.gameFrame.init기다리는시간(i, 픽업대들.get(i).손님.기다리는시간);
        }

    }

}
