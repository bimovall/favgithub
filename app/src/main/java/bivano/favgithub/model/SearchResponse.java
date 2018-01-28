package bivano.favgithub.model;

import java.util.List;

/**
 * Created by BimoV on 12/26/2017.
 */

public class SearchResponse {

    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public class Items {
        private String id;
        private String login;
        private String avatar_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

    }
}
