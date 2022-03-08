import java.util.ArrayList;
import java.util.HashMap;

public class KitchenEquipment extends Kitchen {
    ArrayList<HashMap> 가격표;
    int 조리시간; // 조리시간? 장비시간? - 레인지:조리시간, 토핑대:토핑시간, 음료디스펜서:음료따르는시간
    int 상태; //  0: 사용 안함 1: 조리중 2: 조리 완료 3: 조리 오버(레인지에만 해당)

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

    public void 장비시간셋팅(){
        if(this.품질>0) this.조리시간 = (int) this.가격표.get(this.품질-1).get("장비시간");
        // 품질 0은 구매를 해야되는 상태
    }
    public boolean 장비업그레이드(GameUser 게임유저){
    // 상점 입장 -> 장비 업그레이드 -> 레인지 업그레이드/연어토핑대 업그레이드/.. -> 코인 지불하고 해당 메소드 실행하여 품질 값 올리기
    // 업그레이드 할 수 있는지 코인수 체크
        int 업그레이드가격 = (int)this.가격표.get(this.품질).get("업그레이드가격");
        if(게임유저.보유코인수 < 업그레이드가격){
            System.out.println("보유 코인이 부족하여 업그레이드를 할 수 없습니다.");
            System.out.println("보유 코인수 : " + 게임유저.보유코인수);
            System.out.println("부족한 코인수 : " + (업그레이드가격-게임유저.보유코인수));
            return false;
        }else{
            String 업그레이드or구매 = this.품질>0? "업그레이드" : "구매";
            this.품질 += 1;
            this.장비시간셋팅();
            System.out.println(super.이름 + " 품질이 " + this.품질 + "로 "+업그레이드or구매+" 되었습니다. " +
                               super.이름 + " 시간은 " + this.조리시간 + "초 입니다.");
            System.out.println(게임유저.이름 + "님의 코인이 "+업그레이드가격 + "코인이 차감되었습니다. ");
            게임유저.보유코인수 -= 업그레이드가격; // TODO: 위치 생각해보기
            System.out.println(게임유저.이름 + "님의 코인이 " + 게임유저.보유코인수 + "코인이 남았습니다.");
            return true;
        }
    }

    public void 장비가격표셋팅(int[] 시간, int[] 업그레이드가격){
        ArrayList<HashMap> 가격표 = new ArrayList<>();;
        for (int i = 0; i < 시간.length; i++){
            int finalI = i;
            가격표.add(new HashMap<>() {{put("장비시간", 시간[finalI]);put("업그레이드가격", 업그레이드가격[finalI]);}});
            }
        this.가격표 = 가격표;
        this.장비시간셋팅();
    }


}
