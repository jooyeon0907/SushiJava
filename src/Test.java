import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static <Char> void main(String[] ags){
        ArrayList<String> 주문메뉴들;
        String 서빙음식;
        // ArrayList 포함여부
        if(1==0) {
            주문메뉴들 = new ArrayList<String>();
            주문메뉴들.add("연어초밥");
            주문메뉴들.add("장어초밥");
            주문메뉴들.add("장어라멘");

            서빙음식 = "장어초밥";

            System.out.println("서빙 : " + (주문메뉴들.contains(서빙음식)));
        }
        
        if(1==0){
            System.out.println(주문메뉴들); // [연어초밥, 장어초밥, 장어라멘]
        }

        // ArrayList 해당 값 index 찾기 , 삭제
        if(1==0){
            System.out.println("주문메뉴들.indexOf(서빙음식) : " + (주문메뉴들.indexOf(서빙음식)));
            System.out.println("주문메뉴들.indexOf(서빙음식) : " + (주문메뉴들.remove(서빙음식)));
            System.out.println(주문메뉴들);
        }

        // join
        StringBuffer sb = new StringBuffer();
        if(1==0){
            String 베이스 = "초밥";
            String 토핑 = "연어";
            베이스 = String.valueOf(sb.append(토핑).append(베이스));
            System.out.println(베이스);
        }

        //  is_number
        Scanner 스캐너 = new Scanner(System.in);
        String 대답;
        if(1==0){
            System.out.print("> ");
            대답 = 스캐너.nextLine();
            System.out.println(isNumber(대답));
        }

        // JButton htmlText
        if(1==0){
            // <html>
            // 첫번째 p 태그 - 이름
            // 두번째 p 태그 - 진행상태
            // 세번째 p 태그 - 시간
            // </html>
            GameFrame gameFrame = new GameFrame();

            String 이름태그 = "<p>"+"쌀"+"</p>";
            String 진행상태태그 = "<p>"+"조리중"+"</p>";
            String 시간태그 = "<p>"+"00:10"+"</p>";
            String htmlText = "<html>" + 이름태그 + 진행상태태그 + 시간태그 + "</html>";
            gameFrame.레인지들[0].setText(htmlText);

            이름태그 = "<p>"+"탄 밥"+"</p>";
            진행상태태그 = "<p>"+"오버쿡"+"</p>";
            시간태그 = "<p>"+""+"</p>";
            htmlText = "<html>" + 이름태그 + 진행상태태그 + 시간태그 + "</html>";
            gameFrame.레인지들[1].setText(htmlText);

            이름태그 = "<p>"+"연어초밥"+"</p>";
            진행상태태그 = "<p>"+"토핑완료"+"</p>";
            시간태그 = "<p>"+""+"</p>";
            htmlText = "<html>" + 이름태그 + 진행상태태그 + 시간태그 + "</html>";
            gameFrame.토핑재료들[0].setText(htmlText);
        }

        // p 태그 붙이기
        if(1==1){
            System.out.println(p태그붙이기("콜라"));
            System.out.println(p태그붙이기("조리중"));

            System.out.println(태그포맷팅("p",  new String[]{"10초"}));
            String[] p태그들 = new String[]{};
            p태그들 = new String[]{태그포맷팅("p",  new String[]{"10초"}), 태그포맷팅("p",  new String[]{"11초"})};
//            p태그들 = new String[]{ 태그포맷팅("p",  new String[]{"11초"})};
            System.out.println(태그포맷팅("html", p태그들));
        }

    } //end main


    static public String p태그붙이기(String 값){
        StringBuffer sb = new StringBuffer();
        return String.valueOf(sb.append("<p>").append(값).append("</p>"));
     }
     static public String 태그포맷팅(String 태그, String 값){ // 1개
        StringBuffer sb = new StringBuffer();
        return String.valueOf(sb.append("<").append(태그).append(">").append(값).append("</").append(태그).append(">"));
     }
     static public String 태그포맷팅(String 태그, String[] 값들){ // 여러개
        StringBuffer sb = new StringBuffer();
        String 값 = "";
        for (int i = 0; i < 값들.length; i++) 값 += 값들[i];
        return String.valueOf(sb.append("<").append(태그).append(">").append(값).append("</").append(태그).append(">"));
     }

   static public boolean isNumber(String str){
        boolean result = true;
        if(str == null || str.length() == 0) result = false;
        else{
            for (int i = 0; i < str.length(); i++){
                if((int)str.charAt(i) < 48  || (int)str.charAt(i) > 57) result=false;
            }
        }
        return result;
    }


}
