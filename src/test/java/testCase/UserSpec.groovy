package testCase

import Source.User
import spock.lang.Specification

class UserSpec extends Specification {

    def "First test"() {
        expect:
        true
    }


    def "user is created"() {
        User user = new User()

    }
}


