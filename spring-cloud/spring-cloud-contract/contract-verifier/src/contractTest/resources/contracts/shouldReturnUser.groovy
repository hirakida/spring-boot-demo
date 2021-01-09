package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('User API test')

    request {
        method "GET"
        url "/users/1"
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body """
            {
                "id": 1,
                "name": "name1"
            }
            """
    }
}
