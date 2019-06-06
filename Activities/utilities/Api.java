package com.nrxtechnologies.hoto.utilities;


import com.nrxtechnologies.hoto.models.MesResponse;
import com.nrxtechnologies.hoto.models.Users.UserModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api
{
    @FormUrlEncoded
    @POST("create_user.php")
    Call<MesResponse> addUser(@Field("employee_id") String employee_id,
                              @Field("username") String username,
                              @Field("mobile") String mobile,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("circle") String circle,
                              @Field("cmp") String cmp,
                              @Field("role") String role,
                              @Field("project") String project);

    @FormUrlEncoded
    @POST("update_user.php")
    Call<MesResponse> updateUser(@Field("employee_id") String employee_id,
                                 @Field("username") String username,
                                 @Field("password") String password,
                                 @Field("mobile") String mobile,
                                 @Field("email") String email,
                                 @Field("circle") String circle,
                                 @Field("cmp") String cmp,
                                 @Field("role") String role,
                                 @Field("project") String project,
                                 @Field("id") String id);



    @POST("safety_details.pdf")
    Call<ResponseBody> getServiceManual();

    @POST("all_user.php")
    Call<UserModel> getAllUsers();




}
