package com.app.booksexchange.security.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.app.booksexchange.services.UsersService;

/**
 *
 * @author ibakli
 * la classe permet d'enregistrer les différents evenements
 * d'authentification et gérer les événements de connexions infructueuses Par
 * exemple, compter le nombre de tentatives de connexion échouées et de le
 * stocker en db pour être utilisé pour verrouiller ou désactiver un compte
 * d'utilisateur en fonction des exigences metier
 */
@Service
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Resource(name = "usersService")
    private UsersService userService;

    
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationListener.class);
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent) {
        Authentication authentication = appEvent.getAuthentication();
        String userName = authentication.getName() != null ? authentication.getName() : "?";
//        Users user = userService.getUserByUsername(userName);
        boolean failed = false;
       

//        if (appEvent instanceof AuthenticationSuccessEvent) {
//            if (user != null) {
//                try {
//                    userService.resetFailAttempts(userName);
//                } catch (Exception e) { 
//                    logger.error("Erreur Bddu impossible de reseter le nombre d'essai d'authentification infructueuses à zero : ", e);
//                }
//            }
//        }

//        if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
//            //AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent) appEvent;
//            if (user != null) {
//                try {
//                    failed = true;
//                    userService.updateFailAttempts(userName);
//                } catch (Exception e) { 
//                	logger.error("Erreur Bddu impossible de updater le nombre d'essai d'authentification infructueuses a zero : ", e);
//                }
//            }
//
//        }

        if (appEvent instanceof AuthenticationFailureCredentialsExpiredEvent) {
            	//AuthenticationFailureCredentialsExpiredEvent event = (AuthenticationFailureCredentialsExpiredEvent) appEvent;
            failed = true;
            logger.error("Erreur Authentification Bddu Mot de passe expiré : " + userName);
        }

        if (appEvent instanceof AuthenticationFailureLockedEvent) {
            	//AuthenticationFailureLockedEvent event = (AuthenticationFailureLockedEvent) appEvent;
            failed = true;
            logger.error("Erreur Authentification Bddu compte bloqué : " + userName);
        }
        
        if (appEvent instanceof AuthenticationFailureDisabledEvent) {
            	//AuthenticationFailureDisabledEvent event = (AuthenticationFailureDisabledEvent) appEvent;
            failed = true;
            logger.error("Erreur Authentification Bddu compte désactivé : " + userName);
        }

        if (appEvent instanceof AuthenticationFailureExpiredEvent) {
            	//AuthenticationFailureDisabledEvent event = (AuthenticationFailureDisabledEvent) appEvent;
            failed = true;
            logger.error("Erreur Authentification Bddu Expired Event : " + userName);
        }

        if (failed) {
             //TODO if authentication failed
        }else {
             //TODO if authentication success
        }       
        }
        
    }

