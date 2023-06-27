package ItemService.Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Field(String name, String description, Item item) {
        this.name = name;
        this.description = description;
        this.item = item;
    }

    public Field() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
