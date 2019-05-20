package com.example.nhocs.demonavigation.Api;

import com.example.nhocs.demonavigation.Model.Banner;
import com.example.nhocs.demonavigation.Model.LoaiSP;
import com.example.nhocs.demonavigation.Model.Statistic_Rating;
import com.example.nhocs.demonavigation.Model.Review;
import com.example.nhocs.demonavigation.Model.ThongTinKiemTra;
import com.example.nhocs.demonavigation.Model.ThongTinNguoiDung;
import com.example.nhocs.demonavigation.Model.ThongTinSanPham;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    @GET("getSanPhamMoiNhat.php")
    Call<ArrayList<ThongTinSanPham>> getSanPhamMoiNhat();

    @GET("getLoaiSanPham.php")
    Call<ArrayList<LoaiSP>> getLoaiSanPham();

    @GET("get_quangcao.php")
    Call<ArrayList<Banner>> getQuangCao();

    @GET("getSanPham.php")
    Call<ArrayList<ThongTinSanPham>> getSanPham(@QueryMap Map<String,String> para);

    @GET("soluong_ketqua.php")
    Call<ResponseBody> getNumOfRes(@Query("name") String name);

    @GET("timkiem.php")
    Call<ArrayList<ThongTinSanPham>> getKetQua(@QueryMap Map<String,String> para);

    @FormUrlEncoded
    @POST("donhang.php")
    Call<ResponseBody> createOrderID(@Field("TenKH") String TenKH,
                                     @Field("SoDT") String soDT,
                                     @Field("DiaChi") String diaChi,
                                     @Field("Email") String email);
    @FormUrlEncoded
    @POST("chitietdonhang.php")
    Call<ResponseBody> createOrderDetail(@Field("json") String json);

    @FormUrlEncoded
    @POST("kiemtra.php")
    Call<ArrayList<ThongTinKiemTra>> getSPDaMua(@Field("ID_DonHang") String id_donhang,
                                                @Field("TenKH") String TenKH);

    @GET("checkExistAccount.php")
    Call<ResponseBody> checkExist(@Query("account") String username);

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> createUser(@Field("fullName") String fullName,
                                  @Field("acc") String account,
                                  @Field("pass") String pass,
                                  @Field("email") String email,
                                  @Field("addr") String addr,
                                  @Field("countryCode") int code,// index trong list country Code
                                  @Field("phone") String phone,
                                  @Field("sex") int sex);
    @FormUrlEncoded
    @POST("signin.php")
    Call<ThongTinNguoiDung> getUser(@Field("acc") String acc,
                                    @Field("pass") String pass);
    @Multipart
    @POST("upload.php")
    Call<ResponseBody> uploadPhoto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("update_info.php")
    Call<ResponseBody> update_info(@Field("name") String name,
                                   @Field("address") String address,
                                   @Field("email") String email,
                                   @Field("countryCode") int code,
                                   @Field("phone") String phone,
                                   @Field("sex") int sex,
                                   @Field("oldImage") String oldImage,
                                   @Field("newImage") String newImage,
                                   @Field("ID") int ID);

    @FormUrlEncoded
    @POST("changePassword.php")
    Call<ResponseBody> changePass(@Field("id") int id,
                                  @Field("newPass") String newPass);

    @FormUrlEncoded
    @POST("insert_review.php")
    Call<ResponseBody> insertReview(@Field("id_pro") int id_pro,
                                    @Field("id_user") int id_user,
                                    @Field("rating") int rating,
                                    @Field("title") String title,
                                    @Field("content") String content);
    @GET("get_review.php")
    Call<ArrayList<Review>> getReivew(@Query("id_pro") int id_pro);

    @GET("statistic.php")
    Call<ArrayList<Statistic_Rating>> getStatistic(@Query("id_pro") int id_pro);
}
