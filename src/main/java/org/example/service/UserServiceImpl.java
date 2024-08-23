package org.example.service;

import org.example.dao.User;
import org.example.dto.UserDTO;
import org.example.dto.UserLoginDTO;
import org.example.repository.UserRepository;
import org.example.utils.EmailSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    @Autowired
    private UserRepository userRepository;

    public static String codeGenerator() {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        String code = sb.toString();
        return code;
    }

    public void sendCode(String email) {

        String code = codeGenerator();

        EmailSender es = new EmailSender();
        es.sendEmail(email, "Verification Code",
                "Your verification code is \n\n" + code + "\n\n Expire in 3 minutes.");

        redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);

    }

    public Boolean verify(String email, String code) {
        return email.equals(redisTemplate.opsForValue().get(code));
    }

    public User register(UserDTO userDTO){

        User user = new User();
        // copy from userDTO to user
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);

    }

    public String login(UserLoginDTO userLoginDTO) {
        // first, check if such user exists in the database
        String email = userLoginDTO.getEmail();
        String pwd = userLoginDTO.getPwd();

        if (userRepository.findPwdByEmail(email)==null) {
            return "User doesn't exist";
        }
        else if (!userRepository.findPwdByEmail(email).equals(pwd)) {
            return "Wrong password";
        }
        else {
            return "Success! Here comes a token. But I don't want to write";
        }
    }
}
