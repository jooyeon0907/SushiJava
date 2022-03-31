import java.util.ArrayList;
import java.util.HashMap;

public class KitchenIngredient extends Kitchen {
    int 가격;

    public KitchenIngredient(){}
    public KitchenIngredient(String 이름) {super(이름);}
    public KitchenIngredient(String 이름, int 가격) {
        super(이름);
        this.가격 = 가격;
    }

    public void 재료가격셋팅(){this.가격 = (int) this.가격표.get(this.품질-1).get("재료가격");}
    public void 재료가격표셋팅(int[] 재료가격, int[] 업그레이드가격){
        ArrayList<HashMap> 가격표 = new ArrayList<>();
        for (int i = 0; i < 재료가격.length; i++){
            int finalI = i;
            가격표.add(new HashMap<>() {{put("재료가격", 재료가격[finalI]);put("업그레이드가격", 업그레이드가격[finalI]);}});
        }
        this.가격표 = 가격표;
        this.재료가격셋팅();
    }
    public boolean 재료업그레이드(GameUser 게임유저){
    // 재료업그레이드 후 메뉴판도 업그레이드 해주기.
    // 상점 입장 -> 재료 업그레이드 -> 연어재료 업그레이드/장어 재료 업그레이드/.. -> 코인 지불하고 해당 메소드 실행하여 품질 값 올리기
    // 업그레이드 할 수 있는지 코인수 체크
        int 업그레이드가격 = (int)this.가격표.get(this.품질).get("업그레이드가격");
        if(게임유저.보유코인수 < 업그레이드가격){
            System.out.println("보유 코인이 부족하여 업그레이드를 할 수 없습니다.");
            System.out.println("보유 코인수 : " + 게임유저.보유코인수);
            System.out.println("부족한 코인수 : " + (업그레이드가격-게임유저.보유코인수));
            return false;
        }else{
            this.품질 += 1;
            this.재료가격셋팅(); // 재료 품질이 향상됨에 따라 올라간 재료 가격으로 업데이트 해주기
            System.out.println(super.이름 + "재료 품질이 " + this.품질 + "로 업그레이드 되었습니다. " +
                               super.이름 + " 재료의 가격이 " + this.가격 + "코인으로 올랐습니다.");
            System.out.println(게임유저.이름 + "님의 코인이 "+업그레이드가격  + "코인이 차감되었습니다. ");
            게임유저.보유코인수 -= 업그레이드가격;
            System.out.println(게임유저.이름 + "님의 코인이 " + 게임유저.보유코인수 + "코인이 남았습니다.");
            return true;
        }
    }



}
