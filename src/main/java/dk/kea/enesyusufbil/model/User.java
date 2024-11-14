package dk.kea.enesyusufbil.model;
public class User {
    private int user_id;
    private String user_navn;
    private String user_email;
    private String user_password;
    private int user_telefonnummer;
    private String user_rolle;
    private String imageUrl;


    public User() {}


    public User(String user_navn, String user_email, String user_password, int user_telefonnummer, String user_rolle, String imageUrl)
    {
        this.user_navn = user_navn;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_telefonnummer = user_telefonnummer;
        this.user_rolle = user_rolle;
        this.imageUrl = imageUrl;
    }


    public User(int user_id, String user_navn, String user_email, String user_password, int user_telefonnummer, String user_rolle, String imageUrl)
    {
        this.user_id = user_id;
        this.user_navn = user_navn;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_telefonnummer = user_telefonnummer;
        this.user_rolle = user_rolle;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_navn() {
        return user_navn;
    }

    public void setUser_navn(String user_navn) {
        this.user_navn = user_navn;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_telefonnummer() {
        return user_telefonnummer;
    }

    public void setUser_telefonnummer(int user_telefonnummer) {
        this.user_telefonnummer = user_telefonnummer;
    }

    public String getUser_rolle() {
        return user_rolle;
    }

    public void setUser_rolle(String user_rolle) {
        this.user_rolle = user_rolle;
    }

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl; }

}