package online.damfood.damfood.network;

import online.damfood.damfood.model.Logins;
import online.damfood.damfood.model.Menus;
import online.damfood.damfood.model.OrderBody;
import online.damfood.damfood.model.Orderan;
import online.damfood.damfood.model.Restos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {
    //the signin call
    @FormUrlEncoded
    @POST("login?key=359AECDECAAF86BF759E132258CEE930")
    Call<Logins> userLogin(
            @Field("nim") String nim,
            @Field("password") String password
    );

    // get penjual
    @GET("restoall?key=359AECDECAAF86BF759E132258CEE930")
    Call<Restos> getPenjual();

    //get menu
    @GET("resto/{id}/menuall?key=359AECDECAAF86BF759E132258CEE930")
    Call<Menus> getMenu(@Path("id") int id);

    //post orderan pesanan
    @POST("order?key=359AECDECAAF86BF759E132258CEE930")
    Call<Orderan> orderPost(@Body OrderBody order);

}
