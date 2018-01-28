package bivano.favgithub.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import bivano.favgithub.model.GithubUser;
import io.reactivex.Maybe;

/**
 * Created by BimoV on 12/25/2017.
 */
@Dao
public interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(GithubUser user);

    @Query("SELECT * FROM Users")
    Maybe<List<GithubUser>>getUsersDb();

    @Query("SELECT * FROM Users WHERE Users.login = :login ")
    Maybe<GithubUser>getUserByLogin(String login);

    @Query("DELETE FROM Users WHERE login = :login")
    void deleteUser(String login);
}
