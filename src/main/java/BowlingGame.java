import java.util.Arrays;

public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        //先分||
        bowlingCode = bowlingCode.replace("|", "%").replaceAll("%%", ",").replace("%", ",");
        String[] tempstr = bowlingCode.split(",");
        System.out.println(tempstr.length);
        int in = 0;
        for (String e : tempstr) {
            System.out.print(in + ":" + e + " ");
            in++;
        }
        System.out.println();
        int size = tempstr.length;
        String[] OperationStr = tempstr;

        int[] score = new int[size];
        int MaxDigit = -1;
        for (int i = 0; i <= 9; i++) {
            //查询当前投入读下面几个投球
            //x--2,存在/--1 存在- --0
            System.out.println(i + "[访问]" + OperationStr[i]);
            if (OperationStr[i].contains("X")) {
                MaxDigit = 2;
                score[i] = 10;
            } else if (OperationStr[i].contains("/")) {
                MaxDigit = 1;
                score[i] = 10;
            } else if (OperationStr[i].contains("-")) {
                MaxDigit = 0;
                String s = OperationStr[i].replace("-", "0");
                score[i] = Integer.valueOf(String.valueOf(s.charAt(0)))
                        + Integer.valueOf(String.valueOf(s.charAt(1)));
            } else if (!OperationStr[i].equals("")) {
                //Integer.valueOf(OperationStr[i])>0&&Integer.valueOf(OperationStr[i])<=9
                //两次都是正常的数字，相加即可
                System.out.println(OperationStr[i]);
                MaxDigit = 0;
                score[i] = Integer.valueOf(String.valueOf(OperationStr[i].charAt(0)))
                        + Integer.valueOf(String.valueOf(OperationStr[i].charAt(1)));
            }

            int j = i + 1;
            int index = 0;
            while (index < MaxDigit && j < size) {
                String s = OperationStr[j];
                int s_index = 0;
                while (index < MaxDigit && s_index <= s.length() - 1) {
                    char ch = s.charAt(s_index);
                    if (ch == 'X') {
                        score[i] += 10;
                    } else if (ch != '/' && ch != '-') {
                        score[i] += Integer.valueOf(String.valueOf(ch));
                    }else if(ch=='/'&&s_index!=0){
                        score[i]+=(10-Integer.valueOf(String.valueOf(s.charAt(s_index-1))));
                    }
                    s_index++;
                    index++;
                }
                j = j + 1;
            }
            System.out.println(index);
        }
        //将score相加
        int Sum = 0;
        for (int i = 0; i < score.length; i++) {
            System.out.println(i + "--" + score[i]);
            Sum = Sum + score[i];
        }
        return Sum;
    }

}
