package amazin;/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import amazin.model.Vendor;
import amazin.model.Account;
import amazin.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VendorTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void vendorPageNoAccount() throws Exception {
        this.mockMvc.perform(get("/Vendor")).andDo(print()).andExpect(status().isFound())
                .andExpect(redirectedUrl("/VendorLogin")).andReturn();
    }

    @Test
    public void vendorCreatePageNoAccount() throws Exception {
        this.mockMvc.perform(get("/VendorCreate")).andDo(print()).andExpect(status().isFound())
                .andExpect(redirectedUrl("/VendorLogin")).andReturn();
    }

    @Test
    public void vendorEditPageNoAccount() throws Exception {
        this.mockMvc.perform(get("/VendorEdit")).andDo(print()).andExpect(status().isFound())
                .andExpect(redirectedUrl("/VendorLogin")).andReturn();
    }
}