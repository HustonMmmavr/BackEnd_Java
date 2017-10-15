package tests.Test;

import com.github.javafaker.Faker;
import lastunion.application.Application;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("UnnecessaryFullyQualifiedName")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@Category(tests.IntegrationTest.class)
//

public class ChangeEmailTest {
    @Autowired
    private MockMvc mock;
    private static Faker faker;
    private static TestRequestBuilder requestBuilder;
    private static String pathUrl;
    private static String userName;
    private static String userEmail;
    private static String userPassword;


    @SuppressWarnings("MissortedModifiers")
    @BeforeClass
    static public void init() {
        faker = new Faker();
        requestBuilder = new TestRequestBuilder();
        requestBuilder.init("newEmail");
    }

    public void createUser() throws Exception {
        this.mock.perform(
                post("/api/user/signup")
                        .contentType("application/json")
                        .content(TestRequestBuilder.getJsonRequestForSignUp(userName, userPassword, userEmail)))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(true)))
                .andExpect(jsonPath("$.responseMessage", is("User created successfully! en")));
    }

    @SuppressWarnings("ThrowInsideCatchBlockWhichIgnoresCaughtException")
    @Before
    public void setUp() {
        userName = faker.name().username();
        userEmail = faker.internet().emailAddress();
        userPassword = faker.internet().password();
        pathUrl = "/api/user/change_email";

        try {
            createUser();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Test
    public void changeEmailNormal() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("application/json")
                        .content(requestBuilder.getJsonRequest(faker.internet().emailAddress()))
                        .sessionAttr("userName", userName))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(true)))
                .andExpect(jsonPath("$.responseMessage", is("Ok! en")));
    }

    @Test
    public void changeEmailIncorrectEmail() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("application/json")
                        .content(requestBuilder.getJsonRequest("aaa"))
                        .sessionAttr("userName", userName))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result", is(false)))
                .andExpect(jsonPath("$.responseMessage", is("Form not valid! en")));
    }


    @Test
    public void changeEmailNullUserNewEmail() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("application/json")
                        .content(requestBuilder.getJsonRequest((String) null))
                        .sessionAttr("userName", userName))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result", is(false)))
                .andExpect(jsonPath("$.responseMessage", is("Json contains null fields! en")));
    }

    @Test
    public void changeEmailNullSession() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("application/json")
                        .content(requestBuilder.getJsonRequest(userEmail)))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.result", is(false)))
                .andExpect(jsonPath("$.responseMessage", is("Invalid session! en")));
    }

    @Test
    public void changeEmailInvalidSession() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("application/json")
                        .content(requestBuilder.getJsonRequest(userEmail))
                        .sessionAttr("userName", faker.name().username()))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.result", is(false)))
                .andExpect(jsonPath("$.responseMessage", is("Invalid session! en")));
    }

    @Test
    public void changeEmailIncorrectDocumentType() throws Exception {
        this.mock.perform(
                post(pathUrl)
                        .contentType("text/html"))
                .andExpect(status().isUnsupportedMediaType());
    }
}