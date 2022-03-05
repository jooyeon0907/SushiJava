public class KitchenEquipment extends Kitchen {
    int 조리시간;
    int 상태; //  0: 사용 안함 1: 조리중 2: 조리 완료 3: 조리 오버(화구에만 해당)

    Food 조리음식;

    public KitchenEquipment(String 이름) {
        super(이름);
        this.조리시간 = 10; // 품질1 = 10초
    }

    public void 조리하기(Food 조리음식){
        this.조리음식 = 조리음식;
        System.out.println(this.이름 +  "에서 " + this.조리음식.이름 + " 조리 시작");
        // 조리시간 타이머 스레드 시작

        // 조리완료되면 이름 조리음식 이름 변경, 가격추가, 음식상태변화
        this.상태 = 2;
        this.조리음식.조리상태 = 2;
    }

    public void 조리음식초기화(){
        this.상태 = 0; //// 메소드명이랑 좀 안어울리는 듯
        this.조리음식 = null;
    }
    public void 장비업그레이드(){
        this.품질 += 1;
    }


}
