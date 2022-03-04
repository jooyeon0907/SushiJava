public class Pickup { ///
    int 위치;
    Customer 손님;
    String[] 주문메뉴들;
    int 상태; // 0 손님없음 1 주문상태 2 계산됨(코인있음)
    int 계산금액;
    public Pickup(){}
    public Pickup(Customer 손님){ // 손님 배정됨
        this.손님 = 손님;
        this.위치 = 손님.위치;
    }

//    public void 손님배정하기(Customer 손님, int 위치){
//        this.손님 = 손님;
//        this.위치 = 위치;
//    }
    public void 손님배정하기(){}
    public void 음식받기(){}

}
