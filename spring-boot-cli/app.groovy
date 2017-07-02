@Grab("thymeleaf-spring4")

@Controller
class app {

    @GetMapping("/")
    def index(Model model) {
        model.addAttribute("message", "Hello!");
        "index"
    }

    @GetMapping("/api")
    @ResponseBody
    def api() {
        new Response(id:1, name:"name1")
    }

    class Response {
        Integer id
        String name
    }
}
