import java.util.*;

public class errorNumber {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Map<String,Integer> map=new LinkedHashMap<>();
        while(sc.hasNext()){
            String path=sc.next();
            int numline=sc.nextInt();
            int pos=path.lastIndexOf('\\');
            String fileName=path.substring(pos+1);
            String keyName=fileName+" "+numline;
            if(!map.containsKey(keyName)){
                map.put(keyName,1);
            }
            else{
                map.put(keyName,map.get(keyName)+1);
            }
        }
        List<Map.Entry<String,Integer>> listmap=new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(listmap,new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Map.Entry<String,Integer> en1, Map.Entry<String,Integer> en2){
                return en2.getValue().compareTo(en1.getValue());
            }
        }
        );
        int i=1;
        for(Map.Entry<String,Integer> entry:listmap){
            if(i<=8){
                String keyNameTemp=entry.getKey();
                int posT=keyNameTemp.lastIndexOf(" ");
                String fileNameTemp=keyNameTemp.substring(0,posT);
                String numlineT=keyNameTemp.substring(posT+1);
                fileNameTemp=fileNameTemp.length()>16?fileNameTemp.substring(fileNameTemp.length()-16,fileNameTemp.length()):fileNameTemp;
                System.out.println(fileNameTemp+" "+numlineT+" "+entry.getValue());
                i++;
            }
            else
                break;
        }
    }

}
