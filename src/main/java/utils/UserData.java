package utils;

public class UserData {
    private User validUser;
    public User getValidUser() {
        return validUser;
    }

    public void setValidUser(User validUser) {
        this.validUser = validUser;
    }


    public static class User {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}