package io.algostrategy.client.coinmarketcap.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.exception.CoinmarketcapApiException;
import io.algostrategy.client.coinmarketcap.security.AuthenticationInterceptor;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.API_BASE_URL;

/**
 * Generates an API implementation based on {@link CoinmarketcapApiService}.
 */
@UtilityClass
public class CoinmarketcapApiServiceGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create(mapper);
    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, Response> errorBodyConverter =
            (Converter<ResponseBody, Response>) converterFactory.responseBodyConverter(
                    io.algostrategy.client.coinmarketcap.domain.Response.class, new Annotation[0], null);

    static {
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    public static <S> S createService(String apiKey, Class<S> serviceClass) {
        return createService(apiKey, new OkHttpClient(), serviceClass);
    }

    public static <S> S createService(String apiKey, OkHttpClient client, Class<S> serviceClass) {

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(converterFactory);

        if (apiKey == null) {
            retrofitBuilder.client(client);
        } else {
            // `adaptedClient` will use its own interceptor, but share thread pool etc with the 'parent' client
            AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(apiKey);
            OkHttpClient.Builder newBuilder = client.newBuilder();
            newBuilder.interceptors().add(0, authInterceptor);
            OkHttpClient adaptedClient = newBuilder.build();
            retrofitBuilder.client(adaptedClient);
        }

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            retrofit2.Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                Response errorResponse = getCoinmarketcapApiError(response);
                throw new CoinmarketcapApiException(errorResponse.getStatus().getMsg());
            }
        } catch (IOException e) {
            throw new CoinmarketcapApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static Response getCoinmarketcapApiError(retrofit2.Response<?> response)
            throws IOException, CoinmarketcapApiException {
        return errorBodyConverter.convert(response.errorBody());
    }
}
