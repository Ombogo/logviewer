/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voting;

/**
 *
 * @author velma
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 *
 * @author velma
 */

public class Ldap {

    /**
     * @param args the command line arguments
     * @throws javax.naming.NamingException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
   public static  boolean validateLogin(String name, String password) throws NamingException, NoSuchAlgorithmException, UnsupportedEncodingException {
   UserDetails user =new UserDetails();
   
    

//        
        String salt = password + "{" + name + "}";
        String hashedPassword = getHash(salt);
        LdapContext ldapContext;
        Hashtable env = new Hashtable();
       //Control[] critConnCtls;
  
        String uid = "uid=" + name;
        String dn = "ou=roamtech,dc=roamtech,dc=com";

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://sms.roamtech.com:389");
//        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, uid + "," + dn);
        env.put(Context.SECURITY_CREDENTIALS, hashedPassword);
        DirContext context = null;
  SearchResult result = null ;
  boolean auth = false;
        NamingEnumeration enumeration = null;
        try {
            context = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
 InitialLdapContext lctx = new InitialLdapContext(env, null);
 enumeration =lctx.search(dn, uid, controls);
           // enumeration = lctx.c.search(dn, uid, controls);
         

            while (enumeration.hasMore()) {
             result = (SearchResult) enumeration.next();
                Attributes attributes = result.getAttributes();
                if(uid.equalsIgnoreCase(result.getName())){
                   auth =  true; 
//                   System.out.println(result.getName());
//                 System.out.println(result.toString());
//                  System.out.println(result);
               //  System.exit(0);
                   break;
                }
                
                 
//                 System.out.println(result.getName());
//                    if(name.equalsIgnoreCase(result.getName())){
//                auth =  true;
//         break;
//                    }
               // System.out.println("attributes" + attributes);
                //System.exit(0);
               // NamingEnumeration values = attributes.getAll();
//                while (values.hasMore()) {
//
//                }
//                
                
                
                
            }
        } catch (NamingException e) {
      //  auth = false;



        } finally {
            if (enumeration != null) {
                enumeration.close();
            } else if (context != null) {
                context.close();
            }
        }
       return auth;
//            throw new RuntimeException(e);
    

    }

    private static String getHash(String inputValue) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String salt = inputValue;
        if (inputValue != null) {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");

            digester.update(inputValue.getBytes());

            BigInteger hash = new BigInteger(1, digester.digest());
            salt = hash.toString(16);
            while (salt.length() < 32) { // 40 for SHA-1
                salt = "0" + salt;
            }

        }
        return salt;
    }
}
