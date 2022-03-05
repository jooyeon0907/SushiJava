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
        System.out.println("서빙메뉴.이름 : "  +서빙메뉴.이름);
        Boolean 음식받기 = false;
        // 서빙한 음식이 현재 픽업대 주문메뉴에 해당하는지 체크
        if(손님.주문메뉴.contains(서빙메뉴.이름)){
            System.out.println(서빙메뉴.이름 + " 서빙 완료하였습니다.");
            this.결제금액 += 서빙메뉴.가격;
            System.out.println("서빙메뉴.가격 : " + 서빙메뉴.가격 + " , this.결제금액 " + this.결제금액);
            손님.주문메뉴.remove(서빙메뉴.이름);
//            gameFrame.주문메뉴들[위치].setText(String.valueOf(손님.주문메뉴)); //
            음식받기 = true;
        }
        if(손님.주문메뉴.size() < 1){ //// 따로 빼기
            System.out.println("주문 clear");
            // 서빙 완료했으면 계산하기  //// 위치 고려해보기
            계산하기(gameFrame);
            픽업대초기화(gameFrame);
            음식받기 = false;
        }
        return 음식받기; ///
    }
    public void 계산하기(GameFrame gameFrame){
        gameFrame.주문메뉴들[위치].setText(Integer.toString(this.결제금액));
    }
    public void 픽업대초기화(GameFrame gameFrame){ // 초기화
        System.out.println("계산 완료 - 픽업대 초기화");
        Game.손님수 += 1;
        // 손님 스레드 사라짐
        ///// 위치 변경할 예정
        gameFrame.손님들[위치].setText(""); //
        gameFrame.주문시간들[위치].setText(""); //  위치 변경

        this.손님 = null;
        this.상태 = 2;
    };
}
