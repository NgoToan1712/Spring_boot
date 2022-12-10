package com.company.opentalk.controller;

import com.company.opentalk.dto.AccountDto;
import com.company.opentalk.enums.RoleName;
import com.company.opentalk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping("/password")
    public void updatePassword(@RequestHeader("Authorization") String authHeader,
                               @RequestParam("oldPass") String oldPass,
                               @RequestParam("newPass") String newPass) {
        accountService.updatePassword(authHeader, oldPass, newPass);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAccount(@Valid @RequestBody AccountDto accountDto) throws MessagingException {
        String roleName = String.valueOf(RoleName.ROLE_ADMIN);
        accountService.addAccount(accountDto, roleName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addAccountUser(@Valid @RequestBody AccountDto accountDto) throws MessagingException {
        String roleName = String.valueOf(RoleName.ROLE_USER);
        accountService.addAccount(accountDto, roleName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-info")
    public ResponseEntity<?> updateInfo(@RequestHeader("Authorization") String authHeader,
                                        @Valid @RequestBody AccountDto accountDto) {
        accountService.updateInfo(authHeader, accountDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestParam("id") Integer id,@Valid @RequestBody AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam("id") Integer id) {
    }

    @PutMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestParam("id") Integer id, @RequestBody List<String> roleNames) {
        accountService.updateRole(id, roleNames);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}