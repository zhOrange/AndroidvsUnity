package com.zh.androidtoolslib;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: AndroidvsUnity
 * @Package: com.zh.androidtoolslib
 * @ClassName: Unity2Android
 * @Description: java类作用描述
 * @Author: zh
 * @CreateDate: 20-3-25 下午5:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 20-3-25 下午5:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Unity2Android {

    private static final String TAG = "Unity2Android";

    /**
     * unity项目启动时的上下文
     */
    private Activity _unityActivity;
    /**
     * unity回调方法,存储了用于Android调用的具体方法.
     */
    private IAndroidUnityCallBack _unityCallback;

    /**
     * unitycallback方法.
     * unity中实现具体方法,然后在Android中调用.
     * 实现Android调用unity方法.
     */
    public void setAndroidUnityCallback(IAndroidUnityCallBack callback){
        try {
            _unityCallback = callback;
        }
        catch (Exception ex)
        {
            Log.v(TAG,ex.toString());
        }
    }

    /**
     * 获取unity项目的上下文
     * @return
     */
    Activity getUnityActivity(){
        if(_unityActivity==null) {
            try {
                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");
                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);
                _unityActivity = activity;
            } catch (ClassNotFoundException e) {

            } catch (IllegalAccessException e) {

            } catch (NoSuchFieldException e) {

            }
        }
        return _unityActivity;
    }


    /**
     * 供unity调用的Android方法.
     * @param param
     * @return
     */
    public boolean androidFunc(String param){
        //显示Toast
        Toast.makeText(getUnityActivity(),"Java method called by Unity.",Toast.LENGTH_SHORT).show();

        //同时,主动调用unity中定义的方法.
        if(_unityCallback != null){
            _unityCallback.unityFunc(param);
        }
        return true;
    }
}
