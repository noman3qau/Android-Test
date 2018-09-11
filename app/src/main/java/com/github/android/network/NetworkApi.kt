package com.github.android.network

import com.github.android.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object NetworkApi {

    /**
     * Manage Retrofit library central request
     */
    fun <S> networkRequest(classService: Class<S>): S {

        val builder = Retrofit.Builder()
                .baseUrl(Constants.GIT_HUB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideHttpClient(provideHttpLoggingInterceptor()))
        return builder.build().create(classService)
    }


    /**
     * The Method to bypass SLL Pining, It create the Trust Manager for all the certificates
     */

    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        try {

            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })

            builder.addInterceptor(interceptor)

            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    /**
     * Set logs enabled for retrofit library
     */
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


}
