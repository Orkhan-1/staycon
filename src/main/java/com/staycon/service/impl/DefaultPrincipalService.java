package com.staycon.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.staycon.daos.PrincipalDao;
import com.staycon.daos.VerificationDao;
import com.staycon.logging.Loggible;
import com.staycon.models.PrincipalModel;
import com.staycon.models.VerificationTokenModel;
import com.staycon.models.enums.TokenType;
import com.staycon.service.PrincipalService;


@Service
public class DefaultPrincipalService implements PrincipalService, UserDetailsService {

    @Autowired
    private PrincipalDao principalDao;
    @Autowired
    private VerificationDao verificationDao;

    @Override
    @Loggible
    public void register(PrincipalModel principalModel) {
        principalModel.setEnabled(true);
        principalModel.setRole("ROLE_ADMIN");
        principalDao.save(principalModel);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        PrincipalModel principal = principalDao.findByEmail(email);

        if (principal == null) {
            return null;
        }

        Boolean enabled = principal.getEnabled();

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(principal.getRole());
        String password = principal.getPassword();

        return new User(email, password, enabled, true, true, true, auth);
    }

    @Override
    public void update(PrincipalModel principalModel) {
        principalDao.save(principalModel);
    }

    @Override
    public String createEmailVerificationToken(PrincipalModel principalModel) {
        VerificationTokenModel model = new VerificationTokenModel(UUID.randomUUID().toString(), principalModel, TokenType.REGISTRATION);
        verificationDao.save(model);
        return model.getToken();
    }

    @Override
    public VerificationTokenModel getVerificationToken(String token) {
        return verificationDao.findByToken(token);
    }

    @Override
    public void deleteToken(VerificationTokenModel verificationTokenModel) {
        verificationDao.delete(verificationTokenModel);
    }
}
