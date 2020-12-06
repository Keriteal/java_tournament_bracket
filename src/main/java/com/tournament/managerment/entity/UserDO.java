package com.tournament.managerment.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_record")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true, length = 20)
    private String userName;
    @Column(nullable = false)
    private String secretKey;
    @CreationTimestamp
    private Timestamp timeCreate;
    @UpdateTimestamp
    private Timestamp timeModified;

    public UserDO() {
        this.userName="";
        this.secretKey="";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Timestamp getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Timestamp timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", timeCreate=" + timeCreate +
                ", timeModified=" + timeModified +
                '}';
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private int userId;
        private String userName;
        private String secretKey;
        private Timestamp timeCreate;
        private Timestamp timeModified;

        private UserBuilder() {
        }

        public static UserBuilder anUserDO() {
            return new UserBuilder();
        }

        public UserBuilder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder withSecretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public UserBuilder withTimeCreate(Timestamp timeCreate) {
            this.timeCreate = timeCreate;
            return this;
        }

        public UserBuilder withTimeModified(Timestamp timeModified) {
            this.timeModified = timeModified;
            return this;
        }

        public UserDO build() {
            UserDO userDO = new UserDO();
            userDO.setUserId(userId);
            userDO.setUserName(userName);
            userDO.setSecretKey(secretKey);
            userDO.setTimeCreate(timeCreate);
            userDO.setTimeModified(timeModified);
            return userDO;
        }
    }
}
