import java.util.ArrayList;

public class Pickup { ///
    int 위치;
    Customer 손님;
//    ArrayList<String> 주문메뉴;
    int 상태; // 0 손님없음 1 주문상태 2 계산됨(코인있음)
    int 결제금액;

    public Pickup(){}

    public void 손님배정하기(String 유형, int 위치){
        // TODO 쓰레드 : 랜덤하게 유형배치를 여기서 할까?
        this.손님 = new Customer(유형);
        // 손님 배치하기
        Game.gameFrame.손님들[위치].setText(유형 + ": " +"안녕하세요");
        //  주문메뉴 주문하기
        this.손님.주문하기(위치); //// TODO 쓰레드 : 위치
        this.상태 = 1; // 픽업대 상태를 주문 중인 상태로 설정
        this.위치 = 위치;
//        System.out.println(유형 + " 입장.");
    }


    public Boolean 음식받기(Menu 서빙메뉴){
        Boolean 음식받기 = false;
        // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
        if(서빙메뉴.이름.equals("만능요리")){
            // 만능메뉴 아이템 사용으로 클릭한 픽업대의 주문 메뉴가 모두 서빙됨
                // 주문 메뉴들의 가격들이 결제되고 손님.주문메뉴 초기화
//            System.out.println("before >>> " + 손님.주문메뉴.toString());
            for (int i = 0; i < 손님.주문메뉴.size(); i++){
                this.결제금액 += Game.메뉴판.get(손님.주문메뉴.get(i)); // 메뉴판에서 주문메뉴의 가격 조회
                System.out.println("주문 메뉴 " + 손님.주문메뉴.get(i) + "이 "+Game.메뉴판.get(손님.주문메뉴.get(i))+" 원에 결제되었습니다.");
            }
            손님.주문메뉴.clear();
//            System.out.println("after >>> " + 손님.주문메뉴.toString());
            음식받기 = true;
        }
        else if(손님.주문메뉴.contains(서빙메뉴.이름)){ // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 확인
            // TODO : 음식 서빙될 때마다 기다리는 시간 n초 추가 or 이전 기분상태만큼 추가?
            System.out.println(서빙메뉴.이름 + " 서빙 완료하였습니다.");
            this.결제금액 += Game.메뉴판.get(서빙메뉴.이름); //
            손님.주문메뉴.remove(서빙메뉴.이름); // 서빙완료했으므로 주문 중인 메뉴에서 삭제해줌
            음식받기 = true;
        }
//        if 손님.유형.equals("인내심없는손님")주문메뉴 추가 // TODO 쓰레드
        return 음식받기;
    }
    public void 주문완료(){
//        System.out.println("주문 clear");
        Game.손님수 += 1;
        // TODO 쓰레드 : 손님  사라짐
        this.손님 = null;
        this.상태 = 2; // 픽업대 상태를 코인있는 상태로 변경
        Game.gameFrame.픽업대주문완료(this.결제금액, this.위치); // 픽업대에 코인수 표시하기
    }


}
