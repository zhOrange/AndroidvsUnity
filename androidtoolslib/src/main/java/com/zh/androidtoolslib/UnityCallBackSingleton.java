package com.zh.androidtoolslib;

import android.app.Activity;
import android.util.Log;

/**
 * @ProjectName: AndroidvsUnity
 * @Package: com.zh.androidtoolslib
 * @ClassName: UnityCallBack
 * @Description: 单例.用于与unity进行交互,获取unity的Activity和_unityCallback.
 * @Author: zh
 * @CreateDate: 20-3-26 下午4:55
 * @UpdateUser: 更新者：
 * @UpdateDate: 20-3-26 下午4:55
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */


/**
 *单例.用于与unity进行交互,获取unity的Activity和_unityCallback.
 */
public class UnityCallBackSingleton {
    private static final String TAG = "UnityCallBack";

    private static UnityCallBackSingleton mInstance;
    private UnityCallBackSingleton(){
        //do nothing.
    }

    /**
     * 获得单例实例对象.
     * @return
     */
    public static UnityCallBackSingleton getInstance(){
        if(mInstance != null){
            mInstance = new UnityCallBackSingleton();
        }
        return mInstance;
    }

    /**
     * unity项目启动时的上下文
     */
    private Activity _unityActivity;

    /**
     * unity回调方法,实现了用于Android调用的具体方法.
     */
    private IUnityCallBack _unityCallback;


    /**
     * 获取Unity正在启动的Activity.
     * @return
     */
    public Activity getUnityActivity(){
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
     * 设置callback.
     * @param callback
     */
    public void setUnityCallback(IUnityCallBack callback){
        try {
            _unityCallback = callback;
        }
        catch (Exception ex)
        {
            Log.v(TAG,ex.toString());
        }
    }

    /**
     * 获取callback实例.
     * @return
     */
    public IUnityCallBack getUnityCallback(){
        return _unityCallback;
    }

}
