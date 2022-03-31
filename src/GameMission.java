import java.util.ArrayList;
import java.util.HashMap;

public class GameMission {
    int 목표매출;
    int 게임시간;
    int 손님수;
    int 손님만족도수;
    int 음식판매수;
    boolean 불만족손님없기; // 손님 기다리는시간이 0 초가 되어서 손님이 떠날 때 게이밈션.불막존손님없기가 true로 설정되었다며 게임종료
    boolean 음식버리지않기; // 음식을 버렸을 때 게임미션.음식버리지않기가 true 로 설정되어있다면 게임종료
    boolean 음식태우지않기; // 음식이 탔을 때 게임미션.음식태우기가 true 로 설정되어있다면 게임종료
//    HashMap<String, Integer> 추가조건;
    boolean 게임결과;

    public GameMission(){
        this.목표매출 = 0;
        this.게임시간 = 0;
        this.손님수 = 0;
        this.손님만족도수 = 0;
        this.음식판매수 = 0;
        this.불만족손님없기 = false; // 손님 기다리는시간이 0 초가 되어서 손님이 떠날 때 게이밈션.불막존손님없기가 true로 설정되었다며 게임종료
        this.음식버리지않기 = false; // 음식을 버렸을 때 게임미션.음식버리지않기가 true 로 설정되어있다면 게임종료
        this.음식태우지않기 = false; // 음식이 탔을 때 게임미션.음식태우기가 true 로 설정되어있다면 게임종료
    }



    public void 게임미션셋팅테스트(int 게임레벨){
        HashMap<String, Object> 게임난이도설정 = new HashMap<>();
        switch(게임레벨){
            case  1:
                게임난이도설정.put("목표매출", 3720);
                게임난이도설정.put("음식버리지않기", 3720);
            // {목표매출 : 3720 , 게임시간 = 48, 손님수: 3}
            break;

          default:
            break;
        }

    }
    public void 게임미션셋팅(int 게임레벨){ // TODO
        /*
        게임레벨이 1~5라면
        손님수 3~5명 랜덤수
        손님스레드생성되는 빈도수
        레벨에 따라 설정되는 변수 및 미션
		- 게임시간 설정
		- 목표매출 설정 션
		- 음식판매횟수 설정
		- 응대해야할 손님수 설정
		- 만족도횟수 (손님기분좋은상태)
		- 불만족 손님 금지(기다리는 시간이 0초가 되면 손님이 떠남)
		- 음식 버리지 않기
        - 음식 태우지 않기
        -> 레벨에 따라 조건개수 달라짐
         */

//        추가조건 = new HashMap<>();
        switch(게임레벨){
            case  1:
                this.목표매출 = 3720; this.게임시간 = 48;
                break;
            case  2:
                this.목표매출 = 3720; this.게임시간 = 48;
                this.손님수 = 3;
                break;
            case  3:
                this.목표매출 = 3720; this.게임시간 = 48;
                this.손님만족도수 = 2;
                break;
            case  4:
                this.목표매출 = 4960; this.게임시간 = 64;
                this.음식판매수 = 5;
                break;
            case  5:
                this.목표매출 = 4960; this.게임시간 = 64;
                this.불만족손님없기 = true;
                break;
            case  6:
                this.목표매출 = 9840; this.게임시간 = 96;
                this.음식태우지않기 = true;
                break;
            case  7:
                this.목표매출 = 9840; this.게임시간 = 96;
                break;
            case  8:
                this.목표매출 = 10320; this.게임시간 = 96;
                this.손님수 = 7; this.불만족손님없기 = true;
                break;
            case  9:
                this.목표매출 = 10320; this.게임시간 = 96;
                break;
            case  10:
                this.목표매출 = 19080; this.게임시간 = 100;
                this.음식태우지않기 = true; this.음식버리지않기 = true;
                break;
            case  11:
                this.목표매출 = 19080; this.게임시간 = 100;
                this.손님수 = 8;
                break;
            case  12:
                this.목표매출 = 19080; this.게임시간 = 100;
                this.음식판매수 = 10; this.음식태우지않기 = true;
                break;
            case  13:
                this.목표매출 = 19080; this.게임시간 = 100;
                this.손님수 = 8; this.손님만족도수 = 4;
                break;
            case  14:
                this.목표매출 = 19080; this.게임시간 = 100;
                this.손님수 = 10; this.음식버리지않기 = true;
                break;
            case  15:
                this.목표매출 = 24840; this.게임시간 = 100;
                this.손님만족도수 = 10;
                break;
            case  16:
                this.목표매출 = 24840; this.게임시간 = 100;
                this.음식태우지않기 = true; this.음식버리지않기 = true;
                break;
            case  17:
                this.목표매출 = 25560; this.게임시간 = 100;
                this.음식태우지않기 = true; this.음식버리지않기 = true; this.불만족손님없기 = true;
                break;
            case  18:
                this.목표매출 = 29820; this.게임시간 = 117;
                break;
            case  19:
                this.목표매출 = 29820; this.게임시간 = 117;
                this.손님수 = 10; this.음식판매수 = 16;
                break;
            case  21:
                this.목표매출 = 45440; this.게임시간 = 128;
                this.손님수 = 12; this.음식버리지않기 = true; this.불만족손님없기 = true;
                break;
            case  22:
                this.목표매출 = 46720; this.게임시간 = 128;
                this.손님수 = 10; this.음식판매수 = 16;
                this.불만족손님없기 = true;
                break;
            case  23:
                this.목표매출 = 49280; this.게임시간 = 128;
                break;
            case  24:
                this.목표매출 = 59760; this.게임시간 = 144;
                this.음식태우지않기 = true; this.음식버리지않기 = true;
                this.손님수 = 15;
                break;
            case  25:
                this.목표매출 = 61200; this.게임시간 = 144;
                this.음식태우지않기 = true; this.음식버리지않기 = true; this.불만족손님없기 = true;
                this.손님수 = 15;
                break;
            case  26:
                this.목표매출 = 62640; this.게임시간 = 144;
                this.음식태우지않기 = true; this.음식버리지않기 = true;
                this.손님수 = 15; this.손님만족도수 = 10;
                break;
            case  27:
                this.목표매출 = 69600; this.게임시간 = 160;
                this.음식태우지않기 = true; this.음식버리지않기 = true;
                this.손님수 = 15; this.음식판매수 = 22;
                break;
            case  28:
                this.목표매출 = 77600; this.게임시간 = 160;
                this.손님수 = 15; this.손님만족도수=10; this.음식판매수 = 22;
                break;
            case  29:
                this.목표매출 = 80800; this.게임시간 = 160;
                this.손님수 = 15; this.손님만족도수=10; this.음식판매수 = 22;
                break;
            case  30:
                this.목표매출 = 80800; this.게임시간 = 160;
                this.음식태우지않기 = true; this.음식버리지않기 = true; this.불만족손님없기 = true;
                this.손님수 = 20; this.손님만족도수=15; this.음식판매수 = 40;
                break;
            default:
                break;
        }

        Game.목표매출 = this.목표매출;
        Game.게임시간 = this.게임시간;
        Game.gameFrame.set목표매출(Game.목표매출); // TODO(22-03-27)

    }






    public void 게임미션결과출력(){
        if(this.게임결과){
            System.out.println("<게임 레벨 통과 완료!!>");
        }else{
            System.out.println("<게임 레벨 통과 실패..>");
        }
        System.out.println("달성 코인수 : " + Game.현재매출);
        System.out.println();
    }




}
