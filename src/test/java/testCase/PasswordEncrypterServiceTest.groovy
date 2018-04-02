package testCase

import Source.PasswordEncrypterService
import spock.lang.Specification

class PasswordEncrypterServiceTest extends Specification {

    def "testing encrypt method"(){
        setup:
        String password = "sankalp"

        when:
        PasswordEncrypterService passwordEncrypterService = new PasswordEncrypterService()
        String encryptedPassword1 = passwordEncrypterService.encrypt(password)

        then:
        def decoded = new String(encryptedPassword1.decodeBase64())
        password==decoded
         
    }
}

