import java.util.ArrayList;
import java.util.HashMap;

public class Kitchen { // 주방 -> TODO 이름 변경할까 - KitchenItem, KitchenMenu, KitchenObject
    String 이름;
    int 품질;
    ArrayList<HashMap> 가격표;

    public Kitchen(){}
    public Kitchen(String 이름) {
        this.이름 = 이름;
        this.품질 = 1;
    }


//    public void 가격표셋팅(int[] 스킬, int[] 업그레이드가격){  // 스킬? - 재료는 가격, 장비는 조리시간
//        for (int i = 0; i < 스킬.length; i++){
//            int finalI = i;
//            this.가격표.add(new HashMap<>() {{put("스킬", 스킬[finalI]);put("업그레이드가격", 업그레이드가격[finalI]);}});
//        }
//    }



}
