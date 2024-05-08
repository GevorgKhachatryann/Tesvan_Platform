package data;

import com.github.javafaker.Faker;

public class userData {
    private static final Faker faker = new Faker();
    private static String email;
    private static String token;
    private static String password;
    private static String registrationMail;
    private static final String firstName = faker.name().firstName();
    private static final String lastName = faker.name().lastName();
    private static final String phone = faker.numerify("######");
    private static final String city = faker.address().city();
    private static final String study = faker.university().name();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber(){
        return phone;
    }

    public String getCity(){
        return city;
    }
    public String getUniversity(){
        return study;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    public void setRegistrationMail(String registrationMail){
        this.registrationMail = registrationMail;
    }
    public String getRegistrationMail(){
        return registrationMail;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
