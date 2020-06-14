package com.pbl.backend.utils;

import com.pbl.backend.config.FileManageConfig;
import com.pbl.backend.constant.ConfigConstants;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author: 杜东方
 * @date: 2020/5/24
 * @description:
 */
public class FileCommonUtils {


    /**
     * 将相对路径转换成绝对路径
     *
     * @param path 文件路径
     *
     * @return {@link String}
     */
    public static String checkPath(String path){
        String prefix = ConfigConstants.COLON + ValueConsts.SEPARATOR;
        return path.startsWith(ValueConsts.SEPARATOR) || path.startsWith(prefix, ValueConsts.ONE_INT) ?
                path : ConfigConstants.STORAGE_PATH + path;
    }

    /**
     * 加载本地资源
     *
     * @param response 返回的Response
     * @param path 资源路径
     */
    public static boolean loadResource(HttpServletResponse response, String path[]){
        try{
            if (Checker.isNotEmpty(path)) {
                File file = new File(path[0]);
                setResponseFileName(response, path[1]);

                FileInputStream in = new FileInputStream(file);
                ServletOutputStream os = response.getOutputStream();
                byte[] b;
                while (in.available() > 0) {
                    b = in.available() > 1024 ? new byte[1024] : new byte[in.available()];
                    in.read(b, 0, b.length);
                    os.write(b, 0, b.length);
                }
                in.close();
                os.flush();
                os.close();
            } else {
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 设置响应头的文件名
     *
     * @param response {@link HttpServletResponse}
     * @param fileName 文件名
     */
    public static void setResponseFileName(HttpServletResponse response, String fileName) throws
            UnsupportedEncodingException {
        response.setHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(fileName, "UTF-8"));
    }

    //根据数据库存储文件路径获取绝对路径
    /**
     * 设置响应头的文件名
     *
     * @param {@link String}
     * @param filePath 数据库存储文件相对路径
     */
    public static String getRealLocalPath(String filePath){
        String[] fileLocalPath = filePath.split("/");
        return FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + fileLocalPath[0] +
                ValueConsts.SEPARATOR + fileLocalPath[1] + ValueConsts.SEPARATOR + fileLocalPath[2];
    }



}
