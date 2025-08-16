package fa.football.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	private String email;
	private String password;
	@Column(name = "user_name")
	private String userName;
	private String phone;
	private String role;
	private String address;
	
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp DEFAULT current_timestamp")
    private LocalDateTime  createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp ON UPDATE current_timestamp")
    private LocalDateTime  updatedAt;
	
	@OneToMany(mappedBy = "manager")
	private List<League> leagues;
	
	@OneToMany(mappedBy = "manager")
	private List<Team> teams;
	
	@OneToMany(mappedBy = "teamManager")
	private List<LeagueInvitation> invitations;
	
	public Account() {
		super();
	}
	public Account(String email, String userName, String password, String phone, String role, String address) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.address = address;
		this.createdAt = LocalDateTime.now();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public List<LeagueInvitation> getInvitations() {
		return invitations;
	}
	public void setInvitations(List<LeagueInvitation> invitations) {
		this.invitations = invitations;
	}
}
