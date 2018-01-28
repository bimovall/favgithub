package bivano.favgithub.data.remote;

import java.util.List;

import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.model.UserRepo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BimoV on 12/22/2017.
 */

public interface NetworkApi {

    @GET("search/users")
    Observable<SearchResponse> searchUser(@Query("q") String user);

    //https://api.github.com/users/bimovall
    @GET("users/{username}")
    Observable<GithubUser> getUser(@Path("username")String username);

    @GET("users/{username}/repos")
    Observable<List<UserRepo>> getUserRepo(@Path("username")String username);

}
