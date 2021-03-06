package com.maike.myblog.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @projectname GEEKCJJBlog
 * @author GEEKCJJ
 * @date 2019年10月1日 下午9:05:24
 * @description:
 *
 */
public class FileOperationUtil {
	public static void main(String[] args) throws IOException{
        // 新建文件夹和文件
        mkDirectory();
        //读取文件
        readFileContent();
        //修改文件
        modifyFileContent();
    }
    //读取文件的3种方式
    private static void readFileContent() throws IOException {
        // TODO Auto-generated method stub
        FileReader fr = new FileReader("E://a//s//PLAYLIST.LY");// 内容为：Love lives,Love java!爱生活，爱java!  
        int ch = 0;  
        while ((ch = fr.read()) != -1) {  
            System.out.print((char) ch);  
        }  
  
        InputStreamReader isr = new InputStreamReader(new FileInputStream(  
                "E://a//s//PLAYLIST.LY"));  
        while ((ch = isr.read()) != -1) {  
            System.out.print((char) ch);  
        }  
  
        BufferedReader br = new BufferedReader(new InputStreamReader(  
                new FileInputStream("E://a//s//PLAYLIST.LY")));  
        String data = null;  
        while ((data = br.readLine()) != null) {  
            System.out.println(data);  
        }  
        fr.close();  
        isr.close();  
        br.close(); 
    }
    //新建文件夹以及文件
    private static void mkDirectory() {
        // TODO Auto-generated method stub
        String mkDirectoryPath = "E:\\a\\s"; 
        File file = null;   
        file = new File(mkDirectoryPath);  
        if (!file.exists()) {  
            file.mkdirs();  
        }
        File writeFile = new File(mkDirectoryPath, "PLAYLIST.LY");
        if(!writeFile.exists()){
            try {
                writeFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /**
     * 修改文件内容的三种方式
     * @throws IOException 
     */
    private static void modifyFileContent() throws IOException {
        
        FileWriter fw = new FileWriter("E://a//s//PLAYLIST.LY");  
        String s = "Love lives, Love java!爱生活，爱java!";  
        fw.write(s, 0, s.length());  
        fw.flush();  
  
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(  
                "E://a//s//PLAYLIST.LY"));  
        osw.write(s, 0, s.length());  
        osw.flush();  
  
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(  
                new FileOutputStream("E://a//s//PLAYLIST.LY")), true);  
        pw.println(s);  
        
        fw.close();  
        osw.close();  
        pw.close();  
    }
    //获得控制台用户输入的信息
    public String getInputMessage() throws IOException{
        System.out.println("请输入您的命令∶");
        byte buffer[]=new byte[1024];
        int count=System.in.read(buffer);
        char[] ch=new char[count-2];//最后两位为结束符，删去不要
        for(int i=0;i<count-2;i++)
            ch[i]=(char)buffer[i];
        String str=new String(ch);
        return str;
    }
    //以文件流的方式复制文件
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
    //写文件
    //1.利用PrintStream写文件
    public void PrintStreamDemo(){
        try{
            FileOutputStream out=new FileOutputStream("D:/test.txt");
            PrintStream p=new PrintStream(out);
            for(int i=0;i<10;i++)
                p.println("This is "+i+" line");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    //2.利用StringBuffer写文件
    public void StringBufferDemo() throws IOException{
        File file=new File("/root/sms.log");
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true);        
        for(int i=0;i<10000;i++){
            StringBuffer sb=new StringBuffer();
            sb.append("这是第"+i+"行:前面介绍的各种方法都不关用,为什么总是奇怪的问题 ");
            out.write(sb.toString().getBytes("utf-8"));
        }        
        out.close();
    }
    //文件重命名
    public void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newname+"已经存在！");
            else{
                oldfile.renameTo(newfile);
            }
        }         
    }
    //转移文件目录
    //转移文件目录不等同于复制文件，复制文件是复制后两个目录都存在该文件，而转移文件目录则是转移后，只有新目录中存在该文件。
    public void changeDirectory(String filename,String oldpath,String newpath,boolean cover){
        if(!oldpath.equals(newpath)){
            File oldfile=new File(oldpath+"/"+filename);
            File newfile=new File(newpath+"/"+filename);
            if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                if(cover)//覆盖
                    oldfile.renameTo(newfile);
                else
                    System.out.println("在新目录下已经存在："+filename);
            }else{
                oldfile.renameTo(newfile);
            }
        }       
    }
    //读文件
    //1.利用FileInputStream读取文件
    public String FileInputStreamDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        FileInputStream fis=new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb=new StringBuffer();
        while((fis.read(buf))!=-1){
            sb.append(new String(buf));    
            buf=new byte[1024];//重新生成，避免和上次读取的数据重复
        }
        return sb.toString();
    }
    //利用BufferedReader读取
    //在IO操作，利用BufferedReader和BufferedWriter效率会更高一点
    public String BufferedReaderDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        return sb.toString();
    }
    //利用dom4j读取xml文件
    public Document readXml(String path) throws DocumentException, IOException{
        File file=new File(path);
        BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
        SAXReader saxreader = new SAXReader();
        Document document = (Document)saxreader.read(bufferedreader);
        bufferedreader.close();
        return document;
    }
    //创建文件(文件夹)
    //1.创建文件夹  
    public void createDir(String path){
        File dir=new File(path);
        if(!dir.exists())
           dir.mkdir();
    }
    //2.创建新文件
    public void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if(!file.exists())
           file.createNewFile();
    }
    //删除文件(目录)
    //删除文件
    public void delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }
    //要利用File类的delete()方法删除目录时，必须保证该目录下没有文件或者子目录，
    //否则删除失败，因此在实际应用中，我们要删除目录，必须利用递归删除该目录下的所有子目录和文件，然后再删除该目录。
    public void delDir(String path){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName());
                }
                else{
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }
}
