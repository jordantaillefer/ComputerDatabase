package com.main.excilys.presentation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.main.excilys.model.CompanyDTO;
import com.main.excilys.model.Page;
import com.main.excilys.service.CompanyService;
import com.main.excilys.util.ComputerDBException;

public class CompanyPresentation {
	private Page<CompanyDTO> pageCompany;
	private CompanyService companyService = new CompanyService();
	private Logger logger = LogManager.getRootLogger();

	public void listAllCompany() throws ComputerDBException {
		List<CompanyDTO> lisCompany = companyService.getAllCompany();
		lisCompany.forEach(company -> {
			System.out.println(company);
		});
	}

	public void listCompanyByPage() throws ComputerDBException {
		logger.debug("Access to company page n°" + pageCompany.getNumPage());
		long idBegin = pageCompany.getNumPage() * Page.getNbObjectPerPage();
		companyService.getCompanyInRange(idBegin, Page.getNbObjectPerPage())
				.forEach(company -> System.out.println(company));
	}

}