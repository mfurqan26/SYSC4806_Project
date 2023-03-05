/*
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

package com.amazin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BuddyInfoTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addBuddy() throws Exception {
        this.mockMvc.perform(post("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("{" + "\"firstName\":\"Greg\"," + "\"lastName\":\"Smith\"" + "}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void removeBuddy() throws Exception {
        this.mockMvc.perform(post("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("{" + "\"firstName\":\"Greg\"," + "\"lastName\":\"Smith\"" + "}")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(delete("/buddyInfo?bookId=1&buddyId=1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"firstName\":\"Greg\"")));
    }

    @Test
    public void getBuddy() throws Exception {
        this.mockMvc.perform(post("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("{" + "\"firstName\":\"Greg\"," + "\"lastName\":\"Smith\"" + "}")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/buddyInfo?bookId=1&buddyId=1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"firstName\":\"Greg\"")));
    }
}