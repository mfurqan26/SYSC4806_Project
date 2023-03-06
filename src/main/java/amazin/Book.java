package amazin;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private Long id;

    private String name;
    private String description;

    private String publisher;

    //private ByteArrayInputStream cover;

    public Long getId() {
        return id;
    }

}
