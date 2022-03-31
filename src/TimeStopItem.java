public class TimeStopItem extends GameItem {
    // 시간정지 아이템
    // 정지시간동안 게임.타이머 스레드가 멈춤
    int 정지시간;
    public TimeStopItem(String 이름, int 가격) {
        super(이름, 가격);
        this.정지시간 = 5;
    }

    public void 아이템설명(){System.out.println("일정 시간 동안 게임의 타이머를 정지시킵니다.");}
    public void 아이템사용(Game 게임){
        super.아이템사용();
//        게임.타이머.sleep(정지시간);
    }

}
