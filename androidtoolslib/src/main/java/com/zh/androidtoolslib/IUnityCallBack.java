package com.zh.androidtoolslib;

/**
 * @ProjectName: AndroidvsUnity
 * @Package: com.zh.androidtoolslib
 * @ClassName: IUnityCallBack
 * @Description: Unity方法调用接口,在此接口中声明Unity中实现的方法,用于在Android开发过程中,调用Unity方法.
 * @Author: zh
 * @CreateDate: 20-3-26 下午4:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 20-3-26 下午4:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

/**
 * Unity方法调用接口,在此接口中声明Unity中实现的方法,用于在Android开发过程中,调用Unity方法.
 */
public interface IUnityCallBack {
    public String getUnityStringFunc();
    public void setUnityValueFunc(int intValue, String str);
}
