package com.company.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.company.address.Address;
import com.company.event.CompanyEvent;
import com.company.position.Position;
import com.company.project.Project;
import com.company.rte.BasicRichTextToolbar;
import com.company.team.Team;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.StorageItem;
import com.psddev.dari.util.StringUtils;

public class Employee extends Content implements
        CompanyEvent,
        Directory.Item {

    @Required
    @Indexed
    private String firstName;

    @Required
    @Indexed
    private String lastName;

    @Indexed
    @ToolUi.DropDown
    private Position position;

    private StorageItem profilePic;

    private String phoneNumber;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String slackId;

    private Address address;

    @ToolUi.RichText(toolbar = BasicRichTextToolbar.class)
    private String shortBio;

    private List<String> skills;

    private Date dateOfBirth;

    @Indexed
    private Date dateOfHire;

    @Indexed
    private Date dateOfTermination;

    @Indexed
    private Set<Team> teams;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public StorageItem getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(StorageItem profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public List<String> getSkills() {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public Set<Team> getTeams() {
        if (teams == null) {
            teams = new LinkedHashSet<>();
        }
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @ToolUi.Hidden
    @Indexed(unique = true)
    public String getFullName() {
        return Stream.of(getFirstName(), getLastName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String getLabel() {
        return getFullName();
    }

    @Override
    public Date getCompanyEventDate() {
        return getDateOfHire();
    }

    @DisplayName("Active Projects")
    @Ignored(false)
    public List<Project> getActiveProjects() {
        // TODO: Still need to implement
        return Collections.emptyList();
    }

    @Override
    public String createPermalink(Site site) {
        String fullNamePath = StringUtils.toNormalized(getFullName());
        if (!StringUtils.isBlank(fullNamePath)) {
            return "/employees/" + fullNamePath;
        }

        return null;
    }
}
