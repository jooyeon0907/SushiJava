import javax.swing.*;

public class ClickMenu {  // 굳이 이 클래스로 빼서 사용안하고 게임컨트롤 부분에 메소드 추가해서 관리하면 될 듯
    Boolean 클릭된상태 = false;
    Object 클릭메뉴;
    public void 베이스재료(KitchenIngredient 베이스재료){
        this.클릭메뉴 = 베이스재료;
    }

    public void 클릭메뉴저장(GameFrame gameFrame, JLabel jLabel){
        jLabel.setText("");
    }
    public void 클릭메뉴비우기(GameFrame gameFrame, JLabel jLabel){
        this.클릭메뉴 = null;
        jLabel.setText("");
    }
}
