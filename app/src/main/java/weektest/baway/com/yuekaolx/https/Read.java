package weektest.baway.com.yuekaolx.https;

import java.io.IOException;
import java.io.InputStream;

public class Read {
    public static Read read=new Read();
    public static Read getInstance(){
        return read;
    }
    public String getread(InputStream inputStream) throws Exception {
        int len=-1;
        byte[] bytes=new byte[1024];
        StringBuffer stringBuffer=new StringBuffer();
        while ((len=inputStream.read(bytes))!=-1){
            String s=new String(bytes,0,len);
            stringBuffer.append(s);

        }
        return stringBuffer.toString();
    }
}
