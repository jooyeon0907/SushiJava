import java.util.ArrayList;
import java.util.HashMap;

public class Range extends KitchenEquipment{
//    BaseIngredient 베이스재료; // 레인지위에 조리 중인 베이스재료
    Food 조리음식;

    public Range() {super();}
    public Range(String 이름) {super(이름);}
    public Range(String 이름, int 품질) {
        super(이름);
        this.품질 = 품질;
    }

    public void 조리하기(Food 조리음식, int 위치){ // TODO 쓰레드
        this.조리음식 = 조리음식;
        System.out.println(this.이름 +  "에서 " + this.조리음식.이름 + " 조리하기 시작");
        // 조리시간 타이머 스레드 시작

        // 조리완료되면 이름 조리음식 이름 변경, 가격추가, 음식상태변화(오버라이드로 장비마다 상태 변화)
        this.상태 = 2; // 조리중
        this.조리음식.조리상태 = 2;
        switch(this.조리음식.이름){
            case "쌀": this.조리음식.이름 = "초밥"; break;
            case "면": this.조리음식.이름 = "라멘"; break;
        }

        // 장비 상태 조리 완료로 변경
        this.상태 = 3;
        Game.gameFrame.set조리완료(this.조리음식.이름, 위치);
    }

    public void 장비초기화(int 위치){
        super.장비초기화();
        this.조리음식 = null;
        Game.gameFrame.init레인지(위치);
    }


}
