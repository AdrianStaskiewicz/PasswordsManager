package algorithms;

public class TextConverter {

    private int power(int base, int exponent){
        int output=1;
        for(int i=1;i<=exponent;i++){
            output=output*base;
        }
        return output;
    }

    private String decimalToBinary(char input){
        int sign = input;
        String output="";
        for(int i=0;i<8;i++){
            output=(sign%2)+output;
            sign=(sign/2);
        }
        return output;
    }
    private int binaryToDecimal(String input){
        int output=0;
        for(int i=0;i<input.length();i++){
            output=output+charToInt(input.charAt(i))*power(2,(input.length()-1-i));
        }
        return output;
    }

    private int charToInt(char input){
        return input-48;
    }

    private char intToChar(int input){
        return (char)input;
    }

    private String textToBinary(String data){
        String output="";
        for(int i=0;i<data.length();i++){
            output=output+decimalToBinary(data.charAt(i));
        }
        //System.out.println(output);
        return output;
    }

    private String textFromBinary(String data){
        String output="";
        for(int i=0;i<data.length();i=i+8) {
            output=output+intToChar(binaryToDecimal(data.substring(i,i+8)));
        }
        //System.out.println(output);
        return output;
    }

    private String alignLength(String input, int length){
        String output="";
        int i=0;
        while(output.length()!=length){
            output=output+input.charAt(i);
            i++;
            if(i==input.length()){
                i=0;
            }
        }
        return output;
    }

    private String xorText(String key,String input){
        String output="";
        for(int i=0;i<input.length();i++){
            if(key.charAt(i)==input.charAt(i)){
                output=output+'0';
            }
            else{
                output=output+'1';
            }
        }
        return output;
    }

    public String encryptText(String masterKey, String input){
        String textA = textToBinary(alignLength(masterKey,input.length()));
        String textB = textToBinary(input);
        String textC = xorText(textA,textB);
        System.out.println("ZASZYFROWANY:");
        System.out.println(textFromBinary(textC));
        return textC;
    }

    public String decryptText(String masterKey, String input){
        String textA = textToBinary(alignLength(masterKey,input.length()));
        //String textB = textToBinary(input);
        String textC = xorText(textA,input);
        System.out.println("ODSZYFROWANY:");
        System.out.println(textFromBinary(textC));
        return textC;
    }

}

