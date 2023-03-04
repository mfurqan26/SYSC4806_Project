import jakarta.persistence.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Book {
    @Id
    private ISBN id;
    private String name;
    private String description;

    private String publisher;
    private float price;
    private ByteArrayInputStream cover;




}
