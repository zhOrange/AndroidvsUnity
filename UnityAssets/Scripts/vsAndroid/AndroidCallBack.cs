using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Helper.Utilities;

public class AndroidCallBack : Singleton<AndroidCallBack> {

    AndroidJavaObject _ajo;
    void Start()
    {
        //加载java类，生成java类对象。
        _ajo = new AndroidJavaObject("com.zh.androidtoolslib.AndroidCallBack");
        if (_ajo != null)
        {
            //设置Android中的unity回调方法。
            _ajo.Call("setAndroidUnityCallback", new UnityCallBack());
        }
    }

    /// <summary>
    /// 调用Android方法.
    /// </summary>
    /// <param name="param"></param>
    /// <param name="arr"></param>
    public void androidFunc(string param, int[] arr)
    {
        if(_ajo != null)
        {
            _ajo.Call("androidFunc", param, arr);
        }
    }

}
