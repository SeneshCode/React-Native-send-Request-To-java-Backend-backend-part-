package hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name", length = 145, nullable = false)
    private String name;
    
    @Column(name = "username", length = 45, nullable = false)
    private String username;
    
    @Column(name = "email", length = 60, nullable = false)
    private String email;
    
    @Column(name = "password", length = 45, nullable = false)
    private String password;
    
    @Column(name = "profile_image", length = 150, nullable = false)
    private String profileImagePath;
    
    @Column(name = "created_at",nullable = false)
    private Date createdAt;
    
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public User() {
    }

    public User(String name, String username, String email, String password, String profileImagePath, Date createdAt, City city) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.createdAt = createdAt;
        this.city = city;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
