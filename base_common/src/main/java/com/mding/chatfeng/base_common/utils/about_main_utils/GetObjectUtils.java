package com.mding.chatfeng.base_common.utils.about_main_utils;

import android.content.Context;

import com.mding.chatfeng.base_common.utils.aboututils.ACache;


/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/28 0028
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class GetObjectUtils {
    private static GetObjectUtils getObjectUtils;
    private GetObjectUtils() {}
    /**
     * 单一实例
     */
    public synchronized static GetObjectUtils GetObject(){
        if (getObjectUtils == null) {
            getObjectUtils= new GetObjectUtils();
        }
        return getObjectUtils;
    }
    ACache aCache = null;
    public ACache getAcache(Context mContext)
    {
        if (aCache==null)
        {
            aCache =  ACache.get(mContext);
        }
        return aCache;
    }
}
