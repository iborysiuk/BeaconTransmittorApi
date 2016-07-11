package com.beacon.transmitter.api;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Created by Yuriy on 2016-07-07.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IBeaconApiApplication.class)
@WebAppConfiguration
public class EmailControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void createUser() throws Exception {
        String userJson = "{\"username\":\"Sergey\",\"email\":\"sergey@ibeacon.com\",\"password\":\"1234\"}";

        this.mockMvc.perform(post("/users/register")
                .with(httpBasic("ibeacon", "ibeacon2016"))
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void userCreated() throws Exception {
        String userJson = "{\"username\":\"Yuriy\",\"email\":\"yuriy@ibeacon.com\",\"password\":\"1234\"}";

        this.mockMvc.perform(post("/users/register")
                .with(httpBasic("ibeacon", "ibeacon2016"))
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void getUser() throws Exception {
        String userJson = "{\"email\":\"yuriy@ibeacon.com\", \"password\":\"1234\"}";

        this.mockMvc.perform(post("/users/login")
                .with(httpBasic("ibeacon", "ibeacon2016"))
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void wrongCredentials() throws Exception {
        String userJson = "{\"email\":\"sergey@ibeacon.com\", \"password\":\"3546\"}";

        this.mockMvc.perform(post("/users/login")
                .with(httpBasic("ibeacon", "ibeacon2016"))
                .contentType(contentType)
                .content(userJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}