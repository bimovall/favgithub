package bivano.favgithub.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import bivano.favgithub.model.GithubUser;

/**
 * Created by BimoV on 12/25/2017.
 */
@Database(entities = {GithubUser.class},version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract LocalDao localDao();
}
