package edu.boun.yilmaz4.deniz.akitaBackend.model;

import edu.boun.yilmaz4.deniz.akitaBackend.model.datatype.Badge;
import edu.boun.yilmaz4.deniz.akitaBackend.model.datatype.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 512)
    private String description;

    @Column(nullable = false, columnDefinition = "varchar(8) default 'USER'")
    private Role role;

    @Column(nullable = false)
    private int credit;

    private int blockedCredits;

    private int lifetimeCredits;

    @Column(nullable = false)
    private int reputationPoints;

    private int numOfRefusals;

    @Column(nullable = false)
    private Badge badge;

    private String photo;

    @ManyToMany
    @JoinTable(name = "interests",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> interests;

    @ManyToMany
    @JoinTable(name = "talents",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> talents;

    @OneToMany(mappedBy = "offerer")
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events = new HashSet<>();

    @ManyToMany(mappedBy = "participants")
    private Set<Event> registeredEvents = new HashSet<>();

    @ManyToMany(mappedBy = "applicants")
    private Set<Offer> appliedOffers = new HashSet<>();

    @ManyToMany(mappedBy = "participants")
    private Set<Offer> participatingOffers = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<EventFeedback> eventFeedbacks = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<OfferFeedback> offerFeedback = new HashSet<>();

    private String address;

    @OneToOne
    private GeoLocation geolocation;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Transient
        public String getPhotosImagePath() {
            if (photo == null || id == null) return null;

            return "/user-photos/" + id + "/" + photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Set<Tag> getInterests() {
        return interests;
    }

    public void setInterests(Set<Tag> interests) {
        this.interests = interests;
    }

    public Set<Tag> getTalents() {
        return talents;
    }

    public void setTalents(Set<Tag> talents) {
        this.talents = talents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getReputationPoints() {
        return reputationPoints;
    }

    public void setReputationPoints(int reputationPoints) {
        this.reputationPoints = reputationPoints;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Offer> getAppliedOffers() {
        return appliedOffers;
    }

    public void setAppliedOffers(Set<Offer> appliedOffers) {
        this.appliedOffers = appliedOffers;
    }

    public Set<Offer> getParticipatingOffers() {
        return participatingOffers;
    }

    public void setParticipatingOffers(Set<Offer> participatingOffers) {
        this.participatingOffers = participatingOffers;
    }

    public Set<Event> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(Set<Event> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<EventFeedback> getEventFeedbacks() {
        return eventFeedbacks;
    }

    public void setEventFeedbacks(Set<EventFeedback> eventFeedbacks) {
        this.eventFeedbacks = eventFeedbacks;
    }

    public Set<OfferFeedback> getOfferFeedback() {
        return offerFeedback;
    }

    public void setOfferFeedback(Set<OfferFeedback> offerFeedback) {
        this.offerFeedback = offerFeedback;
    }

    public int getBlockedCredits() {
        return blockedCredits;
    }

    public void setBlockedCredits(int blockedCredits) {
        this.blockedCredits = blockedCredits;
    }

    public int getLifetimeCredits() {
        return lifetimeCredits;
    }

    public void setLifetimeCredits(int lifetimeCredits) {
        this.lifetimeCredits = lifetimeCredits;
    }

    public int getNumOfRefusals() {
        return numOfRefusals;
    }

    public void setNumOfRefusals(int numOfRefusals) {
        this.numOfRefusals = numOfRefusals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoLocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoLocation geolocation) {
        this.geolocation = geolocation;
    }
}
