@Grab("thymeleaf-spring4")

@Controller
class app {

    @GetMapping("/")
    def index(Model model) {
        model.addAttribute("message", "groovy test");
        "index"
    }
}
