package database.support.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Accounts", schema = "dbo", catalog = "PasswordManagerDatabase")
public class AccountsEntity {
    private int id;
    private String mail;
    private String login;
    private String password;
    private String comments;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "Login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsEntity that = (AccountsEntity) o;
        return id == that.id &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, login, password, comments);
    }
}
