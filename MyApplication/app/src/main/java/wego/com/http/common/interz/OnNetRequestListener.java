package wego.com.http.common.interz;

/**
 * 网络回调接口
 * Created by Administrator on 2017/10/27.
 */

public interface OnNetRequestListener <T> {

    /**
     * 网络请求开始
     */
    void onStart();

    /**
     * 网络请求结束
     */
    void onFinish();

    /**
     * 网络请求成功
     * @param data 返回的数据实体类信息 泛型定义
     */
    void onSuccess(T data);

    /**
     * 请求失败
     * @param t 异常
     */
    void onFailure(Throwable t);


}
