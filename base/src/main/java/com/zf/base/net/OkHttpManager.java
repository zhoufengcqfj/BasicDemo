package com.zf.base.net;


import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.zf.base.BuildConfig;
import com.zf.base.interceptor.BaseInterceptor;
import com.zf.base.interceptor.HeaderIntecepter;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;


public class OkHttpManager {

    private static OkHttpClient mClient;


    private OkHttpManager() {

        X509TrustManager trustManager = getTrustManager();
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(trustManager);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HeaderIntecepter())
                .addInterceptor(new BaseInterceptor());
        //非正式环境打开log
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LoggingInterceptor.Builder()
                    .loggable(true)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .build());
        }

        if (sslSocketFactory != null) {
            // ssl
            builder.sslSocketFactory(sslSocketFactory, trustManager)
                    //verifier
                    .hostnameVerifier((hostname, session) -> true);
        }

        mClient = builder.build();

    }

    private static class InstanceHolder {
        private InstanceHolder() {
        }

        private static final OkHttpManager INSTANCE = new OkHttpManager();
    }

    /**
     * 获取OkHttpManager单例
     *
     * @return 单例
     */
    public static OkHttpManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取 OkHttpClient
     *
     * @return
     */
    public OkHttpClient getClient() {
        return mClient;
    }

    private X509TrustManager getTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

    private SSLSocketFactory getSSLSocketFactory(X509TrustManager manager) {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{manager};

            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }
}