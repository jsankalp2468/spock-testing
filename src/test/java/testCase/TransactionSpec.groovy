package testCase

import Source.*
import spock.lang.Specification

class TransactionSpec extends Specification {

    void setup(){}

    void "testing getAllPopularProducts"() {
        setup:
        Transaction transaction = new Transaction()

        when:
        List<Product> popularProducts = transaction.getAllPopularProducts()

        then:
        popularProducts.size() == 1
    }

    void "testing sell method"(){

        setup:
        User user1 = new User(balance: 10000)
        User user2 = new User(balance: 20000)
        Product product1 = new Product(price: 10000)
        Product product2 = new Product(price: 2000)
        Transaction transaction = new Transaction()

        when:
        transaction.sell(product2,user2)

        then:
        user2.purchasedProducts.size() == 1

        when:
        transaction.sell(product2,user2)

        then:
        user2.purchasedProducts.size() == 2

        when:
        transaction.sell(product1,user1)

        then:
        user1.purchasedProducts.size() == 1

        when:
        transaction.sell(product2,user1)

        then:
        thrown(SaleException)
    }


    def "testing discount"(){
        setup:
//        User user1 = new User(isPrivellegedCustomer: true,balance: 10000)
//        User user2 = new User(isPrivellegedCustomer: false,balance: 20000)
//        Product product1 = new Product(discountType: DiscountType.PRIVELLEGE_ONLY,price: 2000,name: "product1")
//        Product product2 = new Product(discountType: DiscountType.ALL,price: 1000,name: "product2")
        Transaction transaction = new Transaction()

        when:
        BigDecimal result = transaction.calculateDiscount(product,user)

        then:
        result == value

        where:
        product                                                                                 |   user                                                   |   value

        new Product(discountType: DiscountType.PRIVELLEGE_ONLY,price: 2000,name: "product1")    |   new User(isPrivellegedCustomer: true,balance: 10000)   |   600
        new Product(discountType: DiscountType.PRIVELLEGE_ONLY,price: 2000,name: "product1")    |   new User(isPrivellegedCustomer: false,balance: 10000)  |   200
        new Product(discountType: DiscountType.NONE,price: 2000,name: "product1")               |   new User(isPrivellegedCustomer: true,balance: 10000)   |   0
        new Product(discountType: DiscountType.ALL,price: 2000,name: "product1")                |   new User(isPrivellegedCustomer: true,balance: 10000)   |   200


    }



    def " to test cancel method"() {

        given:
        Product product = new Product(name: 'product1', price: 1600)
        User user = new User(isPrivellegedCustomer: true,balance: 800)
        Transaction transaction = new Transaction()
        user.cancelPurchase(product)

        when:
        transaction.cancelSale(product, user)
        then:
        user.purchasedProducts.size() == 0
        user.balance == 2400
    }

}