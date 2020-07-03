using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Demo : MonoBehaviour {

    AndroidJavaObject _ajo;

	// Use this for initialization
	void Start () {
        UnityCallBack.OnEventCallBack += this.onEventCallBack;
    }
	
	// Update is called once per frame
	void Update () {
		
	}

    public void onStartBtnClick()
    {
        string str = "";
        int[] arr = { 1, 2, 3, 4, 5 };
        AndroidCallBack.Instance.androidFunc(str, arr);         //调用Android方法.
    }
    public void onEndBtnClick()
    {
        
    }

    /// <summary>
    /// Android调用unity方法的响应事件。
    /// 可在多个脚本中设置不同的响应事件。
    /// </summary>
    /// <param name="result">Android调用unity方法时，传入的参数。 </param>
    private void onEventCallBack(string result)
    {
        Debug.Log(result);
    }
    private void OnDestroy()
    {
        UnityCallBack.OnEventCallBack -= this.onEventCallBack;
    }
}
