@RestController
class UserController {

    @GetMapping("/users/{id}")
    def user(@PathVariable Integer id) {
        new Response(id: id, name: "name" + id)
    }

    static class Response {
        Integer id
        String name
    }
}
