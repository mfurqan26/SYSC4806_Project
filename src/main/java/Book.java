import jakarta.persistence.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@IdClass(BookId.class)
@Entity
public class Book {
    @Id
    private BookId id;

    private String name;
    private String description;

    private String publisher;
    private ByteArrayInputStream cover;


    public BookId getId() {
        return id;
    }

}
