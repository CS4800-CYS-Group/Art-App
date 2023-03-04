package edu.cpp.CYS.model;

public class LoginModel{
   private String username;
   private String password;

   public LoginModel() {
      super();
   }

   public LoginModel(String username, String password) {
      this.username = username;
      this.password = password;
   }

   @Override
   public String toString() {
      return "LoginModel [username=" + username + ", password=" + password + "]";
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUsername() {
      return username;
   }
   
   public String getPassword() {
      return password;
   }
}