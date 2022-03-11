public class SuperSpecialItem extends GameItem {
    Menu 만능메뉴;
    public SuperSpecialItem(String 이름, int 가격) {
        super(이름, 가격);
        this.만능메뉴 = new Menu("만능요리");
    }

//    public SuperSpecialItem(String 이름, int 가격) {
//        super(이름, 가격);
//    }

    public void 아이템설명(){
        System.out.println("모든 손님에게 한꺼번에 서빙하세요!");
    }
    public void 아이템사용(){
        // 현재 픽업대에 주문된 모든 메뉴들이 처리됨
        //
        //픽업대.음식받기(new GameFrame(), "만능요리");
    }
}
