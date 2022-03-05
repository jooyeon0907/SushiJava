public class KitchenIngredient extends Kitchen {
    int 가격;

    public KitchenIngredient(){}
    public KitchenIngredient(String 이름) {super(이름);}
    public KitchenIngredient(String 이름, int 가격) {
        super(이름);
        this.가격 = 가격;
    }

    public void 재료업그레이드(){
    // 상점 입장 -> 재료 업그레이드 -> 연어재료 업그레이드/장어 재료 업그레이드/.. -> 코인 지불하고 해당 메소드 실행하여 품질 값 올리기
        this.품질 += 1;
//        this.가격 += 100;
    }

}
