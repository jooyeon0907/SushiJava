public class Food { // 조리한 음식
//    int 조리시간; // -> 장비가 조리시간을 갖기
    String 이름;
    int 조리상태; // 0 탄음식,1 베이스재료 ,2 화구에 가열된 음식,3 토핑된 음식

    public Food(){}
    public Food(String 이름, int 조리상태) {
        this.이름 = 이름;
        this.조리상태 = 조리상태;
    }
}
