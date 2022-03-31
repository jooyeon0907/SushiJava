import java.util.ArrayList;

public class GameUser { // 게임 유저
    String  이름;
    int 보유코인수;
    ArrayList<GameItem> 게임아이템들;

    public GameUser(String 이름, int 보유코인수) {
        this.이름 = 이름;
        this.보유코인수 = 보유코인수;
        this.게임아이템들 = new ArrayList<>();
    }

    public void 코인회수하기(Pickup 픽업대){
        // TODO 위치 check : 회수한 코인은 게임유저의 보유코인수에 저장되는게 아니라 게임 변수에 추가되는 건데.. 그럼 게임 클래스에서 배치하는게 자연스러울려나 ?
        System.out.println(픽업대.결제금액  +"코인을 회수합니다.");
        Game.현재매출 += 픽업대.결제금액;
        픽업대.상태 = 0;
        픽업대.결제금액 = 0;
        Game.gameFrame.코인회수하기(픽업대.위치);
        /// 현재매출 목표매출 비교
    }

    public void 음식버리기(Menu 메뉴, boolean 게임미션_음식버리지않기){
        // TODO 위치 check : 유저에서 관리할지 게임에서 관리할지 ->
        //  '유저가 음식을 버린다' 로만 생각했을 때는 유저클래스에 배치를 해야할거 같지만 막상 메소드 안의 로직을 보면 게임 클래스에 관련된 내용임
//        if(게임미션_음식버리지않기) System.out.println("게임실패하고 종료 "); // TODO : 쓰레드 할 때 기능추가
        System.out.println("쓰레기통에 " + 메뉴.이름 + " 버리기");
        Game.음식버린횟수 += 1; ///  TODO : 음식버린횟수 >1 되면 게임미션.음식버리지않기 = true 로 변경하거나 어떠한 처리하기
        Game.현재매출 -= 메뉴.가격;
        System.out.println("Game.현재매출: " +Game.현재매출);
        System.out.println("메뉴.가격: " +메뉴.가격);
        Game.gameFrame.set현재매출(Game.현재매출);
    }

}



/*
















* */