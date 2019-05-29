import java.io.*;


public class file {

    public static void readFile(String filename) {
        StringBuffer str = new StringBuffer();
        try {
            char[] buf = new char[50];
            System.out.println(buf);
            FileReader f = new FileReader(filename);
            while (f.read(buf) > 0) {
//                System.out.println(buf);
                str.append(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

    public static void fileStream() throws IOException{
        File f=new File("test1.txt");
        FileOutputStream fileout=new FileOutputStream(f);
        OutputStreamWriter outwriter=new OutputStreamWriter(fileout,"utf-8");
        outwriter.append("中文输入：\n");
        outwriter.append("abc\t");
        outwriter.append("123");
        outwriter.close();

        FileInputStream fileinput=new FileInputStream(f);
        InputStreamReader inputwriter=new InputStreamReader(fileinput);
        StringBuffer str=new StringBuffer();
        while(inputwriter.ready()){
            str.append((char)inputwriter.read());
        }
        inputwriter.close();
        System.out.println(str);
    }

    public static void main(String[] args)throws IOException {
        String filename = "/home/steven/IdeaProjects/Demo/src/test.txt";
        readFile(filename);
        fileStream();
    }
}
