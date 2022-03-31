public class Dispensor extends KitchenEquipment{
    Beverage 음료;
    BeverageIngredient 음료재료;


    public Dispensor(String 이름) {super(이름);}
    public Dispensor(String 이름, BeverageIngredient 음료재료) {
        super(이름);
        this.음료재료 = 음료재료;
    }

    public void 음료따르기(Beverage 음료, int 위치){ // TODO 쓰레드
        this.음료 = 음료;
        System.out.println(this.이름 + "에서 " + this.음료.이름 + " 따르기 시작 ");
        // 따르기 완료
        this.상태 = 1;
        Game.gameFrame.set음료따르기완료(this.음료.이름, 위치);
    }

    public void 장비초기화(int 위치) {
        super.장비초기화();
        this.음료 = null;
        Game.gameFrame.init음료디스펜스(위치);
    }
}
