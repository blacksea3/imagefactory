package com.bla.imagefetch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件工具类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private enum PIC_SUFFIX{
        PNG("png"),
        JPG("jpg"),
        JPEG("jpeg");

        private String name;
        private PIC_SUFFIX(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 查找所有的图片文件, 在指定目录
     * @author jiaxiantao(blacksea3)
     * @date 2020/7/23
     * @param directory 目录
     * @return java.util.List<java.lang.String>
     */
    public static List<String> findAllPicFiles(String directory){
        /*
        以下已被废弃, 图片根目录被全局配置文件指定, 避免在工具类中硬编码逻辑

        LoggerUtil.trace(LOGGER, System.getProperty("user.dir"));
        //previous Maybe xxx/imagefetch/app
        Path dir = new File(System.getProperty("user.dir")).toPath();

        Path rootDirPath = dir.getParent().getParent();
        LoggerUtil.trace(LOGGER, rootDirPath.toString() + "\\" + directory);
         */

        List<String> ret = new ArrayList<>();
        File rootDir = new File(directory);
        File[] allFiles = rootDir.listFiles();

        if (allFiles == null){
            return ret;
        }

        for (File file:allFiles){
            String name = file.getName();

            boolean found = false;
            for(PIC_SUFFIX pic : PIC_SUFFIX.values()){
                if (name.endsWith(pic.getName())){
                    found = true;
                    break;
                }
            }

            if (found){
                ret.add(directory.toString() + "\\" + directory + "\\" + name);
            }
        }
        return ret;
    }

    /**
     * Description: 下载图片
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param urlString: 远端url
     * @return java.lang.String 本地路径
     */
    public static String downloadFromRemoteUrl(String urlString, String savePath, String filename){
        // 构造URL
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            LoggerUtil.error(LOGGER, e, "url失效:", urlString);
            return null;
        }
        // 打开连接
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e, "url失效:", urlString);
            return null;
        }
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = null;
        try {
            is = con.getInputStream();
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e, "url失效:", urlString);
            return null;
        }

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        //获取图片的扩展名
        String extensionName = filename.substring(filename.lastIndexOf(".") +     1);
        // 新的图片文件名 = 编号 +"."图片扩展名

        Timestamp ts = new Timestamp(new Date().getTime());

        String newFileName = ts.getTime() + filename.substring(0, filename.lastIndexOf(".")) + "." + extensionName;
        OutputStream os = null;
        String fullFileName = sf.getPath() + "\\" + newFileName;
        try {
            os = new FileOutputStream(fullFileName);
        } catch (FileNotFoundException e) {
            LoggerUtil.error(LOGGER, e, "无法创建文件:", fullFileName);
            return null;
        }
        // 开始读取
        try {
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }catch (IOException e){
            LoggerUtil.error(LOGGER, e, "无法输入数据至文件:", sf.getPath() + "\\" + newFileName);
            try {
                os.close();
                is.close();
            } catch (IOException ioException) {
                LoggerUtil.error(LOGGER, e, "无法关闭输入或输出流");
            }
            return null;
        }

        try {
            os.close();
            is.close();
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e, "无法关闭输入或输出流");
            return null;
        }

        return fullFileName;
    }
}
