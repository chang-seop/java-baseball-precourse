package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static boolean isRandomReload = true;
    public static int strikeCount = 0;
    public static int ballCount = 0;
    public static List<String> randomArray = new ArrayList<>();

    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        run();
    }

    public static void run() {
        do {
            countInit();
            randomInit();
            judgment(customInput()); // 판결
            ballStrikePrint();
        } while (!isReboot());
    }

    public static void countInit() {
        strikeCount = 0;
        ballCount = 0;
    }

    public static void randomInit() {
        if(isRandomReload) {
            randomArray.clear();
            for (int i = 0; i < 3; i++) { // 3번
                randomArray.add(String.valueOf(Randoms.pickNumberInRange(1, 9)));
            }
            isRandomReload = false;
        }
    }

    public static String customInput() {
        System.out.print("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public static void judgment(String inputValue) {
        if(inputValue.length() > 3 || inputValue.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < inputValue.length(); i++) {
            int temp = Integer.parseInt(String.valueOf(inputValue.charAt(i)));
            if(temp < 1 || temp > 9) {
                throw new IllegalArgumentException();
            }

            if(randomArray.get(i).equals(String.valueOf(temp))) {
                strikeCount++;
            }

            else if(randomArray.contains(String.valueOf(temp))) {
                ballCount++;
            }
        }
    }

    public static void ballStrikePrint() {
        StringBuilder sb = new StringBuilder();
        if(ballCount > 0) {
            sb.append(ballCount).append("볼");
        }

        if(strikeCount > 0) {
            if(ballCount > 0) sb.append(" ");
            sb.append(strikeCount).append("스트라이크");
        }

        if(ballCount == 0 && strikeCount == 0) {
            System.out.println("낫싱");
        } else {
            System.out.println(sb);
        }
    }

    public static boolean isReboot() {
        if(strikeCount == 3) {
            System.out.println("3개의 숫자를 모두 맞히였습니다! 게임 종료\n 게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String inputNumber = Console.readLine();
            if(inputNumber.equals("1")) {
                isRandomReload = true;
            }

            return inputNumber.equals("2");
        }

        return false;
    }
}
