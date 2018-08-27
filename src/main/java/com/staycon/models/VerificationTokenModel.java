package com.staycon.models;


import com.staycon.models.enums.TokenType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification")
public class VerificationTokenModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = PrincipalModel.class)
    @JoinColumn(name = "user_id", nullable = false)
    private PrincipalModel principalModel;

    @Column(name = "expiredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @PrePersist
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        expiredDate = calendar.getTime();
    }

    public VerificationTokenModel() {
    }

    public VerificationTokenModel(String token, PrincipalModel principalModel, TokenType type) {
        this.token = token;
        this.principalModel = principalModel;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PrincipalModel getPrincipalModel() {
        return principalModel;
    }

    public void setPrincipalModel(PrincipalModel principalModel) {
        this.principalModel = principalModel;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
