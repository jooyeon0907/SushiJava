import java.util.HashMap;

public class GameUser { // 게임 유저
    String  이름;
    int 생성날짜;
//    int 게임점수; // 매출액 누적 - 유저정보 저장기능 사용한다면 게임점수 저장시키기
    int 보유코인수;
    int 게임레벨; // 유저에 저장? 게임 관리하는 클래스에 저장?
//    String[] 보유아이템;
//    HashMap<GameItem, Integer> 보유아이템;
    // {아이템객체1: 2}, {아이템객체1: 3개 } ...
    public GameUser(){}

    public void 캐릭터초기화(){

    }
    public void 이름설정하기(){}
    public void 코인회수하기(GameFrame gameFrame, Pickup 픽업대){
        System.out.println(픽업대.결제금액  +"코인을 회수합니다.");
        Game.현재매출 += 픽업대.결제금액;
        픽업대.상태 = 0;
        픽업대.결제금액 = 0;

        /// 현재매출 목표매출 비교
    }

//    public void 음식서빙하기(Pickup 픽업대, Food 서빙메뉴){ // 픽업대에 배치?
//        // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
//        if(픽업대.주문메뉴들.contains(서빙메뉴.이름)){
//            System.out.println(서빙메뉴 + " 서빙 완료하였습니다.");
//            픽업대.결제금액 += 서빙메뉴.가격;
//            픽업대.주문메뉴들.remove(서빙메뉴.이름);
//            //// 서빙메뉴 객체는 언제 사라지는지 체크하기
//        }
//        if(픽업대.주문메뉴들.size() < 1){
//            // 서빙 완료했으면 계산하기  //// 위치 고려해보기
//            Boolean 계산완료됨 = 픽업대.손님.계산하기();
//            if(계산완료됨) 픽업대.계산완료됨();
//        }
//    }

    public void 음식버리기(){Game.음식버린횟수 += 1;}


    public void 상점이용하기(){}

}



/*
















* */