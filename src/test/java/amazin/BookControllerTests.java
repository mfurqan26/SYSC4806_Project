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

import amazin.model.Book;
import amazin.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getAddressBook() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/book?isbn=978-0-122453-12-1&version=1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createNewBook() throws Exception {
        Book newBook = new Book("978-0-122453-12-1", 2, "Book1", "description", "publisher", 1, 1);
        bookRepository.save(newBook);
        this.mockMvc.perform(get("http://localhost:8080/book?isbn=978-0-122453-12-1&version=2")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"Book1\"")));
    }

    @Test
    public void editBook() throws Exception {
        Book newBook = new Book("978-0-122453-12-1", 2, "Book1", "description", "publisher", 1, 1);
        bookRepository.save(newBook);
        Book newBookEdited = new Book("978-0-122453-12-1", 2, "Book1Edited", "description", "publisher", 1, 1);
        bookRepository.save(newBookEdited);
        this.mockMvc.perform(get("http://localhost:8080/book?isbn=978-0-122453-12-1&version=2")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"Book1Edited\"")));
    }
}
