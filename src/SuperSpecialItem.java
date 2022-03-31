public class SuperSpecialItem extends GameItem { // 초특급요리
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

}
