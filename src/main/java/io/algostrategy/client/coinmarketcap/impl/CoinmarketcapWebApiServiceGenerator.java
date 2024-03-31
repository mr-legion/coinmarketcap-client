package io.algostrategy.client.coinmarketcap.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.coinmarketcap.domain.Response;
import io.algostrategy.client.coinmarketcap.exception.CoinmarketcapWebApiException;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static io.algostrategy.client.coinmarketcap.constant.CoinmarketcapApiConstants.WEB_API_BASE_URL;

/**
 * Generates an API implementation based on {@link CoinmarketcapWebApiService}.
 */
@UtilityClass
public class CoinmarketcapWebApiServiceGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create(mapper);

    static {
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(new OkHttpClient(), serviceClass);
    }

    public static <S> S createService(OkHttpClient client, Class<S> serviceClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEB_API_BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();

        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {

            T response = call.execute().body();

            if (response instanceof Response && ((Response<?>) response).getStatus().getCode() != 0) {
                throw new CoinmarketcapWebApiException(((Response<?>) response).getStatus().getMsg());
            }

            return response;
        } catch (IOException e) {
            throw new CoinmarketcapWebApiException(e);
        }
    }
}
