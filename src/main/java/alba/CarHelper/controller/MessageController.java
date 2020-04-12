package alba.CarHelper.controller;

import alba.CarHelper.exceptions.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.util.logging.Messages;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("message")
@Api(value="message resources", description = "crud operations")


public class MessageController {
    private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping
    @ApiOperation(value="get messages", response = Iterable.class)
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    @ApiOperation(value="get id", response = Messages.class)
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);

    }

    @PostMapping
    @ApiOperation(value="add messages", response = Iterable.class)
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    @ApiOperation(value="edit messages", response = Iterable.class)
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    @DeleteMapping("{id}")
    @ApiOperation(value="delete messages", response = Iterable.class)
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }

    @Controller
    public class ExceptionController {

        @RequestMapping(value = "/runtimeException", method = RequestMethod.GET)
        public void throwException( ) {
            throw new RuntimeException();
        }
    }

    @Component
    public static class ScheduledTasks {

        private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Scheduled(fixedRate = 5000)
        public void reportCurrentTime() {
            log.info("The time is now {}", dateFormat.format(new Date()));
        }
    }

}