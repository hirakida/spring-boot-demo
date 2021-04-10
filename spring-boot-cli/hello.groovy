@Grab("thymeleaf-spring5")
@Controller
class HelloController {

    @GetMapping("/")
    def index(Model model) {
        model.addAttribute("message", "Hello!");
        "index"
    }
}
