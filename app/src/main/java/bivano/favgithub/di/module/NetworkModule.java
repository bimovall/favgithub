package bivano.favgithub.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import bivano.favgithub.data.remote.NetworkApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BimoV on 12/22/2017.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    static Converter.Factory provideConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideLogging() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkhttp(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client,Converter.Factory converter){
        return new Retrofit.Builder()
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl("https://api.github.com")
                .build();
    }

    @Provides
    @Singleton
    static NetworkApi provideApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }

}
