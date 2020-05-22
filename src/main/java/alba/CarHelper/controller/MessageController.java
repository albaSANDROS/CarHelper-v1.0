package alba.CarHelper.controller;

import alba.CarHelper.domain.Message;
import alba.CarHelper.repo.MessageRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.util.logging.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("message")
@Api(value="message resources", description = "crud operations")


public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @ApiOperation(value="get messages", response = Iterable.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value="get id", response = Messages.class)

    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }



    @PostMapping
    @ApiOperation(value="add messages", response = Iterable.class)
    public Message create(@RequestBody Message message) {


        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    @ApiOperation(value="edit messages", response = Iterable.class)
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value="delete messages", response = Iterable.class)
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
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
        private int counter;

         private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Scheduled(fixedRate = 5000)
        public void reportCurrentTime() {

           log.info("Current time is now {}", dateFormat.format(new Date()));
        }
    }

}