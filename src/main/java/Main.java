import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isRomanianNumber(String str){
        for(RomanNumber r: RomanNumber.values()){
            if(str.equals(r.name())){return true;}
        }
        return false;
    }
    public static String deleteSpaces(String str){
        if(str.contains(" ")){
            String[] data = str.split(" ");
            int count =0;
            for (String datum : data) {
                if (!datum.equals("")) count++;
            }
            if(count==1){
                return str.trim();
            }else try {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return str;
    }

    public static String calc(String input){
        String result="Результат отсутствует\n";
        Operator operation =null;
        int index=0;
        int countOperations=0;
        String aOperand;
        String bOperand;
        int a = 0;
        int b = 0;

        try {
            for(int i=0;i<input.length();i++){
                for(Operator value: Operator.values()){
                    if ((value.getSymbol().equals(String.valueOf(input.charAt(i))))){
                        countOperations++;
                        operation=value;
                        index=i;
                    }
                }
            }

            if (countOperations==0) throw new Exception("строка не является математической операцией");//если операторы в выражении отсутствуют то выбрасываем исключение
            if (countOperations!=1) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

            aOperand = deleteSpaces(input.substring(0,index));
            bOperand = deleteSpaces(input.substring(index+1));

            if(isNumeric(aOperand)&&isRomanianNumber(bOperand)) throw new Exception("используются одновременно разные системы счисления");
            if(isNumeric(bOperand)&&isRomanianNumber(aOperand)) throw new Exception("используются одновременно разные системы счисления");

            if(isNumeric(aOperand)&&isNumeric(bOperand)){
                try{
                    a=Integer.parseInt(aOperand);
                    b=Integer.parseInt(bOperand);
                    if(a<1||a>=10) throw new Exception("Введите число от 1 до 10 включительно");
                    if(b<1||b>=10) throw new Exception("Введите число от 1 до 10 включительно");
                    switch (operation.getSymbol()){
                        case("+"):
                            result = String.valueOf(a+b);
                            break;
                        case("-"):
                            result = String.valueOf(a-b);
                            break;
                        case("*"):
                            result = String.valueOf(a*b);
                            break;
                        case("/"):
                            result = String.valueOf(a/b);
                            break;
                        default: throw new Exception("Введенный оператор не соответствует одному из следующих (+, -, /, *)");
                        }
                    }catch(NumberFormatException ignored){
                }
            }else if(isRomanianNumber(aOperand)&&isRomanianNumber(bOperand)){
                for(RomanNumber r: RomanNumber.values()){
                    if(aOperand.equals(r.toString())) a = r.getRomanNumber();
                    if(bOperand.equals(r.toString())) b = r.getRomanNumber();
                    if(a<1||a>=10) throw new Exception("Введите число от I до X включительно");
                    if(b<1||b>=10) throw new Exception("Введите число от I до X включительно");
                }

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
                        else result = String.valueOf(a/b);
                            break;
                        default: throw new Exception("Введенный оператор не соответствует одному из следующих (+, -, /, *)");
                    }

                    for(RomanNumber r: RomanNumber.values()){
                        if(r.getRomanNumber()==Integer.parseInt(result)){
                            result = r.name();
                        }
                    }
            }
//            else throw new Exception("Введенные данные не являются арабскими/римскими цифрами. Повторите запрос");
        }catch (NumberFormatException ignored) {}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
                System.out.println("Введите любую операцию из (+, -, /, *). Для выхода введите команду: \"exit\"");
                String line =reader.readLine();
                if(line.equals("exit")) break;
                System.out.println(calc(line));
                System.out.println("===========================");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

