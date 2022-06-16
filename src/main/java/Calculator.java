import java.io.BufferedReader;
import java.io.InputStreamReader;
enum RomanianNumber {
    //    I(1), II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10);
    I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),XI(11),XII(12),XIII(13),XIV(14),XV(15),
    XVI(16),XVII(17),XVIII(18),XIX(19),XX(20),XXI(21),XXII(22),XXIII(23),XXIV(24),XXV(25),XXVI(26),XXVII(27),XXVIII(28),
    XXIX(29),XXX(30),XXXI(31),XXXII(32),XXXIII(33),XXXIV(34),XXXV(35),XXXVI(36),XXXVII(37),XXXVIII(38),XXXIX(39),XL(40),
    XLI(41),XLII(42),XLIII(43),XLIV(44),XLV(45),XLVI(46),XLVII(47),XLVIII(48),XLIX(49),L(50),LI(51),LII(52),LIII(53),
    LIV(54),LV(55),LVI(56),LVII(57),LVIII(58),LIX(59),LX(60),LXI(61),LXII(62),LXIII(63),LXIV(64),LXV(65),LXVI(66),LXVII(67),
    LXVIII(68),LXIX(69),LXX(70),LXXI(71),LXXII(72),LXXIII(73),LXXIV(74),LXXV(75),LXXVI(76),LXXVII(77),LXXVIII(78),LXXIX(79),
    LXXX(80),LXXXI(81),LXXXII(82),LXXXIII(83),LXXXIV(84),LXXXV(85),LXXXVI(86),LXXXVII(87),LXXXVIII(88),LXXXIX(89),XC(90),XCI(91),
    XCII(92),XCIII(93),XCIV(94),XCV(95),XCVI(96),XCVII(97),XCVIII(98),XCIX(99),C(100);
    private final int i;
    RomanianNumber(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
enum Operation{
    PLUS("+"), MINUS("-"), MULTIPLICATION("*"), DIVISION("/");

    private String symbol;
    Operation(String symbol){
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
    public static void calculate(int a, int b, Operation operation){
        switch (operation){
            case PLUS:
                System.out.println(a+b);
                break;
            case MINUS:
                System.out.println(a-b);
                break;
            case MULTIPLICATION:
                System.out.println(a*b);
                break;
            case DIVISION:
                System.out.println(a/b);
                break;
            default:
                System.out.println("Калькулятор умеет проводить следующие операции: '+','-','*','/'. \nПожалуйста повторите попытку");
        }
    }

}
public class Calculator {
    private static String aOperand, bOperand;
    private static int a = 0;
    private static int b = 0;
    public static Operation hasOperation(String line){
        for(Operation op:Operation.values()){
            if(line.contains(op.getSymbol())) return op;
            System.out.println(op);
            break;
        }
        return null;
    }
    public static String calc(String input){
        String result=null;
        Operation operation =null;
        int index=0;
        int countOperations=0;

        try {

            for(int i=0;i<input.length();i++){
                for(Operation value:Operation.values()){
                    if ((value.getSymbol().equals(String.valueOf(input.charAt(i))))){
                        countOperations++;
                        operation=value;
                        index=i;
                    }
                }
            }
            if (countOperations!=1) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            //-----------------------------------------------
            countOperations=0;
            aOperand =input.substring(0,index);
            bOperand =input.substring(index+1,input.length());
            for(RomanianNumber r:RomanianNumber.values()){
//                input.substring(0,index).contains(r.name());
                if(aOperand.equals(r.toString())) {
                    a = r.getI();
                    countOperations++;
                }
                if(bOperand.equals(r.toString())) {
                    b = r.getI();
                    countOperations++;
                }
            }
            if(countOperations==2){
                switch (operation.getSymbol()){
                    case("+"):
                        result = String.valueOf(a+b);
                        break;
                    case("-"):if((a-b)<=0){throw new Exception("Результат не может быть отрицательным или равным нулю");}
                    else result = String.valueOf(a-b);
                        break;
                    case("*"):result = String.valueOf(a*b);
                        break;
                    case("/"):if((a/b)<=0){throw new Exception("Результат деления меньше I");}
                    else result = String.valueOf(a/b); // результат может быть нулевым
                        break;
                }
                for(RomanianNumber r:RomanianNumber.values()){
                    if(r.getI()==Integer.parseInt(result)){
                        result = r.name();
                    }
                }
            }else throw new Exception("введенные цифры не римские");
            //-----------------------------------------------


        }catch (NullPointerException e){
            System.out.println("Калькулятор умеет проводить следующие операции: '+','-','*','/'. \nПожалуйста повторите попытку");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line =reader.readLine();
            System.out.println(calc(line));


//            System.out.println(line.charAt(index)+" "+index+" "+line.charAt(line.length()-1));
//            int a = Integer.parseInt(line.substring(0,index));
//            int b = Integer.parseInt(line.substring(index+1,line.length()));
//            System.out.println(a);
//            System.out.println(b);

        }catch (NullPointerException e){
            System.out.println("Калькулятор умеет проводить следующие операции: '+','-','*','/'. \nПожалуйста повторите попытку");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

