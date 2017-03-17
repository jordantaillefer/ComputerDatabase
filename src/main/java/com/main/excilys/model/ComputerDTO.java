package com.main.excilys.model;

import java.time.LocalDate;

public class ComputerDTO {
	private long id;
	private String name;
	private LocalDate discontinued;
	private LocalDate introduced;
	private CompanyDTO companyDTO;

	public static class Builder {
		private long id;
		private String name;
		private LocalDate discontinued;
		private LocalDate introduced;
		private CompanyDTO companyDTO;

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder discontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder introduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder companyDTO(CompanyDTO companyDTO) {
			this.companyDTO = companyDTO;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(id, name, discontinued, introduced,
					companyDTO);
		}
	}

	private ComputerDTO(long id, String name, LocalDate discontinued,
			LocalDate introduced, CompanyDTO companyDTO) {
		super();
		this.companyDTO = companyDTO;
		this.name = name;
		this.discontinued = discontinued;
		this.introduced = introduced;
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (companyDTO == null ? 0 : companyDTO.hashCode());
		result = prime * result
				+ (discontinued == null ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ id >>> 32);
		result = prime * result
				+ (introduced == null ? 0 : introduced.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		ComputerDTO other = (ComputerDTO) obj;
		if (companyDTO.equals(other.companyDTO)) {
			return false;
		}
		if (discontinued == null) {
			if (other.discontinued != null) {
				return false;
			}
		} else if (!discontinued.equals(other.discontinued)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (introduced == null) {
			if (other.introduced != null) {
				return false;
			}
		} else if (!introduced.equals(other.introduced)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", discontinued="
				+ discontinued + ", introduced=" + introduced + ", companyDTO="
				+ companyDTO + "]";
	}

}