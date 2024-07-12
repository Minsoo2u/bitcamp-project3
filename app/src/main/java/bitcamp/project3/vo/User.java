package bitcamp.project3.vo;

public class User {
    private int id;
    private String name;
    private String contact;

    // 기본 생성자
    public User() {
    }

    // 파라미터가 있는 생성자
    public User(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Getter 및 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
