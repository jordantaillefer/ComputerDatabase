package com.test.excilys.persistence;

import com.main.excilys.model.CompanyDto;
import com.main.excilys.model.ComputerDto;
import com.main.excilys.service.ComputerService;
import com.main.excilys.util.ComputerDbException;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class ComputerDaoTest {
  private ComputerService computerService = new ComputerService();

  @Test
  public void testGetComputerById() throws ComputerDbException {
    List<ComputerDto> listAllComputer;
    listAllComputer = computerService.getAllComputer();

    ComputerDto randomComputer = listAllComputer
        .get((int) (Math.random() * listAllComputer.size()));
    long idToTest = randomComputer.getId();

    ComputerDto selectComputer = computerService.getComputerById(idToTest);
    assert selectComputer.getId() == idToTest;

  }

  @Test
  public void testGetNbComputer() throws ComputerDbException {
    int nbComputer;
    nbComputer = computerService.getNbComputer();
    // TODO Company
    ComputerDto newComputerDto = new ComputerDto.Builder().name("Nouveau computer").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.createComputer(newComputerDto);
    assert computerService.getNbComputer() == nbComputer + 1;
  }

  @Test
  public void testCreateComputer() throws ComputerDbException {
    // TODO company
    ComputerDto newComputerDto = new ComputerDto.Builder().name("Nouveau computer").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    long generateId = computerService.createComputer(newComputerDto);
    newComputerDto.setId(generateId);
    ComputerDto testNewComputerDto = computerService.getComputerById(generateId);
    assert testNewComputerDto.equals(newComputerDto);
  }

  @Test(expected = ComputerDbException.class)
  public void testCreateComputerNullName() throws ComputerDbException {
    // TODO company
    ComputerDto newComputerDto = new ComputerDto.Builder().name(null).introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.createComputer(newComputerDto);
  }

  @Test(expected = ComputerDbException.class)
  public void testCreateComputerMinCharName() throws ComputerDbException {
    // TODO company
    ComputerDto newComputerDto = new ComputerDto.Builder().name("aa").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.createComputer(newComputerDto);
  }

  @Test(expected = ComputerDbException.class)
  public void testCreateComputerInvalidName() throws ComputerDbException {
    // TODO company
    ComputerDto newComputerDto = new ComputerDto.Builder().name("3az").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.createComputer(newComputerDto);
  }

  @Test
  public void testListAllComputer() throws ComputerDbException {
    int nbComputer;
    nbComputer = computerService.getNbComputer();

    List<ComputerDto> listComputer = computerService.getAllComputer();

    assert nbComputer == listComputer.size();
  }

  @Test
  public void testDeleteComputer() throws ComputerDbException {
    List<ComputerDto> listAllComputer;
    listAllComputer = computerService.getAllComputer();

    ComputerDto deleteComputer = listAllComputer
        .get((int) (Math.random() * listAllComputer.size()));
    long idToDelete = deleteComputer.getId();
    computerService.deleteComputer(idToDelete);
    assert computerService.getComputerById(idToDelete) == null;

  }

  @Test
  public void testUpdateComputer() throws ComputerDbException {
    SecureRandom random = new SecureRandom();

    String randAlpha = new BigInteger(50, random).toString(32);
    List<ComputerDto> listAllComputer;
    listAllComputer = computerService.getAllComputer();

    ComputerDto updateComputer = listAllComputer
        .get((int) (Math.random() * listAllComputer.size()));

    updateComputer.setName("Update computer " + randAlpha);
    LocalDate updateDiscontinued = new Timestamp(
        (long) (random.nextDouble() * new Date().getTime())).toLocalDateTime().toLocalDate();
    updateComputer.setDiscontinued(updateDiscontinued);
    LocalDate updateIntroduced = new Timestamp((long) (random.nextDouble() * new Date().getTime()))
        .toLocalDateTime().toLocalDate();
    updateComputer.setIntroduced(updateIntroduced);

    // TODO Switch to random company
    updateComputer.setCompanyDto(new CompanyDto.Builder().id(10).name("").build());
    computerService.updateComputer(updateComputer);
    ComputerDto newUpdateComputer = computerService.getComputerById(updateComputer.getId());
    assert updateComputer.equals(newUpdateComputer);
  }

  @Test(expected = ComputerDbException.class)
  public void testUpdateWrongCompanyIdComputer() throws ComputerDbException {
    List<ComputerDto> listAllComputer;
    listAllComputer = computerService.getAllComputer();

    ComputerDto updateComputer = listAllComputer
        .get((int) (Math.random() * listAllComputer.size()));
    updateComputer.setName(updateComputer.getName());
    updateComputer.setDiscontinued(updateComputer.getDiscontinued());
    updateComputer.setIntroduced(updateComputer.getIntroduced());
    // TODO company
    updateComputer.setCompanyDto(new CompanyDto.Builder().id(1000000).name("").build());
    computerService.updateComputer(updateComputer);
  }

  @Test(expected = ComputerDbException.class)
  public void testUpdateComputerNullName() throws ComputerDbException {
    List<ComputerDto> listAllComputer;
    listAllComputer = computerService.getAllComputer();

    ComputerDto updateComputer = listAllComputer
        .get((int) (Math.random() * listAllComputer.size()));
    updateComputer.setName(null);
    updateComputer.setDiscontinued(null);
    updateComputer.setIntroduced(null);
    // TODO company
    updateComputer.setCompanyDto(new CompanyDto.Builder().id(10).name("").build());
    computerService.updateComputer(updateComputer);
  }

  @Test(expected = ComputerDbException.class)
  public void testUpdateComputerMinCharName() throws ComputerDbException {
    // TODO company
    ComputerDto newComputer = new ComputerDto.Builder().name("aa").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.updateComputer(newComputer);
  }

  @Test(expected = ComputerDbException.class)
  public void testUpdateComputerInvalidName() throws ComputerDbException {
    // TODO company
    ComputerDto newComputer = new ComputerDto.Builder().name("3az").introduced(null)
        .discontinued(null).companyDto(new CompanyDto.Builder().id(10).name("").build()).build();
    computerService.updateComputer(newComputer);
  }
}