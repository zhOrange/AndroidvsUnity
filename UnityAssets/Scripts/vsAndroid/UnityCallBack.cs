using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UnityCallBack: AndroidJavaProxy
{

    public delegate void OnUnityEventAction(string result);

    public static event OnUnityEventAction OnEventCallBack;

    /// <summary>
    /// UnityCallBack构造方法,实现Android中的IUnityCallBack接口。
    /// </summary>
    public UnityCallBack() : base("com.zh.androidtoolslib.IUnityCallBack")
    {
        //Debug.Log("UnityCallBack 构造方法");
    }

    #region 复写Android中IAndroidUnityCallBack接口方法，需保证方法名与参数类型、顺序等于Java接口定义完全一致。

    /// <summary>
    /// Android中调用方法，从unity返回一个字符串给Android。
    /// </summary>
    /// <returns></returns>
    public string getUnityStringFunc()
    {
        if (OnEventCallBack != null)
        {
            OnEventCallBack.Invoke("开始");
        }
        string str = "a string from Unity.";
        return str;
    }

    /// <summary>
    /// Android中调用方法，通过传入参数，将Android中的某些值，传入unity。
    /// </summary>
    /// <param name="intValue"></param>
    /// <param name="str"></param>
    public void setUnityValueFunc(int intValue, string str)
    {
        Debug.Log("get an int value frome Android: " + intValue);
        Debug.Log("get a string frome Android: " + str);
    }
    #endregion

}
