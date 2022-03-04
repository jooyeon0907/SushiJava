public class KitchenEquipment extends Kitchen {
    int 조리시간;
    int 상태; //  0: 사용 안함 1: 조리중 2: 조리 완료 3: 조리 오버(화구에만 해당)


    public KitchenEquipment(String 이름, String 타입) {
        this.이름 = 이름;
        this.타입 = 타입;
    }

    public void 조리하기(Food 조리음식){
        this.조리음식 = 조리음식;
        System.out.println(this.이름 +  "에서 " + this.조리음식.이름 + " 조리 시작");
        // 조리시간 타이머 스레드 시작

        // 조리완료되면 이름 조리음식 이름 변경, 가격추가, 음식상태변화
        this.상태 = 2;
        this.조리음식.조리상태 = 2;
    }

    public void 음료따르기(KitchenIngredient 음료){
        this.음료 = 음료;
        System.out.println(this.이름 + "에서 " + this.음료.이름 + " 따르기 시작 ");
    }


}
