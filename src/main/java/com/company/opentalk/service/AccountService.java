package com.company.opentalk.service;

import com.company.opentalk.dto.AccountDto;
import com.company.opentalk.entity.CompanyBranch;
import com.company.opentalk.entity.Employee;
import com.company.opentalk.entity.Role;
import com.company.opentalk.entity.User;
import com.company.opentalk.repository.CompanyBranchRepository;
import com.company.opentalk.repository.EmployeeRepository;
import com.company.opentalk.repository.RoleRepository;
import com.company.opentalk.repository.UserRepository;
import com.company.opentalk.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyBranchRepository branchRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MailService mailService;

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);


    public void addAccount(AccountDto accountDto, String roleName) throws MessagingException {
        long star, end;
        star = System.nanoTime();
        Employee employee = employeeRepository.findByEmail(accountDto.getEmail());
        if (employee == null) {
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByRoleName(roleName));
            User user = new User();
            user.setEmail(accountDto.getEmail());
            user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            user.setEnabled(accountDto.isEnabled());
            user.addRole(roles);
            System.out.println(accountDto.getDateOfBirth());
            LocalDate date = LocalDate.parse(accountDto.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            CompanyBranch branch = branchRepository.findByranchName(accountDto.getBranch());
            Employee newEmployee = new Employee(accountDto.getFirstName(), accountDto.getLastName(),
                    date, accountDto.getPhone(), accountDto.getEmail(), branch, user);
            System.out.println(newEmployee.getUser().getRoleList().get(0).getRoleName());
            employeeRepository.save(newEmployee);
            mailService.sendEmailCreateEmployee(accountDto);
        } else {
            logger.error("Account with email {} already exists", accountDto.getEmail());
        }
        end = System.nanoTime();
        System.out.println("________________________" + (end - star));
    }

    public void updateAccount(Integer id, AccountDto accountDto) {
        Employee employee = employeeRepository.findById(id).get();
        if (employee != null ) {
            LocalDate date = LocalDate.parse(accountDto.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            CompanyBranch branch = branchRepository.findByranchName(accountDto.getBranch());
            employee.setFirstName(accountDto.getFirstName());
            employee.setLastName(accountDto.getLastName());
            employee.setDateOfBirth(date);
            employee.setPhone(accountDto.getPhone());
            employee.setCompanyBranch(branch);
            employeeRepository.save(employee);
        } else {
            logger.error("Id {} does not exist or email {} already exists", id, accountDto.getEmail());
        }
    }

    public void updateInfo(String authHeader, AccountDto accountDto) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        Employee employee = employeeRepository.findByEmail(email);
        employee.setFirstName(accountDto.getFirstName());
        employee.setLastName(accountDto.getLastName());
        LocalDate date = LocalDate.parse(accountDto.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        employee.setDateOfBirth(date);
        employee.setPhone(accountDto.getPhone());
        employeeRepository.save(employee);

    }

    public void updatePassword(String authHeader, String oldPass, String newPass) {
        String email = jwtTokenProvider.getUserIdFromJWT(authHeader);
        User user = userRepository.findUsersByEmail(email);
        if (passwordEncoder.matches(oldPass, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPass));
            userRepository.save(user);
        } else {
            logger.error("Incorrect password");
        }

    }

    public void updateRole(Integer id, List<String> roleName) {
        List<Role> roles = new ArrayList<>();
        for (String str : roleName) {
            Role role = roleRepository.findByRoleName(str);
            roles.add(role);
        }
        Employee employee = employeeRepository.findById(id).get();
        if (employee != null) {
            User user = employee.getUser();
            user.setRoleList(roles);
            employee.setUser(user);
            employeeRepository.save(employee);
        } else {
            logger.error("Account does not exist");
        }
    }

    public void deleteAccount(Integer id) {
        employeeRepository.deleteById(id);
    }
}
