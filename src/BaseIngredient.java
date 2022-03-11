import java.util.ArrayList;
import java.util.HashMap;

public class BaseIngredient extends KitchenIngredient { // 베이스 재료
    public BaseIngredient() {super();}
    public BaseIngredient(String 이름) {super(이름);}
    public BaseIngredient(String 이름, int 가격) {
        super(이름, 가격);
    }

    public void 베이스재료업그레이드(String 이름, int 현재품질){ //

    }
    
    public String 조리된이름(){
        String 이름 = null; 
        switch(this.이름){
            case "쌀": 이름 = "초밥"; break;
            case "면": 이름 = "라멘"; break;
        }
        return 이름;
    }


}

