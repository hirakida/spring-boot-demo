import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('random API test')

    request {
        method "GET"
        url "/random"
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body """
            {
                "random": 1234567890
            }
            """
    }
}
