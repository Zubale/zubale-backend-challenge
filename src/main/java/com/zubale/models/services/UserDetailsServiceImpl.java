package com.zubale.models.services;

import com.zubale.models.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component("UserDetailsServiceImpl")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.zubale.models.entity.User user = userDao.findByUsername(username);

        if(user == null){
            log.error("Error login: Not existing '"+username+"' in the system");
            throw new UsernameNotFoundException("Error login: Not existing '"+username+"' in the system");
        }
        List<GrantedAuthority> authorityList = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), user.getEnabled(),true, true,true,authorityList);
    }

    @Override
    @Transactional(readOnly = true)
    public com.zubale.models.entity.User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<com.zubale.models.entity.User> findAll() {
        return (List<com.zubale.models.entity.User>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<com.zubale.models.entity.User> findAll(Pageable pageable){
        return  userDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public com.zubale.models.entity.User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public com.zubale.models.entity.User save(com.zubale.models.entity.User quote) {
        return userDao.save(quote);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
