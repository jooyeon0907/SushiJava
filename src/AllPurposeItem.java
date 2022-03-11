import java.awt.*;
import java.util.ArrayList;

public class AllPurposeItem extends GameItem {
    // 클릭된 메뉴 = 만능요리 상태에서 픽업대로 전달되면 픽업대 주문 메뉴들 전부 서빙됨.
    ///// 그럼 음식결제금액은 어떻게 정산될지
    Menu 만능메뉴;
    public AllPurposeItem(String 이름, int 가격) {
        super(이름, 가격);
        this.만능메뉴 = new Menu("만능요리");
    }

    public void 아이템설명(){System.out.println("손님의 주문을 무엇이든 완료해줍니다! (손님 한 명의 주문을 모두 서빙)");}
    public void 아이템사용(ArrayList<Pickup> 픽업대들){
        // 여기서 딱히 사용되는건 없네 아니면 코드를 여기로 옮겨서 관리해야되나
//        for (int j = 0; j < gameFrame.주문메뉴들.length; j++){
//                    gameFrame.주문메뉴들[j].setBackground(Color.blue);
//        }
    }
}
