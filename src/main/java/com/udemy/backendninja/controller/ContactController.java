package com.udemy.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.backendninja.constant.ViewConstant;
import com.udemy.backendninja.model.ContactModel;
import com.udemy.backendninja.services.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	private static final Log LOGGER = LogFactory.getLog(ContactController.class);
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/contactform")
	private String redirectContactForm(@RequestParam(name="id", required=false) int id,
			Model model) {
		ContactModel contactModel = new ContactModel();
		if(id != 0) {
			contactModel = contactService.findContactByIdModel(id);
		}
		model.addAttribute("contactmodel", contactModel);
		return ViewConstant.CONTACT_VIEW;
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactmodel") ContactModel contactModel,
			Model model) {
		LOGGER.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
		
		if (contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_VIEW);
		mav.addObject("contacts", contactService.listAllContacts());
		
		return mav;
		
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		
		return showContacts();
	}
	
}
