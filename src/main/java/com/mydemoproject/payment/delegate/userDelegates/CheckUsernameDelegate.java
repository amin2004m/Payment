package com.mydemoproject.payment.delegate.userDelegates;

import com.mydemoproject.payment.exceptions.UserAlreadyExistsException;
import com.mydemoproject.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("CheckUsernameDelegate")
@RequiredArgsConstructor
public class CheckUsernameDelegate implements JavaDelegate {

    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String username = (String) execution.getVariable("username");
        var user =userRepository.findByUsername(username);
        if (user!= null){
            throw new UserAlreadyExistsException("Username already Exists ");
        }

    }

}
