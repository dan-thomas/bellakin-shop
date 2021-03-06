package com.bellakin.botzzer.controllers.account;

import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.data.models.users.AppUser;
import com.bellakin.botzzer.security.EncryptionHelper;
import com.bellakin.botzzer.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class LoginController {

  private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

  private final UserSession userSession;

  private final IUserService<AppUser> userService;

  @Autowired
  public LoginController(UserSession userSession, IUserService<AppUser> userService) {
    this.userSession = userSession;
    this.userService = userService;
  }

  @GetMapping("/account/login")
  public ModelAndView userLogin(ModelMap model) {
    return new ModelAndView("account/login", model);
  }

  @GetMapping("/account/preferences")
  public ModelAndView userPreferences(ModelMap model) {
    return new ModelAndView("account/preferences", model);
  }

  @PostMapping("/account/login")
  public ResponseEntity addAdmin(
    @RequestParam(value = "name") String name,
    @RequestParam(value = "password") String password
  ) {
    if(userSession.isLoggedIn()) {
      return new ResponseEntity<>("Already logged in", HttpStatus.ACCEPTED);
    }

    if(userSession.logIn(userService, name, EncryptionHelper.encryptPassword(password))) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>("Username or password is not recognized", HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/account/logout")
  public ResponseEntity logOut() {
    userSession.logOut();
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
