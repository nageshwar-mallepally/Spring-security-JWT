package com.project.real.config;

import com.project.real.config.security.jwt.JwtUtils;
import com.project.real.config.security.services.UserDetailsImpl;
import com.project.real.model.ParentObject;
import com.project.real.model.Session;
import com.project.real.model.SessionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
public class ObjectEntityListener {

    private static SessionWrapper sessionWrapper;

    private static ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


    @Autowired
    public void setMyService(SessionWrapper sessionWrapper, ApplicationEventPublisher applicationEventPublisher) {
        this.sessionWrapper = sessionWrapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @PrePersist
    public void prePersist(ParentObject object) {
        UserDetailsImpl session = getCurrentSession();

        if (session != null) {
            Integer personId = session.getId().intValue();
            if (object.getId() == null) {
                object.setCreatedDate(new Date());
                object.setCreatedBy(personId);
            }
            object.setModifiedBy(personId);
            object.setModifiedDate(new Date());
        }
    }

    @PostPersist
    public void postPersist(ParentObject object) {
        if (applicationEventPublisher != null) {
//            applicationEventPublisher.publishEvent(new CassiniObjectEvents.CassiniObjectCreatedEvent(object));
        }
    }

    @PreUpdate
    public void preUpdate(ParentObject object) {
        UserDetailsImpl session = getCurrentSession();

        if (session != null) {
            Integer personId = session.getId().intValue();
            object.setModifiedBy(personId);
            object.setModifiedDate(new Date());
        }
    }

    @PostUpdate
    public void postUpdate(ParentObject object) {
        if (applicationEventPublisher != null) {
//            applicationEventPublisher.publishEvent(new CassiniObjectEvents.CassiniObjectUpdatedEvent(object));
        }
    }

    @PreRemove
    public void preRemove(ParentObject object) {
       /* if (commentRepository != null) commentRepository.deleteCommentByObjectId(object.getId());
        if (activityStreamObjectRepository != null) {
            activityStreamObjectRepository.deleteActivityStreamObjectByObjectId(object.getId());
        }*/
    }

    @PostRemove
    public void postRemove(ParentObject object) {
        if (applicationEventPublisher != null) {
//            applicationEventPublisher.publishEvent(new CassiniObjectEvents.CassiniObjectDeletedEvent(object));
        }
    }

    private UserDetailsImpl getCurrentSession() {
//        Session session = SessionManager.get().getThreadSession();
        Session session = sessionWrapper.getSession();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            if (sessionWrapper == null) {
//                ApplicationContextProvider.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
            }

        }

        return userDetails;
    }

}
