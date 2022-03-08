public class Food extends Menu{ // 조리한 음식
    int 조리상태; // 0 탄음식,1 베이스재료 ,2 레인지에 가열된 음식,3 토핑된 음식

    public Food(String 이름) {
        super(이름);
    }
    public Food(String 이름, int 조리상태) {
        super(이름);
        this.이름 = 이름;
        this.조리상태 = 조리상태;
    }

    public Food(String 이름, int 조리상태, int 가격) {
        super(이름);
        this.이름 = 이름;
        this.조리상태 = 조리상태;
        this.가격 = 가격;
    }
}
