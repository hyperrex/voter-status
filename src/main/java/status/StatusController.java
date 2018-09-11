package status;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @RequestMapping("/status")
    public Status status(@RequestParam(value="name", defaultValue="World") String name) {
        return new Status(true);
    }

}

