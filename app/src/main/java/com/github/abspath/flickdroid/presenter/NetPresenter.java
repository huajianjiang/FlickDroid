package com.github.abspath.flickdroid.presenter;

import com.github.abspath.flickdroid.AppManager;
import com.github.abspath.flickdroid.contract.BaseContract;
import com.github.abspath.flickdroid.net.FlickrRequest;
import com.github.abspath.flickdroid.net.FlickrService;
import com.github.abspath.flickdroid.net.NetManager;
import com.github.abspath.flickdroid.net.ParamInterceptor;
import com.github.abspath.flickdroid.oauth.OAuthManager;

import java.util.Map;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public abstract class NetPresenter<V extends BaseContract.BaseIView> extends BasePresenter<V>
        implements ParamInterceptor.OnInterceptListener
{
    protected FlickrService service;
    private FlickrRequest mRequest;

    protected NetPresenter() {
        NetManager.getInstance().getHeaderParamInterceptor().registerInterceptListener(this);
        NetManager.getInstance().getQueryStringParamInterceptor().registerInterceptListener(this);
        service = AppManager.getInstance().getFlickrService();
    }

    protected void setRequest(FlickrRequest request) {
        mRequest = request;
    }

    @Override
    public boolean onIntercept(ParamInterceptor interceptor) {
        if (mRequest == null) throw new NullPointerException("FlickrRequest == null");
        ParamInterceptor.InterceptType type = interceptor.getType();
        if (type == ParamInterceptor.InterceptType.HEADER && mRequest.requireAuth()) {
            Map<String, String> signedHeaders = OAuthManager.get()
                    .signRequest(mRequest.method(), mRequest.params());
            interceptor.setParams(signedHeaders);
        } else if (type == ParamInterceptor.InterceptType.QUERY_STRING) {
            interceptor.getParams().put("method", mRequest.method());
        }
        return true;
    }

    @Override
    public void stop() {
        NetManager.getInstance().getHeaderParamInterceptor().unregisterInterceptListener();
        NetManager.getInstance().getQueryStringParamInterceptor().unregisterInterceptListener();
    }
}
