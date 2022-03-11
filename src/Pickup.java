import java.util.ArrayList;

public class Pickup { ///
    int 위치;
    Customer 손님;
//    ArrayList<String> 주문메뉴;
    int 상태; // 0 손님없음 1 주문상태 2 계산됨(코인있음)
    int 결제금액;

    public Pickup(){}

    public void 손님배정하기(Customer 손님, int 위치){
        System.out.println("손님입장.");
        this.손님 = 손님;
        this.위치 = 위치;
    }
    public Boolean 음식받기(GameFrame gameFrame, Menu 서빙메뉴){
        Boolean 음식받기 = false;
        // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
        if(서빙메뉴.이름.equals("만능요리")){
            // 만능메뉴 아이템 사용으로 클릭한 픽업대의 주문 메뉴가 모두 서빙됨
                // 주문 메뉴들의 가격들이 결제되고 손님.주문메뉴 초기화
            System.out.println("before >>> " + 손님.주문메뉴.toString());
            for (int i = 0; i < 손님.주문메뉴.size(); i++){
                this.결제금액 += Game.메뉴가격표.get(손님.주문메뉴.get(i));
                System.out.println("주문 메뉴 " + 손님.주문메뉴.get(i) + "가 "+Game.메뉴가격표.get(손님.주문메뉴.get(i))+" 원에 결제되었습니다.");
            }
            손님.주문메뉴.clear();
            System.out.println("after >>> " + 손님.주문메뉴.toString());
            음식받기 = true;
        }
        else if(손님.주문메뉴.contains(서빙메뉴.이름)){ // TODO : 음식 서빙될 때마다 기다리는 시간 n초 추가 or 이전 기분상태만큼 추가?
            System.out.println(서빙메뉴.이름 + " 서빙 완료하였습니다.");
            this.결제금액 += Game.메뉴가격표.get(서빙메뉴.이름); // TODO: 가격표에서 가격 가져오기
            손님.주문메뉴.remove(서빙메뉴.이름);
            음식받기 = true;
        }

//        if 손님.유형.equals("인내심없는손님")주문메뉴 추가 // TODO

        return 음식받기;
    }
    public void 주문완료(GameFrame gameFrame){
        System.out.println("주문 clear");
        Game.손님수 += 1;
        // 손님 스레드 사라짐
        this.손님 = null;
        this.상태 = 2;
        gameFrame.픽업대주문완료(this.결제금액, this.위치);
    }

//    public void 픽업대초기화(GameFrame gameFrame){ // 초기화
//        System.out.println("계산 완료 - 픽업대 초기화");
//        Game.손님수 += 1;
//        // 손님 스레드 사라짐
//        ///// 위치 변경할 예정
//        gameFrame.손님수값.setText(String.valueOf(Game.손님수)); //
//        gameFrame.손님들[위치].setText(""); //
//        gameFrame.주문시간들[위치].setText(""); //  위치 변경
//
//        this.손님 = null;
//        this.상태 = 2;
//    };
}
