package org.joychou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Basic integration tests for the web controllers.
 * - Verifies root ("/") redirects to /index
 * - Verifies /index view name and model attribute "user"
 * - Verifies /appInfo returns JSON and contains expected fields
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void rootRedirectsToIndex_andIndexReturnsView() throws Exception {
        // "/" should redirect to "/index"
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        // "/index" should return view "index" and a "user" model attribute
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser
    public void appInfo_returnsJsonAndContainsAppNameAndUser() throws Exception {
        mockMvc.perform(get("/appInfo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"app_name\":\"java security code\"")))
                .andExpect(content().string(containsString("\"login\":\"success\"")))
                .andExpect(content().string(containsString("\"username\":\"user\"")));
    }
}
