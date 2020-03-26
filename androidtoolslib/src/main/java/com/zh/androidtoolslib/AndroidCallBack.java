package com.zh.androidtoolslib;

import android.util.Log;

import java.util.Arrays;

/**
 * @ProjectName: AndroidvsUnity
 * @Package: com.zh.androidtoolslib
 * @ClassName: AndroidCallBack
 * @Description: Android方法,在与Unity通信开发过程中,供Unity调用.
 * @Author: zh
 * @CreateDate: 20-3-26 下午4:31
 * @UpdateUser: 更新者：
 * @UpdateDate: 20-3-26 下午4:31
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

/**
 * Android方法,在与Unity通信开发过程中,供Unity调用.
 */
public class AndroidCallBack {
    private static final String TAG = "AndroidCallBack";

    /**
     * Unity中调用方法.
     * 应在unity中的onstart()方法中,调用此方法,设置callback.
     * @param callback unity中实现IUnityCallBack接口的类的对象,其中实现了IUnityCallBack接口声明的各个方法,供Android调用.
     */
    public void setAndroidUnityCallback(IUnityCallBack callback){
        try {
            UnityCallBackSingleton.getInstance().setUnityCallback(callback);
        }
        catch (Exception ex)
        {
            Log.v(TAG,ex.toString());
        }
    }

    /**
     * Android方法,供unity调用,获得unity传入多个参数,执行相关动作.
     * @param param 传入string参数.
     * @param array 传入int[]参数
     */
    public void androidFunc(String param, int[] array){

        Log.d(TAG, "androidFunc: get a string from unity: " + param);
        Log.d(TAG, "androidFunc: get a array from unity: " + Arrays.toString(array));


        //Android主动调用unity中的方法.
        IUnityCallBack callBack = UnityCallBackSingleton.getInstance().getUnityCallback();
        if(callBack != null){
            String unityStr = callBack.getUnityStringFunc();                                        //调用unity方法,获取返回值.
            Log.d(TAG, "androidFunc: call the unity function and get the result: " + unityStr);
            callBack.setUnityValueFunc(32, "AndroidString");                          //将Android中的某些值,传入unity.
        }

    }

}
