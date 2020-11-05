package com.tw.bookYourShow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




public class UserAuth {

	private String user_type;
	private String is_tfa_enabled;
	private String tfa_default_type;
	
}
