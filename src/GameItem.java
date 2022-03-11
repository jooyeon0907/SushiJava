public class GameItem {
    String 이름;
    int 가격;
    int 개수;

    public GameItem(String 이름, int 가격) {
        this.이름 = 이름;
        this.가격 = 가격;
        this.개수 = 10; // 기본 1개씩 제공
    }

    public void 아이템설명(){}
    public void 아이템사용(){
        System.out.println(this.이름+" 아이템 사용 ");
        this.개수 -=1;
    }
    public boolean 구매하기(GameUser 게임유저){ // TODO : 멘트가 재료/장비업그레이드와 멘트가 겹치니 게임유저에 작성하고 불러오도록 수정
        if(게임유저.보유코인수 < this.가격){
            System.out.println("보유 코인이 부족하여 업그레이드를 할 수 없습니다.");
            System.out.println("보유 코인수 : " + 게임유저.보유코인수);
            System.out.println("부족한 코인수 : " + (this.가격-게임유저.보유코인수));
            return false;
        }else{
            this.개수 += 1;
            게임유저.보유코인수 -= this.가격; // TODO: 위치 생각해보기
            System.out.println(this.이름 + "아이템을 구매하셨습니다.\t보유개수 : " + this.개수 + "개");
            System.out.println(게임유저.이름 + "님의 코인이 "+this.가격 + "코인이 차감되었습니다. ");
            System.out.println(게임유저.이름 + "님의 코인이 " + 게임유저.보유코인수 + "코인이 남았습니다.");
            return true;
        }
    }



}


