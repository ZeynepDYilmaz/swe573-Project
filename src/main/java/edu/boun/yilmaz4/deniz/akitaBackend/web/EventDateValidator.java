package edu.boun.yilmaz4.deniz.akitaBackend.web;

import edu.boun.yilmaz4.deniz.akitaBackend.model.Event;
import edu.boun.yilmaz4.deniz.akitaBackend.model.Member;
import edu.boun.yilmaz4.deniz.akitaBackend.service.EventService;
import edu.boun.yilmaz4.deniz.akitaBackend.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventDateValidator implements Validator {

    @Autowired
    private EventService eventService;
    @Autowired
    private MemberServiceImpl memberService;

    public boolean supports(Class<?> aClass) {
        return Event.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Event event = (Event) o;
        Member member = memberService.findByUsername(memberService.getCurrentUserLogin());
        // check if the member has any other offer for that date and time
        if (eventService.checkForUniqueTimestamp(member, event)) {
            errors.rejectValue("date", "Duplicate.date");
        }
    }
}
