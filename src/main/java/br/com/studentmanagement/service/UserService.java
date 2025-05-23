package br.com.studentmanagement.service;

import br.com.studentmanagement.exception.CryptoExistsException;
import br.com.studentmanagement.exception.EmailExistsException;
import br.com.studentmanagement.repository.UserRepository;
import br.com.studentmanagement.exception.ServiceException;
import br.com.studentmanagement.model.User;
import br.com.studentmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) throws Exception {

        try {

            if(userRepository.findByEmail(user.getEmail()) !=null) {
                throw new EmailExistsException("This email is already registered: " + user.getEmail());
            }

            user.setPassword(Util.md5(user.getPassword()));

        } catch (NoSuchAlgorithmException e) {
            throw new CryptoExistsException("Error in password encryption");
        }
        userRepository.save(user);
    }

    public User loginUser(String user, String password) throws ServiceException {

        User userLogin = userRepository.buscarLogin(user, password);
        return userLogin;
    }
}
