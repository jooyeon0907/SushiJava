public class GameUser { // 게임 유저
    String  이름;
    int 생성날짜;
    int 게임점수; // 매출액 누적
    int 보유코인수;
    int 게임레벨;
    String[] 보유아이템;
    public GameUser(){}

    public void 캐릭터초기화(){

    }
    public void 이름설정하기(){}
    public void 코인회수하기(Pickup 픽업대){
        // 게임한판.코인 +=
        픽업대.상태 = 0;
        픽업대.계산금액 = 0;
    }
    public void 음식서빙하기(){

    }
    public void 음식버리기(){

    }

}



/*
















* */