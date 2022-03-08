import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameConsole {
    Scanner 스캐너 = new Scanner(System.in);

    public String 게임유저등록(){
        System.out.print("이름을 입력해주세요 : ");
        return  스캐너.nextLine();
    }


    public void 메인화면출력(Game 게임){
        String 대답;
        System.out.println("""
                    \n<메인화면>
                    1. 게임 시작
                    2. 나의 정보
                    3. 상점
                    4. 종료
                    """);
        System.out.println(": 숫자를 입력해주세요.");
        대답 = 범위내_숫자대답받기(1, 4);
        switch(대답){
            case "1":
                게임.게임시작();
                break;
            case "2":
                나의정보출력(게임);
                break;
            case "3":
                상점_화면출력(게임);
                break;
            case "4":
                System.out.println("스시자바를 종료합니다.");
                System.exit(0);
                break;
        }//end switch
    }

    public void 메인화면으로돌아가기(Game 게임){
        System.out.println("\n1. 메인화면으로 돌아가기");
        String 대답 = 범위내_숫자대답받기(1, 1);
        if(대답.equals("1")) 메인화면출력(게임);
    }

//나의 정보/////////////////////////////////////////////////////
    public void 나의정보출력(Game 게임){
        String 대답;
        System.out.println("""
\n<나의 정보>
1. 유저 정보
2. 재료 정보
3. 장비 정보
4. 보유 아이템
5. 메인화면으로 돌아가기
                    """);
        System.out.println(": 숫자를 입력해주세요.");
        대답 = 범위내_숫자대답받기(1, 5);
        switch(대답){
            case "1":
                유저정보출력(게임);
                break;
            case "2":
                재료정보출력(게임);
                break;
            case "3":
                장비정보출력(게임);
                break;
            case "4":
                보유아이템출력(게임);
            case "5":
                메인화면출력(게임);
                break;
        }//end switch
    }
    public void 유저정보출력(Game 게임){
        System.out.println("\n<유저 정보>");
        System.out.println("이름 : " + 게임.게임유저.이름);
        System.out.println("게임레벨 : " + 게임.게임유저.게임레벨);
        System.out.println("보유코인수 : " + 게임.게임유저.보유코인수);
        메인화면으로돌아가기(게임);
    }
    public void 재료정보출력(Game 게임){
        System.out.println("\n<재료 정보>");
        System.out.println("--베이스 재료----------------------");
        System.out.println("이름\t\t\t품질\t\t\t가격");
        System.out.println("---------------------------------");
        for (int i = 0; i < 게임.베이스재료들.size(); i++){
            BaseIngredient 베이스재료 = 게임.베이스재료들.get(i);
            System.out.println(베이스재료.이름 + "\t\t\t" +  베이스재료.품질  +"\t\t\t" + 베이스재료.가격);
        }

        System.out.println("\n--토핑 재료----------------------");
        System.out.println("이름\t\t\t품질\t\t\t가격");
        System.out.println("--------------------------------");
        for (int i = 0; i < 게임.토핑대들.size(); i++){
            ToppingIngredient 토핑재료 = 게임.토핑대들.get(i).토핑재료;
            System.out.println(토핑재료.이름 + "\t\t\t" +  토핑재료.품질  +"\t\t\t" + 토핑재료.가격);
        }

        System.out.println("\n--음료 재료----------------------");
        System.out.println("이름\t\t\t품질\t\t\t가격");
        System.out.println("--------------------------------");
        for (int i = 0; i < 게임.음료디스펜서.size(); i++){
            BeverageIngredient 음료재료 = 게임.음료디스펜서.get(i).음료재료;
            System.out.println(음료재료.이름 + "\t\t\t" +  음료재료.품질  +"\t\t\t" + 음료재료.가격);
        }
        메인화면으로돌아가기(게임);
    }

    public void 장비정보출력(Game 게임){
        System.out.println("\n<장비 정보>");
        System.out.println("--레인지--------------------------------");
        System.out.println("이름\t\t\t품질\t\t\t조리시간");
        System.out.println("-------------------------------------");
        for (int i = 0; i < 게임.레인지들.size(); i++){
            Range 레인지 = 게임.레인지들.get(i);
            System.out.println(레인지.이름 + "\t\t\t" +  레인지.품질  +"\t\t\t" + 레인지.조리시간);
        }

        System.out.println();
        System.out.println("--토핑대------------------------------");
        System.out.println("이름\t\t\t품질\t\t\t토핑시간");
        System.out.println("-------------------------------------");
        for (int i = 0; i < 게임.토핑대들.size(); i++){
            ToppigTable 토핑대 = 게임.토핑대들.get(i);
            System.out.println(토핑대.이름 + "\t\t\t" +  토핑대.품질  +"\t\t\t" + 토핑대.조리시간);
        }

        System.out.println();
        System.out.println("--음료디스펜서-------------------------");
        System.out.println("이름\t\t\t품질\t\t\t시간");
        System.out.println("-------------------------------------");
        for (int i = 0; i < 게임.음료디스펜서.size(); i++){
            Dispensor 디스펜서 = 게임.음료디스펜서.get(i);
            System.out.println(디스펜서.이름 + "\t\t\t" +  디스펜서.품질  +"\t\t\t" + 디스펜서.조리시간);
        }
        메인화면으로돌아가기(게임);
    }
    public void 보유아이템출력(Game 게임){
        메인화면으로돌아가기(게임);
    }
//end 나의 정보/////////////////////////////////////////////////////



//상점/////////////////////////////////////////////////////
    public void 상점_화면출력(Game 게임){
        System.out.println("""
                    \n<상점>
             1. 재료 업그레이드
             2. 장비 업그레이드
             3. 아이템 구매
             4. 메인화면으로 돌아가기
                """);
        System.out.println(": 숫자를 입력해주세요.");
        String 대답 = 범위내_숫자대답받기(1, 4);
        switch(대답){
            case "1":
                재료업그레이드_화면출력(게임);
                break;
            case "2":
                장비업그레이드_화면출력(게임);
                break;
            case "3":
                아이템구매_화면출력(게임);
                break;
            case "4":
                메인화면출력(게임);
        }//end switch
    }

    public String 재료가격출력(int no, String 재료이름, int 품질, ArrayList<HashMap> 재료가격표 ){
        String result = null;
        if(품질<Game.재료최고품질){
                result = no+". "+재료이름+"(품질"+품질+") "+재료가격표.get(품질-1).get("재료가격")+"코인" +
                          " -> "+재료이름+"(품질"+(품질+1)+") "+재료가격표.get(품질).get("재료가격")+"코인  " +
                            "--->>>업그레이드 "+재료가격표.get(품질).get("업그레이드가격")+"코인<<<---";
        }else{ result = no +". "+재료이름+"(품질"+품질+") "+재료가격표.get(품질-1).get("재료가격")+"코인\t\t\t----------최고 품질----------"; }
        return result;
    }
    public void 재료업그레이드_화면출력(Game 게임) {
        int num = 1;
        ArrayList<KitchenIngredient> 주방재료들 = new ArrayList<>();
        System.out.println("\n<재료 업그레이드>");
        System.out.println("--베이스 재료----------------------");
        for (int i = 0; i < 게임.베이스재료들.size(); i++){
            BaseIngredient 베이스재료 = 게임.베이스재료들.get(i);
            주방재료들.add(베이스재료);
            ArrayList<HashMap> 베이스재료가격표 = 베이스재료.가격표;
            System.out.println(재료가격출력(num++, 베이스재료.이름, 베이스재료.품질, 베이스재료가격표 ));
        }

        System.out.println("\n--토핑 재료----------------------");
        for (int i = 0; i < 게임.토핑대들.size(); i++){
            ToppingIngredient 토핑재료 = 게임.토핑대들.get(i).토핑재료;
            주방재료들.add(토핑재료);
            ArrayList<HashMap> 토핑재료가격표 = 토핑재료.가격표;
            System.out.println(재료가격출력(num++, 토핑재료.이름, 토핑재료.품질, 토핑재료가격표 ));
        }

        System.out.println("\n--음료 재료----------------------");
        for (int i = 0; i < 게임.음료디스펜서.size(); i++){
            BeverageIngredient 음료재료 = 게임.음료디스펜서.get(i).음료재료;
            주방재료들.add(음료재료);
            ArrayList<HashMap> 음료재료가격표 = 음료재료.가격표;
            System.out.println(재료가격출력(num++, 음료재료.이름, 음료재료.품질, 음료재료가격표 ));
        }

        while(true){
            System.out.println("\n:업그레이드할 재료 번호를 입력해주세요. (상점으로 돌아가려면 "+num+"을(를) 입력해주세요.)");
            String 대답 = 범위내_숫자대답받기(1,num);
            if(대답.equals(Integer.toString(num))){상점_화면출력(게임); break; }
            else{ // 최고 품질인지 아닌지 확인
                KitchenIngredient 주방재료 = 주방재료들.get(Integer.parseInt(대답)-1);
                if(주방재료.품질 < Game.재료최고품질){
                    System.out.println("'"+주방재료.이름+"' 재료를 " +
                        "업그레이드("+주방재료.가격표.get(주방재료.품질).get("업그레이드가격")+"코인) 하시겠습니까? (1.예/2.아니오)");
                    String 업그레이드대답 = 범위내_숫자대답받기(1,num);
                    if(업그레이드대답.equals("1")){
                        boolean 재료업그레이드완료 = 주방재료.재료업그레이드(게임.게임유저);
                         if(재료업그레이드완료){ 재료업그레이드_화면출력(게임);  break; }
                         // 품질이 업그레이드된 목록을 보여주기 위해 <재료 업그레이드> 화면을 다시 출력해야됨
                        // -> 반복문 탈출하여 다시 함수 호출
                    }else if(업그레이드대답.equals("2")){continue;}
                }else{
                    System.out.println(": '"+주방재료.이름+"'은(는) 최고 품질로 더이상 업그레이드를 할 수 없습니다.");
                    continue;
                }
            }
        } //end while
        // TODO: 화면 돌아가는 위치 - 반복문에서 탈출하하여 불린으로 판단하여 화면 전환해도 괜찮은 시나리오일지..>
//        if(상점돌아가기) 상점_화면출력(게임);
//        else if(재료업그레이드완료) 재료업그레이드_화면출력(게임);
    }


    public String 장비가격출력(int no, String 장비이름, int 품질, ArrayList<HashMap> 장비가격표 ){
        String result = null;
        if(품질==0){
            result = no + ". " + 장비이름 + "(없음)   " + 장비가격표.get(품질).get("장비시간")+"초                      " +
                            "---->>>구매 "+장비가격표.get(품질).get("업그레이드가격")+"코인<<<----";
        }else if(품질<Game.장비최고품질){
            result = no+". " + 장비이름 + "(품질" + 품질 + ") " + 장비가격표.get(품질-1).get("장비시간") + "초" +
                      " -> " + 장비이름 + "(품질" + (품질+1) + ") " + 장비가격표.get(품질).get("장비시간")+"초  " +
                        "--->>>업그레이드 " + 장비가격표.get(품질).get("업그레이드가격") + "코인<<<---";
        }else{ result = no + ". " + 장비이름 + "(품질" + 품질 + ") " + 장비가격표.get(품질-1).get("장비시간") + "초\t\t\t----------최고 품질----------"; }
        return result;
    }
    public void 장비업그레이드_화면출력(Game 게임) {
        int num = 1;
        ArrayList<KitchenEquipment> 주방장비들 = new ArrayList<>();
        System.out.println("\n<장비 업그레이드>");
        System.out.println("--조리 시간 업그레이드-----------------------");
        for (int i = 0; i < 게임.레인지들.size(); i++){
            Range 레인지 = 게임.레인지들.get(i);
            ArrayList<HashMap> 레인지가격표 = 레인지.가격표;
            // TODO: 품질 0은 업그레이드가 아니라 구매로 표시하기
            System.out.println(장비가격출력(num++, 레인지.이름, 레인지.품질, 레인지가격표 ));
            주방장비들.add(레인지);
        }
        System.out.println("\n--토핑 시간 업그레이드----------------------");
        for (int i = 0; i < 게임.토핑대들.size(); i++){
            ToppigTable 토핑대 = 게임.토핑대들.get(i);
            ArrayList<HashMap> 토핑대가격표 = 토핑대.가격표;
            System.out.println(장비가격출력(num++, 토핑대.이름, 토핑대.품질, 토핑대가격표 ));
            주방장비들.add(토핑대);
        }
        System.out.println("\n--음료 따르는 시간 업그레이드----------------");
        for (int i = 0; i < 게임.음료디스펜서.size(); i++){
            Dispensor 음료디스펜서 = 게임.음료디스펜서.get(i);
            ArrayList<HashMap> 음료디스펜서가격표 = 음료디스펜서.가격표;
            System.out.println(장비가격출력(num++, 음료디스펜서.이름, 음료디스펜서.품질, 음료디스펜서가격표 ));
            주방장비들.add(음료디스펜서);
        }

        while(true){
            System.out.println("\n:업그레이드할 장비 번호를 입력해주세요. (상점으로 돌아가려면 "+num+"을(를) 입력해주세요.)");
            String 대답 = 범위내_숫자대답받기(1,num);
            if(대답.equals(Integer.toString(num))){상점_화면출력(게임); break; }
            else{ // 최고 품질인지 아닌지 확인
                KitchenEquipment 주방장비 = 주방장비들.get(Integer.parseInt(대답)-1);
                if(주방장비.품질 < Game.장비최고품질){
                    String 업그레이드or구매 = 주방장비.품질>0? "업그레이드" : "구매";
                    System.out.println("'"+주방장비.이름+"' 재료를 " +
                        업그레이드or구매 + "("+주방장비.가격표.get(주방장비.품질).get("업그레이드가격")+"코인) 하시겠습니까? (1.예/2.아니오)");
                    String 업그레이드대답 = 범위내_숫자대답받기(1,num);
                    if(업그레이드대답.equals("1")){
                        boolean 장비업그레이드완료 = 주방장비.장비업그레이드(게임.게임유저);
                         if(장비업그레이드완료){ 장비업그레이드_화면출력(게임);  break; }
                         // 품질이 업그레이드된 목록을 보여주기 위해 <재료 업그레이드> 화면을 다시 출력해야됨
                        // -> 반복문 탈출하여 다시 함수 호출
                    }else if(업그레이드대답.equals("2")){continue;}
                }else{
                    System.out.println(": '"+주방장비.이름+"'은(는) 최고 품질로 더이상 업그레이드를 할 수 없습니다.");
                    continue;
                }
            }
        } //end while

    }

    public void 아이템구매_화면출력(Game 게임) {
        
    }
//end 상점/////////////////////////////////////////////////////









    public void 일시정지(Game 게임){
        String 대답;
        System.out.println("""
                    \n<일시정지>
                    1. 게임 재개
                    2. 다시 하기
                    3. 게임 종료 - 메인화면으로 돌아가기
                    """);
        System.out.println(": 숫자를 입력해주세요.");
        대답 = 범위내_숫자대답받기(1, 3);
        switch(Integer.parseInt(대답)){
            case 1:
                게임.gameFrame.프레임.setVisible(true);
                게임.일시정지 = false;
                break;
            case 2:
                게임.게임시작();
                게임.일시정지 = false;
                break;
            case 3:
                System.out.println("게임을 종료합니다.");
                메인화면출력(게임);
                break;
        }
    }


    public boolean 숫자이다(String str){
        boolean result = true;
        if(str == null || str.length() == 0) result = false;
        else{
            for (int i = 0; i < str.length(); i++){
                if((int)str.charAt(i) < 48  || (int)str.charAt(i) > 57) result=false;
            }
        }
        return result;
    }
    public String 범위내_숫자대답받기(int min, int max){
        String 대답 = null;
        while(true){
            System.out.print("> " );
//            System.out.println(1111);
            대답 = 스캐너.nextLine();
//            System.out.println(22222);
            System.out.println("대답  : " + 대답);
            if(!숫자이다(대답)){
                System.out.println("숫자가 아닙니다. 숫자를 입력해주세요.");
            } else if(Integer.parseInt(대답) < min || max < Integer.parseInt(대답)){
                System.out.println("범위를 벗어났습니다. 보기에 있는 숫자를 입력해주세요.");
            } else break;
        }
        return 대답;
    }
}
