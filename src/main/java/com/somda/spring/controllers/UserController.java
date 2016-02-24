package com.somda.spring.controllers;

import javax.faces.bean.ManagedBean;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.somda.spring.entity.User;
import com.somda.spring.services.UserService;

@ManagedBean(name = "userController")
// @SessionScoped
@Controller
@Scope(value = "session")
public class UserController {

	private final String WELCOME = "Welcome.\n";

	private String userName;
	private String password;
	private String name;
	private String confirmPass;
	private String status = WELCOME;
	private boolean buttonDisabled = false;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public String login() {
		try {
			User user = userService.findByUserName(this.userName);
			if (this.password.equals(user.getPassword()))
				this.status = "success";
			else
				this.status = "Failed to log in. Plase check your password.\n";
		} catch (NonUniqueResultException e) {
			this.status = "There are two or more users with the same Username.\n";
		} catch (NullPointerException e) {
			this.status = "Username invalid.\n";
		}

		return ("success".equals(status) ? "mainForm" : "index");
	}

	public String signOn() {
		checkPassword();
		checkUserName();
		checkFields();
		if (WELCOME.equals(status)) {

			User user = new User();
			user.setName(this.name);
			user.setPassword(this.password);
			user.setUserName(this.userName);

			try {
				userService.saveUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			return "index";
		} else {
			return "signIn";
		}

	}

	public void checkPassword() {
		if (this.password != null) {
			if (this.password.equals(this.confirmPass)) {
				this.buttonDisabled = false;
				this.status = WELCOME;
			} else {
				this.buttonDisabled = true;
				this.status = "Passwords do not match.\n";
			}
		} else if (this.confirmPass != null) {
			if (this.confirmPass.equals(this.password)) {
				this.buttonDisabled = false;
				this.status = WELCOME;
			} else {
				this.buttonDisabled = true;
				this.status = "Passwords do not match.\n";
			}
		}
	}

	public void checkUserName() {
		User user = userService.findByUserName(this.userName);
		if (user != null) {
			this.buttonDisabled = true;
			this.status = "Username already exists.\n";
		} else {
			this.buttonDisabled = false;
			this.status = WELCOME;
		}
	}

	public void checkFields() {
		if (this.name == null || this.name.isEmpty()) {
			this.status = "Name is empty.\n";
		} else if (this.userName == null || this.userName.isEmpty()) {
			this.status = "Username is empty.\n";
		} else if (this.password == null || this.password.isEmpty()) {
			this.status = "Password is empty.\n";
		} else if (this.confirmPass == null || this.confirmPass.isEmpty()) {
			this.status = "Confirm Password is empty.\n";
		}
	}
}
