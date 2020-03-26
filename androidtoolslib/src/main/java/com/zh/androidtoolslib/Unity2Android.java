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
     * Android方法,供unity调用,简单显示一个toast.
     * @param param
     * @return
     */
    public boolean androidShowToast(String param){

        //显示Toast
        Toast.makeText(getUnityActivity(),"get a string from Unity: " + param,Toast.LENGTH_SHORT).show();

        return true;
    }

    /**
     * Android方法,供unity调用,获得unity传入多个参数,执行相关动作.
     * @param param 传入string参数.
     * @param array 传入int[]参数
     */
    public void androidFunc(String param, int[] array){

        Log.d(TAG, "androidFunc: get a string from unity: " + param);
        Log.d(TAG, "androidFunc: get a array from unity: " + array.toString());

        //同时,主动调用unity中定义的方法.
        if(_unityCallback != null){
            String unityStr = _unityCallback.getUnityStringFunc();                                        //调用unity方法,获取返回值.
            Log.d(TAG, "androidFunc: call the unity function and get the result: " + unityStr);
            _unityCallback.setUnityValueFunc(32, "AndroidString");                          //将Android中的某些值,传入unity.
        }
    }
}
