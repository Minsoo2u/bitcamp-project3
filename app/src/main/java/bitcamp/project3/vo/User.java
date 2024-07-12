package bitcamp.project3.vo;

public class User {
    private static int seqNo;

    private int no;
    private String name;
    private String contact;

    // 기본 생성자
    public User() {
        this.no = ++seqNo;
    }

    // 파라미터가 있는 생성자
    public User(String name, String contact) {
        this.no = ++seqNo;
        this.name = name;
        this.contact = contact;
    }

    // Getter 및 Setter 메서드
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
                "no=" + no +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
