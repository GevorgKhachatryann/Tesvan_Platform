package data;

import com.github.javafaker.Faker;

public class userData {
    static Faker faker = new Faker();
    private static String email;
    private static final String firstName = faker.name().firstName();
    private static final String lastName = faker.name().lastName();


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

}
