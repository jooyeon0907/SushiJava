import java.util.Scanner;

public class GameConsole {
    Scanner 스캐너 = new Scanner(System.in);

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
        대답 = 숫자대답받기(1, 4);
        switch(대답){
            case "1":
                게임.게임시작();
                break;
            case "2":
                나의정보출력(게임);
                break;
            case "3":
                break;
            case "4":
                System.out.println("스시자바를 종료합니다.");
                System.exit(0);
                break;
        }//end switch
    }

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
        대답 = 숫자대답받기(1, 5);
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
    public void 메인화면으로돌아가기(Game 게임){
        System.out.println("\n1. 메인화면으로 돌아가기");
        String 대답 = 숫자대답받기(1, 1);
        if(대답.equals("1")) 메인화면출력(게임);
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
        System.out.println("--화구--------------------------------");
        System.out.println("이름\t\t\t품질\t\t\t조리시간");
        System.out.println("-------------------------------------");
        for (int i = 0; i < 게임.화구들.size(); i++){
            Range 화구 = 게임.화구들.get(i);
            System.out.println(화구.이름 + "\t\t\t" +  화구.품질  +"\t\t\t" + 화구.조리시간);
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

    public void 일시정지(Game 게임){
        System.out.println("""
                    \n<일시정지>
                    1. 게임 재개
                    2. 다시 하기
                    3. 게임 종료
                    """);
            System.out.println(": 숫자를 입력해주세요.");
            String 대답 = 숫자대답받기(1, 3);
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
    public String 숫자대답받기(int min, int max){
        String 대답 = null;
        while(true){
                System.out.print("> " );
                대답 = 스캐너.nextLine();
                if(!숫자이다(대답)){
                    System.out.println("숫자가 아닙니다. 숫자를 입력해주세요.");
                } else if(Integer.parseInt(대답) < min || max < Integer.parseInt(대답)){
                    System.out.println("범위를 벗어났습니다. 보기에 있는 숫자를 입력해주세요.");
                } else break;
            }
        return 대답;
    }

}
