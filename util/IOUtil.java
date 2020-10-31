package util;
import java.io.*;

import util.Node;

public class IOUtil {
    public static String[] read(String path){
        StringBuffer str=new StringBuffer("");
        File file=new File(path);
        String[] data;
        try {
            FileInputStream in=new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(in);
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while ((line=br.readLine())!=null){
                str.append(line);
            }
            in.close();
            isr.close();
            br.close();
            data=str.toString().split(" ");
            return data;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void write(String[] src,String path){
        File file=new File(path);
        try {
            FileOutputStream out = new FileOutputStream(path);
            OutputStreamWriter osr=new OutputStreamWriter(out);
            for(int i=0;i<src.length;i++){
                osr.write(src[i]+" ");
            }
            osr.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
