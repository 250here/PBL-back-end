package com.pbl.backend.config;

import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.BackendApplication;
import com.pbl.backend.constant.ConfigConstants;
import com.pbl.backend.utils.FileCommonUtils;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;

/**
 * @author: 杜东方
 * @date: 2020/5/24
 * @description:
 */
public class FileManageConfig {

    private static final String WINDOWS = "windows";

    private static final String MAC = "mac";

    private static final String LINUX = "linux";

    //private static final String PJ_FILE_PATH = "src/main/resources/static/PJFile/upload";

    //public static final String ABSOLUTE_SEPARATOR = ValueConsts.SEPARATOR;

    public static String SEPARATOR = "";

    private static OsName currentOS;

    static {
        if(Checker.isWindows()){
            currentOS = OsName.WINDOWS;
            SEPARATOR = ValueConsts.SEPARATOR;
        }
        else if(Checker.isMacOS()){
            currentOS = OsName.MAC;
        }
        else{
            SEPARATOR = "/";
            currentOS = OsName.LINUX;
        }
    }

    public static String getUploadStoragePath(){
        String parent = getStoragePath(ConfigConstants.UPLOAD_PATH_OF_SETTING);
        return parent;
    }

    public static String getStoragePath(String path){
        String pathType = "";
        if(currentOS == OsName.WINDOWS){
            pathType = WINDOWS;
        }
        else if(currentOS == OsName.MAC){
            pathType += MAC;
        }
        else{
            pathType += LINUX;
        }
        return BackendApplication.file_manage_settings.getJSONObject(path).getString(pathType);
    }


    /**
     * 当前系统名称
     */
    public enum OsName {
        /**
         * windows系统
         */
        WINDOWS,

        /**
         * MacOS系统
         */
        MAC,

        /**
         * Linux系统
         */
        LINUX
    }
}
