package wtb.smUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
 

import wtb.core.model.GeneratorResult;
 
 
public class FKSequenceGenerator {
    protected String rule = "{head}{date}{sequence}";
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
    protected String date = dateFormat.format(new Date());
     
    private Long c = null;
     
//    @Test
//    public void Generator7() {
//        System.out.println("7位:");
//        System.out.println(generatorAuto("test",7).getID());
//         
//        c = 10000000l;
//         
//        System.out.println("8位:");
//        System.out.println(generatorAuto("test",7).getID());
//        System.out.println(generatorAuto("test",7).getID());
//        System.out.println(generatorAuto("test",7).getID());
//         
//        c = 100000000l;
//        System.out.println("9位:");
//        System.out.println(generatorAuto("test",7).getID());
//        System.out.println(generatorAuto("test",7).getID());
//        System.out.println(generatorAuto("test",7).getID());
//    }
     
    public GeneratorResult generator(String head,int length){
        Long maxSequence = new Double(Math.pow(10, length)).longValue();
        return generator(head,length,null,maxSequence);
    }
     
    public GeneratorResult generatorAuto(String head,int len){
        c = c == null ? 1 : c+1;
        Long sequence = c;
        Long maxSequence = new Double(Math.pow(10, len)).longValue();
        return generator(head,len,sequence,maxSequence);
    }
     
    public GeneratorResult generatorAuto(String head){
        c = c == null ? 1 : c+1;
        Long sequence = c;
        int len = Long.valueOf(sequence).toString().length();
        return generator(head,len,sequence,c * 10);
    }
     
    private GeneratorResult generator(String head,int length,Long currentVal,Long maxSequence) {
        Object[] objs = new Object[]{head,length,currentVal,maxSequence};
        return generator(objs);
    }
     
    private GeneratorResult generator(Object obj) {
        Object[] objs = (Object[])obj;
        String head = (String)objs[0];
        Integer length = (Integer)objs[1];
        Long currentVal = (Long)objs[2];
        Long maxSequence = (Long)objs[3];
        GeneratorResult result = new GeneratorResult();
         
        Long currSequence = currentVal;
        String rule = this.rule+"";
        rule = rule.replace("{head}", (head == null?"":head));
        rule = rule.replace("{date}", date);
        if(currSequence > (maxSequence-1)){
            return generator(head, length+1,currSequence, maxSequence * 10);
        }else{
            String sequenceStr = "";
            int ws = (currSequence+"").length();
            for (int i = 0;i<length;i++) {
                sequenceStr += "0";
            }
            rule = rule.replace("{sequence}", sequenceStr.substring(0, sequenceStr.length() - ws)+currSequence+"");
        }
        result.setID(rule);
        return result;
    }
}