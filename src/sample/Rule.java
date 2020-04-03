package sample;

public class Rule {
    private boolean [] rules = new boolean[8];

    public Rule(int rule){

        String str = Integer.toBinaryString(rule);
        char[] chars = str.toCharArray();
        int counter = 8 - chars.length;
        for(int i = 0; i < counter; i++){
            rules[i] = false;
        }
        for(int i = 0; i <chars.length; i++){
            rules[i+counter] = (chars[i] == '1') ? true : false;
        }


    }


    public boolean nextValue(boolean prev, boolean middle, boolean next){
        if(prev && middle && next)
            return rules[0];
        else if(prev && middle && !next)
            return rules[1];
        else if(prev&& !middle && next)
            return rules[2];
        else if(prev && !middle && !next)
            return rules[3];
        else if(!prev && middle && next)
            return rules[4];
        else if(!prev && middle && !next)
            return rules[5];
        else if(!prev && !middle && next)
            return rules[6];
        else
            return rules[7];
    }
}
