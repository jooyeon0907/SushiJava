public class ToppigTable extends KitchenEquipment {
    ToppingIngredient 토핑재료;

    public ToppigTable(String 이름) {
        super(이름);
    }
    public ToppigTable(String 이름, ToppingIngredient 토핑재료) {
        super(이름);
        this.토핑재료 = 토핑재료;
    }

    public void 조리하기(Food 조리음식){
        this.조리음식 = 조리음식;
        System.out.println(this.이름 +  "에서 " + this.조리음식.이름 + " 토핑 시작");
        // 조리시간 타이머 스레드 시작

        // 조리완료되면 이름 조리음식 이름 변경, 가격추가, 음식상태변화(오버라이드로 장비마다 상태 변화)
        this.상태 = 2;
        this.조리음식.조리상태 = 3;
        StringBuffer sb = new StringBuffer();
        this.조리음식.이름 = String.valueOf(sb.append(this.토핑재료.이름).append(this.조리음식.이름));
    }


}
